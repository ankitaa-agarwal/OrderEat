package com.ordereat.OrderEat.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ordereat.OrderEat.Entity.CustomerEntity;
import com.ordereat.OrderEat.Entity.IndividualOrderResponse;
import com.ordereat.OrderEat.Entity.Order;
import com.ordereat.OrderEat.Entity.OrderListForCustomer;
import com.ordereat.OrderEat.Entity.OrderProduct;
import com.ordereat.OrderEat.Entity.OrderRequestEntity;
import com.ordereat.OrderEat.Entity.RestaurantDetails;
import com.ordereat.OrderEat.Entity.RestaurantProduct;

@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepository {

	@Autowired
	EntityManager entityManager;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	RestaurantDetailsRepository restaurantDetailsRepository;

	@Override
	public void placeOrder(OrderRequestEntity order) {
		Order orderToBePlaced = new Order();
		CustomerEntity customer = customerRepository.findCustomerById(order.getCustomerId());
		orderToBePlaced.setCustomer(customer);
		RestaurantDetails restaurantDetails = restaurantDetailsRepository
				.findRestaurantDetailsById(order.getRestaurantId());
		orderToBePlaced.setRestaurant(restaurantDetails);
		List<RestaurantProduct> existingMenu = restaurantDetails.getRestaurantProducts();
		List<OrderProduct> productsList = new ArrayList<OrderProduct>();
		orderToBePlaced.setSeats(order.getSeats());
		for (OrderProduct op : order.getProducts()) {
			for (RestaurantProduct p : existingMenu) {
				if (op.getProduct_id() == p.getId()) {
					OrderProduct productToBeOrdered = new OrderProduct();
					productToBeOrdered.setOrder(orderToBePlaced);
					productToBeOrdered.setProduct(p);
					productToBeOrdered.setProduct_id(p.getId());
					productToBeOrdered.setQuantity(op.getQuantity());
					productsList.add(productToBeOrdered);
					entityManager.persist(orderToBePlaced);
					entityManager.persist(productToBeOrdered);
				}
			}
		}

	}

	@Override
	public List<Object[]> getOrders() {
		String query = "Select o.id, o.customer.firstName, o.restaurant.restaurantName,o.seats, p.quantity, rp.productName, rp.productPrice"
				+ " from Order o left join OrderProduct p on p.order = o.id"
				+ " left join p.product rp on p.product.id = rp.id";
		Query typedQuery = entityManager.createQuery(query);
		List<Object[]> orderDetails = typedQuery.getResultList();
		return orderDetails;
	}

	@Override
	public IndividualOrderResponse getOrderFromOrderId(Long orderId) {
		IndividualOrderResponse response = new IndividualOrderResponse();
		String query = "Select op.product,op.quantity from OrderProduct op where op.order.id = :orderId";
		Query resQuery1 = entityManager.createQuery(query);
		resQuery1.setParameter("orderId", orderId);
		List<Object[]> productDetails = resQuery1.getResultList();
		List<RestaurantProduct> productsOrdered = new ArrayList<RestaurantProduct>();
		int totalQuantity = 0;
		Double totalAmount = 0.0;
		response.setTotalAmount(totalAmount);
		response.setTotalQuantity(totalQuantity);

		for (Object[] obj : productDetails) {
			RestaurantProduct rp = (RestaurantProduct) obj[0];
			rp.setQuantity((int) obj[1]);
			totalQuantity = response.getTotalQuantity() + rp.getQuantity();
			totalAmount = response.getTotalAmount() + (rp.getQuantity() * rp.getProductPrice());
			response.setTotalAmount(totalAmount);
			response.setTotalQuantity(totalQuantity);

			productsOrdered.add(rp);
		}
		response.setProductsOrdered(productsOrdered);

		String query2 = "Select o.id,o.customer.id, o.customer.firstName, o.customer.lastName,"
				+ "o.restaurant.id, o.restaurant.restaurantName, o.seats " 
				+ "from Order o where o.id = :id";
		Query resQuery2 = entityManager.createQuery(query2);
		resQuery2.setParameter("id", orderId);
		List<Object[]> orderDetails = resQuery2.getResultList();
		for (Object[] obj : orderDetails) {
			response.setOrderId(orderId);
			response.setCustomerId((Long) obj[1]);
			response.setCustomerName(obj[2].toString() + " " + obj[3].toString());
			response.setRestaurantId((Long) obj[4]);
			response.setRestaurantName(obj[5].toString());
			response.setSeats((int) obj[6]);
		}

		return response;
	}

	@Override
	public OrderListForCustomer getOrderFromCustomerId(Long customerId) {
		OrderListForCustomer customerOrderList = new OrderListForCustomer();
		List<IndividualOrderResponse> orderList = new ArrayList<IndividualOrderResponse>();
		
		String query2 = "Select o.id,o.customer.id, o.customer.firstName, o.customer.lastName,"
				+ "o.restaurant.id, o.restaurant.restaurantName, o.seats " 
				+ "from Order o where o.customer.id = :id";
		
		Query resQuery1 = entityManager.createQuery(query2);
		resQuery1.setParameter("id", customerId);
		List<Object[]> orderDetailsForCustomer = resQuery1.getResultList();
		
		for (Object[] obj : orderDetailsForCustomer) {
			customerOrderList.setCustomerId(customerId);
			customerOrderList.setCustomerName(obj[2].toString()+ " "+ obj[3].toString());
			
			IndividualOrderResponse individualOrderResponse = getOrderFromOrderId((Long) obj[0]);
			
			orderList.add(individualOrderResponse);
			
		}
		customerOrderList.setOrderDetailsList(orderList);
		return customerOrderList;
	}
}

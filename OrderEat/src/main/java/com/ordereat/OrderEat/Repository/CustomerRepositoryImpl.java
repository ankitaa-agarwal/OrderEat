package com.ordereat.OrderEat.Repository;

import java.math.BigInteger;
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
import com.ordereat.OrderEat.Entity.LoginCredentials;
import com.ordereat.OrderEat.Entity.OrderListForCustomer;
import com.ordereat.OrderEat.Entity.RestaurantDetails;
import com.ordereat.OrderEat.Entity.RestaurantProduct;
import com.ordereat.OrderEat.Entity.CustomerEntity;
import com.ordereat.OrderEat.Entity.Role;
import com.ordereat.OrderEat.Entity.UserRegistrationEntity;
import com.ordereat.OrderEat.Exception.AlreadyExistsException;
import com.ordereat.OrderEat.Exception.NotFoundException;

@Repository
@Transactional
public class CustomerRepositoryImpl implements CustomerRepository {

	@Autowired
	EntityManager entityManager;
	
	@Override
	public CustomerEntity findCustomerById(Long id) {
		// TODO Auto-generated method stub
		String query = "Select c from CustomerEntity c where id = :id";
		TypedQuery<CustomerEntity> typedQuery = entityManager.createQuery(query, CustomerEntity.class);
		typedQuery.setParameter("id", id);
		CustomerEntity customerEntity = typedQuery.getSingleResult();
		return customerEntity;
	}

	
	@Override
	public List<CustomerEntity> getCustomerByEmailId(String username) {
		String query = "Select user from CustomerEntity user where user.emailId = :email";
		TypedQuery<CustomerEntity> typedQuery = entityManager.createQuery(query, CustomerEntity.class);
		typedQuery.setParameter("email", username);
		return typedQuery.getResultList();
	}
	
	@Override
	public void registerCustomer(CustomerEntity customerEntity) {
		Role role = new Role("CUSTOMER");
		customerEntity.addRole(role);
		entityManager.persist(role);
		entityManager.persist(customerEntity);
			
	}
	
	@Override
	public CustomerEntity getCustomerByUserName(String username) {
		String query = "Select user from CustomerEntity user where user.username = :username";
		TypedQuery<CustomerEntity> typedQuery = entityManager.createQuery(query, CustomerEntity.class);
		typedQuery.setParameter("username", username);
		return typedQuery.getSingleResult();
	}

	@Override
	public CustomerEntity isValidCredentials(String loginId, String password) {
		List<CustomerEntity> list = getCustomerByEmailId(loginId);
		CustomerEntity user = new CustomerEntity();
		if (list.isEmpty()) {
			user = getCustomerByUserName(loginId);
			if (user.getPassword().equals(password))
				return user;
		} else if (list.get(0).getPassword().equals(password)) {
			user = list.get(0);
			return user;
		} else
			throw new NotFoundException("Customer not registered");
		return user;
	}

	@Override
	public OrderListForCustomer getOrderFromCustomerId(Long customerId) {
		OrderListForCustomer customerOrderList = new OrderListForCustomer();
		List<IndividualOrderResponse> orderList = new ArrayList<IndividualOrderResponse>();
		
		String query2 = "Select o.id,o.customer.id, o.customer.firstName, o.customer.lastName,"
				+ "o.customer.emailId, o.customer.username,o.customer.customerCity,o.customer.phone, o.seats " 
				+ "from Order o where o.customer.id = :id";
		
		Query resQuery1 = entityManager.createQuery(query2);
		resQuery1.setParameter("id", customerId);
		List<Object[]> orderDetailsForCustomer = resQuery1.getResultList();
		
		for (Object[] obj : orderDetailsForCustomer) {
			customerOrderList.setCustomerId(customerId);
			customerOrderList.setCustomerName(obj[2].toString()+ " "+ obj[3].toString());
			customerOrderList.setEmailId(obj[4].toString());
			customerOrderList.setUsername(obj[5].toString());
			customerOrderList.setCity(obj[6].toString());
			customerOrderList.setPhoneNumber(obj[7].toString());
			IndividualOrderResponse individualOrderResponse = getOrderFromOrderId((Long) obj[0]);
			
			orderList.add(individualOrderResponse);
			
		}
		customerOrderList.setOrderDetailsList(orderList);
		return customerOrderList;
	}
	
	@Override
	public OrderListForCustomer loginAction(LoginCredentials loginCredentials) {
		String password = loginCredentials.getPassword();
		CustomerEntity validUser = new CustomerEntity();
		String loginId = loginCredentials.getLoginId();
		validUser = isValidCredentials(loginId, password);

		if (!validUser.getPassword().isEmpty()) {

			OrderListForCustomer customerDetails = getOrderFromCustomerId(validUser.getId());
			return customerDetails;
		}
		return new OrderListForCustomer();
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


	public boolean isEmptyOrNull(String string) {
		if (string == null || string.isEmpty())
			return true;
		return false;
	}
	

}

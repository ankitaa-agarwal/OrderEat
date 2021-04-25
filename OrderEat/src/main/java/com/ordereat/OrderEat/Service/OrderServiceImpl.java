package com.ordereat.OrderEat.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ordereat.OrderEat.Entity.IndividualOrderResponse;
import com.ordereat.OrderEat.Entity.OrderListForCustomer;
import com.ordereat.OrderEat.Entity.OrderRequestEntity;
import com.ordereat.OrderEat.Entity.OrderResponseEntity;
import com.ordereat.OrderEat.Entity.ResponseEntityClass;
import com.ordereat.OrderEat.Exception.BadRequestException;
import com.ordereat.OrderEat.Repository.OrderRepository;
import com.ordereat.OrderEat.Service.RestaurantUserServiceImpl.STATUS;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Override
	public ResponseEntity<ResponseEntityClass> placeOrder(OrderRequestEntity orderRequest) {
		if(orderRequest.getCustomerId() <= 0 || orderRequest.getRestaurantId() <= 0 
				|| orderRequest.getSeats() <= 0 || orderRequest.getProducts().isEmpty())
			throw new BadRequestException("Bad Request -- Missing fieqds !");
		orderRepository.placeOrder(orderRequest);
		ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(),
				null, "");
		return new ResponseEntity<ResponseEntityClass>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseEntityClass> getOrders() {
		List<Object[]> ordersList = orderRepository.getOrders();
		List<OrderResponseEntity> orders = new ArrayList<OrderResponseEntity>();
		for(Object[] obj: ordersList) {
			OrderResponseEntity response = new OrderResponseEntity();
			Long longId = (Long) obj[0];
			String customerName = obj[1].toString();
			String restaurantName = obj[2].toString();
			String productName = obj[5].toString();
			int seats =(int)obj[3];
			int quantity = (int)obj[4];
			double total = quantity * (Double)obj[6];
			response.setCustomerName(customerName);
			response.setId(longId);
			response.setRestaurantName(restaurantName);
			response.setQuantity(quantity);
			response.setSeats(seats);
			response.setProductName(productName);
			response.setTotalAmount(total);
			orders.add(response);
		}
		ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(),
				orders, "");
		return new ResponseEntity<ResponseEntityClass>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseEntityClass> getOrderFromOrderId(Long orderId) {
		IndividualOrderResponse rep = orderRepository.getOrderFromOrderId(orderId);
		List<IndividualOrderResponse> responseList = new ArrayList<IndividualOrderResponse>();
		responseList.add(rep);
		
		ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(),
				responseList, "");
		return new ResponseEntity<ResponseEntityClass>(response, HttpStatus.OK);
	}

	@Override
	public List<OrderListForCustomer> getOrderFromCustomerId(Long restaurantId) {
		OrderListForCustomer rep = orderRepository.getOrderFromCustomerId(restaurantId);
		List<OrderListForCustomer> responseList = new ArrayList<OrderListForCustomer>();
		responseList.add(rep);
		
		
		return responseList;
	}

}

package com.ordereat.OrderEat.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ordereat.OrderEat.Entity.OrderListForCustomer;
import com.ordereat.OrderEat.Entity.OrderRequestEntity;
import com.ordereat.OrderEat.Entity.ResponseEntityClass;

public interface OrderService {

	public ResponseEntity<ResponseEntityClass> placeOrder(OrderRequestEntity orderRequest);
	
	public ResponseEntity<ResponseEntityClass> getOrders();
	
	public ResponseEntity<ResponseEntityClass> getOrderFromOrderId(Long orderId);

	public List<OrderListForCustomer> getOrderFromCustomerId(Long customerId);
}

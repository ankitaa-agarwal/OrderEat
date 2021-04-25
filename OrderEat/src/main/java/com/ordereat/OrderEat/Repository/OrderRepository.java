package com.ordereat.OrderEat.Repository;

import java.util.List;

import com.ordereat.OrderEat.Entity.IndividualOrderResponse;
import com.ordereat.OrderEat.Entity.OrderListForCustomer;
import com.ordereat.OrderEat.Entity.OrderRequestEntity;
import com.ordereat.OrderEat.Entity.OrderResponseEntity;

public interface OrderRepository {

	public void placeOrder(OrderRequestEntity order);
	
	public List<Object[]> getOrders();

	IndividualOrderResponse getOrderFromOrderId(Long orderId);

	public OrderListForCustomer getOrderFromCustomerId(Long restaurantId);
}

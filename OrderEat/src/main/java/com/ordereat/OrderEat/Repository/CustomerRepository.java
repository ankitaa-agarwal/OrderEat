package com.ordereat.OrderEat.Repository;

import java.util.List;

import com.ordereat.OrderEat.Entity.CustomerEntity;
import com.ordereat.OrderEat.Entity.IndividualOrderResponse;
import com.ordereat.OrderEat.Entity.LoginCredentials;
import com.ordereat.OrderEat.Entity.OrderListForCustomer;

public interface CustomerRepository {

	public CustomerEntity findCustomerById(Long id);
	
	public void registerCustomer(CustomerEntity customerEntity);

	List<CustomerEntity> getCustomerByEmailId(String username);

	Object getCustomerByUserName(String username);

	CustomerEntity isValidCredentials(String loginId, String password);

	OrderListForCustomer loginAction(LoginCredentials loginCredentials);

	OrderListForCustomer getOrderFromCustomerId(Long customerId);

	IndividualOrderResponse getOrderFromOrderId(Long orderId);
}

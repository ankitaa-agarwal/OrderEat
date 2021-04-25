package com.ordereat.OrderEat.Service;

import org.springframework.http.ResponseEntity;

import com.ordereat.OrderEat.Entity.CustomerEntity;
import com.ordereat.OrderEat.Entity.LoginCredentials;
import com.ordereat.OrderEat.Entity.ResponseEntityClass;

public interface CustomerService {

	public ResponseEntity<ResponseEntityClass> registerCustomer(CustomerEntity customerEntity);
	
	public ResponseEntity<ResponseEntityClass> loginCustomer(LoginCredentials loginCredentials);
}

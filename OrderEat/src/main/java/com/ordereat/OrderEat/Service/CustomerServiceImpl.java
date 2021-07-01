package com.ordereat.OrderEat.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ordereat.OrderEat.Entity.CustomerEntity;
import com.ordereat.OrderEat.Entity.LoginCredentials;
import com.ordereat.OrderEat.Entity.OrderListForCustomer;
import com.ordereat.OrderEat.Entity.ResponseEntityClass;
import com.ordereat.OrderEat.Entity.UserRegistrationEntity;
import com.ordereat.OrderEat.Exception.BadRequestException;
import com.ordereat.OrderEat.Exception.NotFoundException;
import com.ordereat.OrderEat.Repository.CustomerRepository;
import com.ordereat.OrderEat.Service.RestaurantUserServiceImpl.STATUS;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public ResponseEntity<ResponseEntityClass> registerCustomer(CustomerEntity customerEntity) {
		if(customerEntity != null && customerEntity.getEmailId() != null)
			customerRepository.registerCustomer(customerEntity);
		else
			throw new BadRequestException("Bad Request - Missing fields");
		ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), null, "");
		return new ResponseEntity<ResponseEntityClass>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseEntityClass> loginCustomer(LoginCredentials loginCredentials) {
		if(loginCredentials.getLoginId().isEmpty() || loginCredentials.getPassword().isEmpty())
			throw new BadRequestException("Bad Request -- Missing fields");
		
		OrderListForCustomer orderList = customerRepository.loginAction(loginCredentials);
		List<OrderListForCustomer> list = new ArrayList<OrderListForCustomer>();
		
		if(orderList.getCustomerId() == null)
			throw new NotFoundException("Invalid Username or Password, Try again!");
		
		list.add(orderList);
		
		ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), list, "");
		return new ResponseEntity<ResponseEntityClass>(response, HttpStatus.OK);
	}

	@Override
	public Object getCustomerByUserName(String username) {
		// TODO Auto-generated method stub
		return customerRepository.getCustomerByUserName(username);
	}

}

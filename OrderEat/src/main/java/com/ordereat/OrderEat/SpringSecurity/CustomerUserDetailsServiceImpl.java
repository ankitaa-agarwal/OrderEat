package com.ordereat.OrderEat.SpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ordereat.OrderEat.Entity.CustomerEntity;
import com.ordereat.OrderEat.Entity.RestaurantUser;
import com.ordereat.OrderEat.Repository.CustomerRepository;
import com.ordereat.OrderEat.Service.CustomerService;

public class CustomerUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	CustomerService customerService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Object customer = customerService.getCustomerByUserName(username);
		if(customer == null)
			throw new UsernameNotFoundException(username);
		if(customer instanceof CustomerEntity)
			return new CustomerUserDetails((CustomerEntity) customer);
		return new RestaurantUserDetails((RestaurantUser) customer);
	}

}
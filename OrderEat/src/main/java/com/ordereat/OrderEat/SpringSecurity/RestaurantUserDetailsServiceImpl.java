package com.ordereat.OrderEat.SpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ordereat.OrderEat.Entity.CustomerEntity;
import com.ordereat.OrderEat.Entity.RestaurantUser;
import com.ordereat.OrderEat.Repository.RestaurantUserRepository;
import com.ordereat.OrderEat.Service.RestaurantUserService;

public class RestaurantUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	RestaurantUserService restaurantUserService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		RestaurantUser user = restaurantUserService.getUserByUserName(username);
		if(user == null)
			throw new UsernameNotFoundException(username);
		return new RestaurantUserDetails(user);
	}

}
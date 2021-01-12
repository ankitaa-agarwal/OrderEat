package com.ordereat.OrderEat.SpringDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ordereat.OrderEat.Entity.RestaurantOwner;
import com.ordereat.OrderEat.Repository.RestaurantOwnerRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	RestaurantOwnerRepository restaurantOwnerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		RestaurantOwner restaurantOwner = restaurantOwnerRepository.getOwnerByUserName(username);
		
		if(restaurantOwner == null)
			throw new UsernameNotFoundException("User does not exists");
		
		return new CustomUserDetails(restaurantOwner);
	}

}

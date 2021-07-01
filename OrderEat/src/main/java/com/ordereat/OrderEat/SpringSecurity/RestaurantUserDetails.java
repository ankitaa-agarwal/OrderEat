package com.ordereat.OrderEat.SpringSecurity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ordereat.OrderEat.Entity.RestaurantUser;
import com.ordereat.OrderEat.Entity.Role;
import com.ordereat.OrderEat.Repository.RoleRepository;

public class RestaurantUserDetails implements UserDetails {

	@Autowired
	RoleRepository roleRepo;
	
	private RestaurantUser restaurantUser;

	public RestaurantUserDetails(RestaurantUser restaurantUser) {
		this.restaurantUser = restaurantUser;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Role role = restaurantUser.getRoleId();
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		//for (Role role : roles) {
		authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
		//}

		return authorities;
	}

	@Override
	public String getPassword() {
		return restaurantUser.getPassword();
	}

	@Override
	public String getUsername() {
		return restaurantUser.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String getUserID() {
		return restaurantUser.getId().toString();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public RestaurantUser getLoggedInUser() {
		// TODO Auto-generated method stub
		return restaurantUser;
	}

}
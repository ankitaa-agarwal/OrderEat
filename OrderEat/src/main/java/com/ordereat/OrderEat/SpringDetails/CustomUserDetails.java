package com.ordereat.OrderEat.SpringDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ordereat.OrderEat.Entity.RestaurantOwner;
import com.ordereat.OrderEat.Entity.Role;

public class CustomUserDetails implements UserDetails {

	
	private RestaurantOwner restaurantOwner;

	public CustomUserDetails(RestaurantOwner restaurantOwner) {
		this.restaurantOwner = restaurantOwner;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Role> roles = restaurantOwner.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
		}

		return authorities;
	}

	@Override
	public String getPassword() {
		return restaurantOwner.getPassword();
	}

	@Override
	public String getUsername() {
		return restaurantOwner.getUsername();
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

}
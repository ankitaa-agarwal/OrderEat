package com.ordereat.OrderEat;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ordereat.OrderEat.SpringSecurity.CustomerUserDetails;
import com.ordereat.OrderEat.SpringSecurity.RestaurantUserDetails;

public class AuditorAwareImpl implements AuditorAware<String> {
	 
    @Override
    public Optional<String> getCurrentAuditor() {
    	String r = "";
    	 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    	    if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
    	      return Optional.of(r);
    	    }
    	    else if(authentication.getPrincipal() instanceof CustomerUserDetails)
    	    	return Optional.ofNullable(((CustomerUserDetails) authentication.getPrincipal()).getUserID());
    	    else
    	    	return Optional.ofNullable(((RestaurantUserDetails) authentication.getPrincipal()).getUserID());
    }

}

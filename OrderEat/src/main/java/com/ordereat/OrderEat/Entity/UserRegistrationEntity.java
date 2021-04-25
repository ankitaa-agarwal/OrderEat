package com.ordereat.OrderEat.Entity;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class UserRegistrationEntity {

	@JsonProperty("restaurantDetails")
	private RestaurantDetails restaurantDetails;
	
	@JsonProperty("restaurantUser")
	private RestaurantUser restaurantUser;
	
	@JsonProperty("role")
	Role role;
	
	public UserRegistrationEntity() {}
	
	public UserRegistrationEntity(RestaurantDetails restaurantDetails, RestaurantUser restaurantUser, Role role) {
		super();
		this.restaurantDetails = restaurantDetails;
		this.restaurantUser = restaurantUser;
		this.role = role;
	}

	public RestaurantDetails getRestaurantDetails() {
		return restaurantDetails;
	}

	public void setRestaurantDetails(RestaurantDetails restaurantDetails) {
		this.restaurantDetails = restaurantDetails;
	}

	public RestaurantUser getRestaurantUser() {
		return restaurantUser;
	}

	public void setRestaurantUser(RestaurantUser restaurantUser) {
		this.restaurantUser = restaurantUser;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}

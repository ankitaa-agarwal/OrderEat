package com.ordereat.OrderEat.Entity;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class CombinedRegistrationEntity {

	@JsonProperty("restaurantDetails")
	private RestaurantDetails restaurantDetails;
	
	@JsonProperty("restaurantOwner")
	private RestaurantOwner restaurantOwner;

	public CombinedRegistrationEntity() {}
	
	public CombinedRegistrationEntity(RestaurantDetails restaurantDetails, RestaurantOwner restaurantOwner) {
		super();
		this.restaurantDetails = restaurantDetails;
		this.restaurantOwner = restaurantOwner;
	}

	public RestaurantDetails getRestaurantDetails() {
		return restaurantDetails;
	}

	public void setRestaurantDetails(RestaurantDetails restaurantDetails) {
		this.restaurantDetails = restaurantDetails;
	}

	public RestaurantOwner getRestaurantOwner() {
		return restaurantOwner;
	}

	public void setRestaurantOwner(RestaurantOwner restaurantOwner) {
		this.restaurantOwner = restaurantOwner;
	}
	
	
}

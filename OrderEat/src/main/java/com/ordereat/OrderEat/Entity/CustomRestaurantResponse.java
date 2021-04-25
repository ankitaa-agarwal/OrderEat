package com.ordereat.OrderEat.Entity;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class CustomRestaurantResponse {

	@JsonProperty("restaurantDetails")
	private RestaurantDetails restaurantDetails;
	
	@JsonProperty("restaurantProducts")
	private List<RestaurantProduct> restaurantProductList;
	
	@JsonProperty("restaurantDays")
	private List<RestaurantDays> restaurantDays;
	
	public CustomRestaurantResponse() {}

	public RestaurantDetails getRestaurantDetails() {
		return restaurantDetails;
	}

	public void setRestaurantDetails(RestaurantDetails restaurantDetails) {
		this.restaurantDetails = restaurantDetails;
	}

	public List<RestaurantProduct> getRestaurantProductList() {
		return restaurantProductList;
	}

	public void setRestaurantProductList(List<RestaurantProduct> restaurantProduct) {
		this.restaurantProductList = restaurantProduct;
	}

	public List<RestaurantDays> getRestaurantDays() {
		return restaurantDays;
	}

	public void setRestaurantDays(List<RestaurantDays> restaurantDays) {
		this.restaurantDays = restaurantDays;
	}
	
}

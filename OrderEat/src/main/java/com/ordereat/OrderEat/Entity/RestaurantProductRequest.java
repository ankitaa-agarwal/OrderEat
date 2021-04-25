package com.ordereat.OrderEat.Entity;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class RestaurantProductRequest {

	@JsonProperty("productId")
	private String productID;
	
	@JsonProperty("restaurant_id")
	private String restaurant_id;
	
	@JsonProperty("name")
	private String productName;
	
	@JsonProperty("description")
	private String productDescription;
	
	@JsonProperty("price")
	private String productPrice;
	
	@JsonProperty("prepTime")
	private String ProductPrepTime;
	
	public RestaurantProductRequest() {}

	public String getRestaurant_id() {
		return restaurant_id;
	}

	public void setRestaurant_id(String restaurant_id) {
		this.restaurant_id = restaurant_id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductPrepTime() {
		return ProductPrepTime;
	}

	public void setProductPrepTime(String productPrepTime) {
		ProductPrepTime = productPrepTime;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}
	
	
}

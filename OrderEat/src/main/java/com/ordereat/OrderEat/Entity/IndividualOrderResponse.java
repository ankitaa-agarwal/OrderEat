package com.ordereat.OrderEat.Entity;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
@JsonFilter("customerOrderFilter")
public class IndividualOrderResponse {

	@JsonProperty("id")
	private Long orderId;
	
	@JsonProperty("customer_id")
	private Long customerId;
	
	@JsonProperty("customer_name")
	private String customerName;
	
	@JsonProperty("restaurant_id")
	private Long restaurantId;
	
	@JsonProperty("restaurant_name")
	private String restaurantName;
	
	@JsonProperty("seats")
	private int seats;
	
	@JsonProperty("products")
	List<RestaurantProduct> productsOrdered;
	
	@JsonProperty("total_quantity")
	private int totalQuantity;
	
	@JsonProperty("total_amount")
	private Double totalAmount;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public List<RestaurantProduct> getProductsOrdered() {
		return productsOrdered;
	}

	public void setProductsOrdered(List<RestaurantProduct> productsOrdered) {
		this.productsOrdered = productsOrdered;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}

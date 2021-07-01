package com.ordereat.OrderEat.Entity;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class OrderListForCustomer {

	@JsonProperty("customer_id")
	private Long customerId;
	
	@JsonProperty("customer_name")
	private String customerName;
	
	@JsonProperty("emailId")
	private String emailId;
	
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("customerCity")
	private String city;
	
	@JsonProperty("phone")
	private String phoneNumber;
	
	@JsonProperty("order_details")
	private List<IndividualOrderResponse> orderDetailsList;

	public OrderListForCustomer() {
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

	public List<IndividualOrderResponse> getOrderDetailsList() {
		return orderDetailsList;
	}

	public void setOrderDetailsList(List<IndividualOrderResponse> orderDetailsList) {
		this.orderDetailsList = orderDetailsList;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}

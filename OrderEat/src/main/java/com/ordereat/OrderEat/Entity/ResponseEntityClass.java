package com.ordereat.OrderEat.Entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class ResponseEntityClass {

	@JsonProperty("status")
	private String status;
	
	@JsonProperty("message")
	private List<?> message = new ArrayList<>();
	
	@JsonProperty("error")
	private String error;
	
	public ResponseEntityClass() {}
	
	public ResponseEntityClass(String status, List<?> message, String error) {
		super();
		this.status = status;
		this.message = message;
		this.error = error;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<?> getMessage() {
		return message;
	}
	public void setMessage(List<?> message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}

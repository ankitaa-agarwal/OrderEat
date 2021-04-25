package com.ordereat.OrderEat.Entity;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class LoginCredentials {

	@JsonProperty("loginId")
	private String loginId;
	
	@JsonProperty("password")
	private String password;

	public LoginCredentials() {}
	
	public LoginCredentials(String loginId, String password) {
		this.loginId = loginId;
		this.password = password;
	}
	
	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

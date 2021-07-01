package com.ordereat.OrderEat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ordereat.OrderEat.Entity.UserRegistrationEntity;
import com.ordereat.OrderEat.Entity.LoginCredentials;
import com.ordereat.OrderEat.Entity.ResponseEntityClass;
import com.ordereat.OrderEat.Entity.RestaurantStaff;
import com.ordereat.OrderEat.Service.RestaurantAuthService;

@RestController
@RequestMapping("/roe/auth")
public class RestaurantAuthController {

	@Autowired
	RestaurantAuthService restaurantAuthService;

	@PostMapping("/register")
	public ResponseEntity<ResponseEntityClass> registerRestaurantUser(
			@RequestBody UserRegistrationEntity combinedEntity) {
		return restaurantAuthService.registerRestaurantUser(combinedEntity);
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseEntityClass> login() {
		return restaurantAuthService.loginUser();
	}
	
	@PostMapping("/registerStaff")
	public ResponseEntity<ResponseEntityClass> createRestaurantUser(@RequestBody RestaurantStaff restaurantStaff){
		return restaurantAuthService.createRestaurantUser(restaurantStaff);
	}
	
	@PostMapping("/verifyRestaurantUser")
	public ResponseEntity<ResponseEntityClass> verifyRestaurantUser(@RequestBody LoginCredentials loginCredentials){
		return restaurantAuthService.verifyRestaurantUser(loginCredentials);
	}

	@PostMapping("/updateCreds")
	public ResponseEntity<ResponseEntityClass> updateStaffCreds(@RequestBody RestaurantStaff restaurantStaff){
		return null;
	}
}

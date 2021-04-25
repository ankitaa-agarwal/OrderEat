package com.ordereat.OrderEat.Service;

import org.springframework.http.ResponseEntity;

import com.ordereat.OrderEat.Entity.UserRegistrationEntity;
import com.ordereat.OrderEat.Entity.LoginCredentials;
import com.ordereat.OrderEat.Entity.ResponseEntityClass;
import com.ordereat.OrderEat.Entity.RestaurantStaff;

public interface RestaurantAuthService {

	public ResponseEntity<ResponseEntityClass> registerRestaurantUser(UserRegistrationEntity combinedEntity);

	public ResponseEntity<ResponseEntityClass> loginUser(LoginCredentials loginCredentials);

	public ResponseEntity<ResponseEntityClass> createRestaurantUser(RestaurantStaff restaurantStaff);

	public ResponseEntity<ResponseEntityClass> verifyRestaurantUser(LoginCredentials loginCredentials);

}

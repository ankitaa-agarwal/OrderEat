package com.ordereat.OrderEat.Service;

import org.springframework.http.ResponseEntity;

import com.ordereat.OrderEat.Entity.ResponseEntityClass;
import com.ordereat.OrderEat.Entity.RestaurantDetails;
import com.ordereat.OrderEat.Entity.RestaurantUser;

public interface RestaurantUserService {

	ResponseEntity<ResponseEntityClass> findAllRestaurantUser();

	ResponseEntity<ResponseEntityClass> findRestaurantUserById(Long id);

	ResponseEntity<ResponseEntityClass> findAllRestaurantDetails();

	ResponseEntity<ResponseEntityClass> findRestaurantDetailsById(Long id);

	boolean findIfRestaurantExists(Long restaurantId);

	ResponseEntity<ResponseEntityClass> updateRestaurantDetails(Long restaurantId, RestaurantDetails restaurantDetails);

	boolean findIfRestaurantUserExists(Long userId);

	ResponseEntity<ResponseEntityClass> updateRestaurantUser(Long userId, RestaurantUser restaurantUser);

	ResponseEntity<ResponseEntityClass> getRestaurantDetailsFromUser(Long user_id);

	ResponseEntity<ResponseEntityClass> getUserDetailsFromRestaurantId(Long restaurantId);

	ResponseEntity<ResponseEntityClass> getRestaurants();
}

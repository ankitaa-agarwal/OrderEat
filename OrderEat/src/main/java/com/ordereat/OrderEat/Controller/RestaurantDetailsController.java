package com.ordereat.OrderEat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ordereat.OrderEat.Entity.ResponseEntityClass;
import com.ordereat.OrderEat.Entity.RestaurantDetails;
import com.ordereat.OrderEat.Entity.RestaurantUser;
import com.ordereat.OrderEat.Service.RestaurantUserService;

@RestController
@RequestMapping("/roe/restaurant")
public class RestaurantDetailsController {

	@Autowired
	RestaurantUserService restaurantUserService;
	
	@GetMapping("/getAllRestaurantUsersForRestaurant/{id}")
	public ResponseEntity<ResponseEntityClass> getUserDetailsFromRestaurantId(@PathVariable("id") Long restaurantId) {
		return restaurantUserService.getUserDetailsFromRestaurantId(restaurantId);
	}

	@PutMapping("/updateRestaurantDetails/{id}")
	public ResponseEntity<ResponseEntityClass> updateRestaurantDetails(@PathVariable("id") Long restaurantId,
			@RequestBody RestaurantDetails restaurantDetails) {
		return restaurantUserService.updateRestaurantDetails(restaurantId, restaurantDetails);
	}

	@PutMapping("/updateRestaurantUser/{id}")
	public ResponseEntity<ResponseEntityClass> updateRestaurantUser(@PathVariable("id") Long userId,
			@RequestBody RestaurantUser restaurantUser) {
		return restaurantUserService.updateRestaurantUser(userId, restaurantUser);
	}
	
	/*****************************************************************************************/
	
	@GetMapping("/getRestaurantUsers")
	public ResponseEntity<ResponseEntityClass> getRestaurantUsersList() {
		return restaurantUserService.findAllRestaurantUser();
	}

	@GetMapping("/getRestaurantUser/{id}")
	public ResponseEntity<ResponseEntityClass> getRestaurantUser(@PathVariable("id") Long id) {
		return restaurantUserService.findRestaurantUserById(id);
	}

	@GetMapping("/getRestaurants")
	public ResponseEntity<ResponseEntityClass> getRestaurantList() {
		return restaurantUserService.findAllRestaurantDetails();
	}

	@GetMapping("/getRestaurants/{id}")
	public ResponseEntity<ResponseEntityClass> getRestaurant(@PathVariable("id") Long id) {
		return restaurantUserService.findRestaurantDetailsById(id);
	}

	@GetMapping("/getRestaurantDetailsFromUser/{id}")
	public ResponseEntity<ResponseEntityClass> getDetailsFromRestaurantUser(@PathVariable("id") Long user_id) {
		return restaurantUserService.getRestaurantDetailsFromUser(user_id);
	}

}

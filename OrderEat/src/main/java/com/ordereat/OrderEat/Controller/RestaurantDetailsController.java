package com.ordereat.OrderEat.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ordereat.OrderEat.Entity.ResponseEntityClass;
import com.ordereat.OrderEat.Entity.RestaurantDetails;
import com.ordereat.OrderEat.Entity.RestaurantOwner;
import com.ordereat.OrderEat.Exception.NotFoundException;
import com.ordereat.OrderEat.Repository.RestaurantDetailsRepository;
import com.ordereat.OrderEat.Repository.RestaurantOwnerRepository;

@RestController
@RequestMapping("/roe/restaurant")
public class RestaurantDetailsController {

	@Autowired
	RestaurantDetailsRepository restaurantDetailsRespository;
	
	@Autowired
	RestaurantOwnerRepository restaurantOwnerRepository;
	
	enum STATUS{
		SUCCESS("success"), FAILURE("failure");
		
		public final String status;
		
		private STATUS(String status) {
			this.status = status;
		}
	}
	
	@GetMapping("/getRestaurantUsers")
	public ResponseEntity<ResponseEntityClass> getRestaurantOwnersList(){
		
		List<RestaurantOwner> restaurantOwnersList = (List<RestaurantOwner>) restaurantOwnerRepository.findAllRestaurantOwner();
		ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), restaurantOwnersList, "");
		return new ResponseEntity<ResponseEntityClass>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getRestaurantUser/{id}")
	public ResponseEntity<ResponseEntityClass> getRestaurantOwner(@PathVariable("id") Long id) {
		RestaurantOwner owner = restaurantOwnerRepository.findRestaurantOwnerById(id);
		List<RestaurantOwner> ownerExtracted = new ArrayList<RestaurantOwner>();
		if(owner == null) {
			/*
			 * ResponseEntityClass response = new
			 * ResponseEntityClass(STATUS.FAILURE.toString(), ownerExtracted, ""); return
			 * new ResponseEntity<ResponseEntityClass>(response,HttpStatus.NOT_FOUND);
			 */
			throw new NotFoundException("Restaurant User does not exist");
		}else {
			ownerExtracted.add(owner);
			ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), ownerExtracted, "");
			return new ResponseEntity<ResponseEntityClass>(response,HttpStatus.OK);
		}
	}
	
	@GetMapping("/getRestaurants")
	public ResponseEntity<ResponseEntityClass> getRestaurantList(){
		List<RestaurantDetails> restaurantOwnersList = (List<RestaurantDetails>) restaurantDetailsRespository.findAll();
		ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), restaurantOwnersList, "");
		return new ResponseEntity<ResponseEntityClass>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getRestaurants/{id}")
	public ResponseEntity<ResponseEntityClass> getRestaurant(@PathVariable("id") Long id) {
		RestaurantDetails restaurantDetails = restaurantDetailsRespository.findRestaurantOwnerById(id);
		List<RestaurantDetails> restaurantDetailsExtracted = new ArrayList<RestaurantDetails>();
		if(restaurantDetails == null) {
			/*
			 * ResponseEntityClass response = new
			 * ResponseEntityClass(STATUS.FAILURE.toString(), restaurantDetailsExtracted,
			 * ""); return new
			 * ResponseEntity<ResponseEntityClass>(response,HttpStatus.NOT_FOUND);
			 */
			throw new NotFoundException("Restaurant does not exist");
		}else {
			restaurantDetailsExtracted.add(restaurantDetails);
			ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), restaurantDetailsExtracted, "");
			return new ResponseEntity<ResponseEntityClass>(response,HttpStatus.OK);
		}
	}
	
	/*
	 * @PutMapping("/updaterestaurantuser") public
	 * updateRestaurantOwner(@RequestBody RestaurantOwner restaurantOwner) { Long id
	 * = restaurantOwner.getId(); if(restaurantDetailsRespository.findById(id)) {
	 * restaurantDetailsRespository.save(entity); } }
	 */
}

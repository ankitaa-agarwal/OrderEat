package com.ordereat.OrderEat.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ordereat.OrderEat.Entity.CombinedRegistrationEntity;
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
	
	@GetMapping("/getRestaurantOwnerDetails/{id}")
	public ResponseEntity<ResponseEntityClass> getOwnerDetailsFromRestaurantId(@PathVariable("id") Long restaurantId){
		RestaurantOwner restaurantOwner = restaurantOwnerRepository.getOwnerFromRestaurantId(restaurantId);
		List<RestaurantOwner> ownerExtracted = new ArrayList<RestaurantOwner>();
		if(restaurantOwner == null) {
			/*
			 * ResponseEntityClass response = new
			 * ResponseEntityClass(STATUS.FAILURE.toString(), ownerExtracted, ""); return
			 * new ResponseEntity<ResponseEntityClass>(response,HttpStatus.NOT_FOUND);
			 */
			throw new NotFoundException("Restaurant User does not exist");
		}else {
			ownerExtracted.add(restaurantOwner);
			ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), ownerExtracted, "");
			return new ResponseEntity<ResponseEntityClass>(response,HttpStatus.OK);
		}
	}
	
	@PutMapping("/updateRestaurantDetails/{id}")
	public ResponseEntity<ResponseEntityClass> updateRestaurantDetails(@PathVariable("id") Long restaurantId, @RequestBody RestaurantDetails restaurantDetails){
		RestaurantDetails updatedDetails = new RestaurantDetails();
		List<RestaurantDetails> updatedList = new ArrayList<RestaurantDetails>();
		if(restaurantOwnerRepository.findIfRestaurantExists(restaurantId)) {
			updatedDetails = restaurantOwnerRepository.updateRestaurantDetails(restaurantDetails);
			updatedList.add(updatedDetails);
			ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), updatedList, "");
			return new ResponseEntity<ResponseEntityClass>(response,HttpStatus.OK);
		}else {
			throw new NotFoundException("Restaurant does not exist");
		}
	}

	@PutMapping("/updateRestaurantOwner/{id}")
	public ResponseEntity<ResponseEntityClass> updateRestaurantOwner(@PathVariable("id") Long userId, @RequestBody RestaurantOwner restaurantOwner){
		RestaurantOwner updatedOwner = new RestaurantOwner();
		List<RestaurantOwner> updatedList = new ArrayList<RestaurantOwner>();
		if(restaurantOwnerRepository.findIfRestaurantOwnerExists(userId)) {
			updatedOwner = restaurantOwnerRepository.updateRestaurantOwner(restaurantOwner);
			updatedList.add(updatedOwner);
			ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), updatedList, "");
			return new ResponseEntity<ResponseEntityClass>(response,HttpStatus.OK);
		}else {
			throw new NotFoundException("Restaurant User does not exist");
		}
	}
	
	@GetMapping("/getRestaurantDetailsFromOwner/{id}")
	public ResponseEntity<ResponseEntityClass> getDetailsFromRestaurantOwner(@PathVariable("id") Long owner_id){
		RestaurantDetails restaurantDetails = restaurantOwnerRepository.getOwnerAndRestaurantDetails(owner_id);
		List<RestaurantDetails> extractedResponseList = new ArrayList<RestaurantDetails>();
		if(restaurantDetails == null) {
			/*
			 * ResponseEntityClass response = new
			 * ResponseEntityClass(STATUS.FAILURE.toString(), ownerExtracted, ""); return
			 * new ResponseEntity<ResponseEntityClass>(response,HttpStatus.NOT_FOUND);
			 */
			throw new NotFoundException("Restaurant User does not exist");
		}else {
			extractedResponseList.add(restaurantDetails);
			ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), extractedResponseList, "");
			return new ResponseEntity<ResponseEntityClass>(response,HttpStatus.OK);
		}
	}
	
}

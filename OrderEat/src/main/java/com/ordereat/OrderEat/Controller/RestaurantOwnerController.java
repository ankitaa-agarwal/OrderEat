package com.ordereat.OrderEat.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ordereat.OrderEat.Controller.RestaurantDetailsController.STATUS;
import com.ordereat.OrderEat.Entity.CombinedRegistrationEntity;
import com.ordereat.OrderEat.Entity.ResponseEntityClass;
import com.ordereat.OrderEat.Entity.RestaurantOwner;
import com.ordereat.OrderEat.Exception.AlreadyExistsException;
import com.ordereat.OrderEat.Exception.BadRequestException;
import com.ordereat.OrderEat.Repository.RestaurantOwnerRepository;
import com.ordereat.OrderEat.Service.RestaurantOwnerService;

@RestController
@RequestMapping("/roe/auth")
public class RestaurantOwnerController {

	@Autowired
	RestaurantOwnerService restaurantOwnerService;
	
	@Autowired
	RestaurantOwnerRepository restaurantOwnerRepository;
	
	@PostMapping("/register")
	public ResponseEntity<ResponseEntityClass> registerRestaurantOwner(@RequestBody CombinedRegistrationEntity combinedEntity) {
		if(combinedEntity == null || combinedEntity.getRestaurantDetails() == null || combinedEntity.getRestaurantOwner() == null) {
			throw new BadRequestException("Bad Request - Missing fields");
		}else {
			if(!restaurantOwnerService.findIfExists(combinedEntity)) {
				restaurantOwnerService.registerRestaurantOwner(combinedEntity);
				ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), null, "");
				return new ResponseEntity<ResponseEntityClass>(response,HttpStatus.FORBIDDEN);
			}
			else
				throw new AlreadyExistsException("User exists");
		}
	}
	
	@GetMapping("/login")
	public ResponseEntity<ResponseEntityClass> login(){
		List<RestaurantOwner> restaurantOwnersList = (List<RestaurantOwner>) restaurantOwnerRepository.findAllRestaurantOwner();
		ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), restaurantOwnersList, "");
		return new ResponseEntity<ResponseEntityClass>(response,HttpStatus.OK);
	}
	
}

package com.ordereat.OrderEat.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ordereat.OrderEat.Entity.CustomRestaurantResponse;
import com.ordereat.OrderEat.Entity.ResponseEntityClass;
import com.ordereat.OrderEat.Entity.RestaurantDays;
import com.ordereat.OrderEat.Entity.RestaurantDetails;
import com.ordereat.OrderEat.Entity.RestaurantProduct;
import com.ordereat.OrderEat.Entity.RestaurantUser;
import com.ordereat.OrderEat.Exception.NotFoundException;
import com.ordereat.OrderEat.Repository.RestaurantDaysRepository;
import com.ordereat.OrderEat.Repository.RestaurantDetailsRepository;
import com.ordereat.OrderEat.Repository.RestaurantProductsRepository;
import com.ordereat.OrderEat.Repository.RestaurantUserRepository;

@Service
public class RestaurantUserServiceImpl implements RestaurantUserService {

	enum STATUS {
		SUCCESS("success"), FAILURE("failure");

		public final String status;

		private STATUS(String status) {
			this.status = status;
		}
	}

	@Autowired
	RestaurantUserRepository restaurantUserRepository;

	@Autowired
	RestaurantDetailsRepository restaurantDetailsRepository;
	
	@Autowired
	RestaurantProductsRepository restaurantProductsRepository;
	
	@Autowired
	RestaurantDaysRepository restaurantDaysRepository;

	@Override
	public ResponseEntity<ResponseEntityClass> findAllRestaurantUser() {
		List<RestaurantUser> restaurantUsersList = (List<RestaurantUser>) restaurantUserRepository
				.findAllRestaurantUser();
		ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), restaurantUsersList, "");
		return new ResponseEntity<ResponseEntityClass>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseEntityClass> findRestaurantUserById(Long id) {
		RestaurantUser user = restaurantUserRepository.findRestaurantUserById(id);
		List<RestaurantUser> userExtracted = new ArrayList<RestaurantUser>();
		if (user == null) {
			throw new NotFoundException("Restaurant User does not exist");
		} else {
			userExtracted.add(user);
			ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), userExtracted, "");
			return new ResponseEntity<ResponseEntityClass>(response, HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<ResponseEntityClass> findAllRestaurantDetails() {
		List<RestaurantDetails> restaurantList = (List<RestaurantDetails>) restaurantDetailsRepository.findAll();
		ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), restaurantList, "");
		return new ResponseEntity<ResponseEntityClass>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseEntityClass> findRestaurantDetailsById(Long id) {
		RestaurantDetails restaurantDetails = restaurantDetailsRepository.findRestaurantDetailsById(id);
		
		if (restaurantDetails == null) {
			throw new NotFoundException("Restaurant does not exist");
		} else {
			CustomRestaurantResponse customRestaurantResponse = new CustomRestaurantResponse();
			customRestaurantResponse.setRestaurantDetails(restaurantDetails);
			List<RestaurantProduct> restaurantProductList = restaurantProductsRepository.getProductsFromRestaurant(id);
			customRestaurantResponse.setRestaurantProductList(restaurantProductList);
			List<RestaurantDays> restaurantDaysList = restaurantDaysRepository.getDaysFromRestaurant(id);
			customRestaurantResponse.setRestaurantDays(restaurantDaysList);
			
			List<CustomRestaurantResponse> restaurantDetailsExtracted = new ArrayList<CustomRestaurantResponse>();
			restaurantDetailsExtracted.add(customRestaurantResponse);
			ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(),
					restaurantDetailsExtracted, "");
			return new ResponseEntity<ResponseEntityClass>(response, HttpStatus.OK);
		}
	}

	@Override
	public boolean findIfRestaurantExists(Long restaurantId) {
		return restaurantUserRepository.findIfRestaurantExists(restaurantId);
	}

	@Override
	public ResponseEntity<ResponseEntityClass> updateRestaurantDetails(Long restaurantId,
			RestaurantDetails restaurantDetails) {
		/*
		 * RestaurantDetails updatedDetails = new RestaurantDetails();
		 * List<RestaurantDetails> updatedList = new ArrayList<RestaurantDetails>();
		 */
		if (restaurantUserRepository.findIfRestaurantExists(restaurantId)) {
			restaurantUserRepository.updateRestaurantDetails(restaurantId, restaurantDetails);
			/* updatedList.add(updatedDetails); */
			ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), null, "");
			return new ResponseEntity<ResponseEntityClass>(response, HttpStatus.OK);
		} else {
			throw new NotFoundException("Restaurant does not exist");
		}
	}

	@Override
	public boolean findIfRestaurantUserExists(Long userId) {
		return restaurantUserRepository.findIfRestaurantUserExists(userId);
	}

	@Override
	public ResponseEntity<ResponseEntityClass> updateRestaurantUser(Long userId, RestaurantUser restaurantUser) {
		if (restaurantUserRepository.findIfRestaurantUserExists(userId)) {
			restaurantUserRepository.updateRestaurantUser(userId, restaurantUser);
			ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), null, "");
			return new ResponseEntity<ResponseEntityClass>(response, HttpStatus.OK);
		} else {
			throw new NotFoundException("Restaurant User does not exist");
		}
	}

	@Override
	public ResponseEntity<ResponseEntityClass> getRestaurantDetailsFromUser(Long user_id) {
		Object[] restaurantDetails = restaurantUserRepository.getRestaurantDetailsFromUser(user_id);
		Long longId = ((BigInteger) restaurantDetails[0]).longValue();
		RestaurantDetails resDetails = new RestaurantDetails();
		resDetails.setId(longId);
		resDetails.setRestaurantName(restaurantDetails[1].toString());
		
		List<RestaurantDetails> extractedResponseList = new ArrayList<RestaurantDetails>();
		extractedResponseList.add(resDetails);
		ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), extractedResponseList, "");
		return new ResponseEntity<ResponseEntityClass>(response, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<ResponseEntityClass> getUserDetailsFromRestaurantId(Long restaurantId) {
		List<RestaurantUser> restaurantUser = restaurantUserRepository.getUserDetailsFromRestaurantId(restaurantId);
		if (restaurantUser == null) {
			throw new NotFoundException("Restaurant User does not exist");
		} else {
			ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), restaurantUser, "");
			return new ResponseEntity<ResponseEntityClass>(response, HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<ResponseEntityClass> getRestaurants() {
		return findAllRestaurantDetails();
	}

	@Override
	public RestaurantUser getUserByUserName(String username) {
		// TODO Auto-generated method stub
		return (RestaurantUser) restaurantUserRepository.getUserByUserName(username);
	}

}

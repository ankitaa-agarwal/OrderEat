package com.ordereat.OrderEat.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ordereat.OrderEat.Entity.LoginCredentials;
import com.ordereat.OrderEat.Entity.ResponseEntityClass;
import com.ordereat.OrderEat.Entity.RestaurantStaff;
import com.ordereat.OrderEat.Entity.RestaurantUser;
import com.ordereat.OrderEat.Entity.UserRegistrationEntity;
import com.ordereat.OrderEat.Exception.AlreadyExistsException;
import com.ordereat.OrderEat.Exception.BadRequestException;
import com.ordereat.OrderEat.Exception.NotFoundException;
import com.ordereat.OrderEat.Repository.RestaurantUserRepository;
import com.ordereat.OrderEat.Service.RestaurantUserServiceImpl.STATUS;

@Service
public class RestaurantAuthServiceImpl implements RestaurantAuthService {

	@Autowired
	RestaurantUserRepository restaurantUserRepository;
	
	@Override
	public ResponseEntity<ResponseEntityClass> registerRestaurantUser(UserRegistrationEntity combinedRegistrationEntity) {
		if (combinedRegistrationEntity == null || combinedRegistrationEntity.getRestaurantDetails() == null
				|| combinedRegistrationEntity.getRestaurantUser() == null ) {
			throw new BadRequestException("Bad Request - Missing fields");
		} else {
			if (restaurantUserRepository.getUserByEmailId(combinedRegistrationEntity.getRestaurantUser().getEmail()) != null) {
				restaurantUserRepository.registerRestaurantUserAndRestaurant(combinedRegistrationEntity);
				ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), null, "");
				return new ResponseEntity<ResponseEntityClass>(response, HttpStatus.OK);
			} else
				throw new AlreadyExistsException("User exists");
		}
	}
	
	@Override
	public ResponseEntity<ResponseEntityClass> loginUser(LoginCredentials loginCredentials) {
		if(loginCredentials.getLoginId().isEmpty() || loginCredentials.getPassword().isEmpty())
			throw new BadRequestException("Bad Request -- Missing fields");
		
		UserRegistrationEntity combinedUserRegistrationEntity = restaurantUserRepository.checkIfValidUser(loginCredentials);
		List<UserRegistrationEntity> list = new ArrayList<UserRegistrationEntity>();
		
		if(combinedUserRegistrationEntity.getRestaurantUser() == null)
			throw new NotFoundException("Invalid Username or Password, Try again!");
		
		list.add(combinedUserRegistrationEntity);
		ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), list, "");
		return new ResponseEntity<ResponseEntityClass>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseEntityClass> createRestaurantUser(RestaurantStaff restaurantStaff) {
		if (restaurantStaff.getRole().isEmpty() || restaurantStaff.getEmail().isEmpty()
				|| restaurantStaff.getUsername().isEmpty() || restaurantStaff.getRestaurantId().isEmpty()
				|| restaurantStaff.getPhoneNumber().isEmpty())
			throw new BadRequestException("Missing Fields -- Bad Request");
		if (restaurantUserRepository.getUserByEmailId(restaurantStaff.getEmail()).size() == 0) {
			restaurantUserRepository.createRestaurantUser(restaurantStaff);
			ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), null, "");
			return new ResponseEntity<ResponseEntityClass>(response, HttpStatus.OK);
		} else
			throw new AlreadyExistsException("User exists");

	}

	@Override
	public ResponseEntity<ResponseEntityClass> verifyRestaurantUser(LoginCredentials loginCredentials) {
		if(loginCredentials.getPassword().isEmpty() || loginCredentials.getLoginId().isEmpty())
			throw new BadRequestException("Bad Request -- Missing fields");
		RestaurantUser restaurantUser = restaurantUserRepository.getUserByEmailId(loginCredentials.getLoginId()).get(0);
		restaurantUser.setPassword(loginCredentials.getPassword());
		if( restaurantUser != null) {
			restaurantUserRepository.updateRestaurantUser(restaurantUser.getId(), restaurantUser);
			ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), null, "");
			return new ResponseEntity<ResponseEntityClass>(response, HttpStatus.OK);
		}
		else
			throw new NotFoundException("User does not exists");
		
	}

}

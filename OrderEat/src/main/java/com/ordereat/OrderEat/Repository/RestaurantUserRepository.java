package com.ordereat.OrderEat.Repository;

import java.util.List;

import com.ordereat.OrderEat.Entity.UserRegistrationEntity;
import com.ordereat.OrderEat.Entity.LoginCredentials;
import com.ordereat.OrderEat.Entity.RestaurantDetails;
import com.ordereat.OrderEat.Entity.RestaurantUser;
import com.ordereat.OrderEat.Entity.RestaurantStaff;

public interface RestaurantUserRepository {

	RestaurantUser findRestaurantUserById(Long id);
	
	void registerAdminAndRestaurant(UserRegistrationEntity combinedRegistrationEntity);

	List<RestaurantUser> findAllRestaurantUser();

	List<RestaurantUser> getUserDetailsFromRestaurantId(Long id);

	boolean findIfRestaurantExists(Long id);
	
	void updateRestaurantDetails(Long restaurantId, RestaurantDetails restaurantDetails);

	void updateRestaurantUser(Long userId, RestaurantUser restaurantUser);

	boolean findIfRestaurantUserExists(Long id);

	Object[] getRestaurantDetailsFromUser(Long user_id);
	
	Object getUserByUserName(String username);

	UserRegistrationEntity checkIfValidUser();

	RestaurantUser isValidCredentials(String emailId, String password);

	List<RestaurantUser> getUserByEmailId(String username);

	void registerRestaurantStaffOrManager(RestaurantStaff restaurantStaff);

	//RestaurantUser isValidUserNameCredentials(String username, String password);
}

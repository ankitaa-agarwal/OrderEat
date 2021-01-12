package com.ordereat.OrderEat.Repository;

import java.util.List;

import com.ordereat.OrderEat.Entity.CombinedRegistrationEntity;
import com.ordereat.OrderEat.Entity.RestaurantDetails;
import com.ordereat.OrderEat.Entity.RestaurantOwner;

public interface RestaurantOwnerRepository {

	RestaurantOwner findRestaurantOwnerById(Long id);
	
	void registerRestaurantOwnerAndRestaurant(CombinedRegistrationEntity combinedRegistrationEntity);

	List<RestaurantOwner> findAllRestaurantOwner();

	boolean findIfExists(CombinedRegistrationEntity combinedEntity);

	RestaurantOwner getOwnerFromRestaurantId(Long id);

	boolean findIfRestaurantExists(Long id);
	
	RestaurantDetails updateRestaurantDetails(RestaurantDetails restaurantDetails);

	RestaurantOwner updateRestaurantOwner(RestaurantOwner restaurantOwner);

	boolean findIfRestaurantOwnerExists(Long id);

	RestaurantDetails getOwnerAndRestaurantDetails(Long owner_id);
	
	RestaurantOwner getOwnerByUserName(String username);
}

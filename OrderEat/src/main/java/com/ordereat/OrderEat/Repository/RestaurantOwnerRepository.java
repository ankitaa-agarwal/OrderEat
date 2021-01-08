package com.ordereat.OrderEat.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.ordereat.OrderEat.Entity.CombinedRegistrationEntity;
import com.ordereat.OrderEat.Entity.RestaurantOwner;

@Repository
@Transactional
public interface RestaurantOwnerRepository {

	RestaurantOwner findRestaurantOwnerById(Long id);
	
	void registerRestaurantOwnerAndRestaurant(CombinedRegistrationEntity combinedRegistrationEntity);

	List<RestaurantOwner> findAllRestaurantOwner();

	boolean findIfExists(CombinedRegistrationEntity combinedEntity);
}
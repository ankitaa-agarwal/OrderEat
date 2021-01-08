package com.ordereat.OrderEat.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordereat.OrderEat.Entity.CombinedRegistrationEntity;
import com.ordereat.OrderEat.Repository.RestaurantOwnerRepository;

@Service
public class RestaurantOwnerServiceImpl implements RestaurantOwnerService {

	@Autowired
	RestaurantOwnerRepository restaurantOwnerRepository;
	@Override
	public void registerRestaurantOwner(CombinedRegistrationEntity combinedRegistrationEntity) {
		// TODO Auto-generated method stub
		restaurantOwnerRepository.registerRestaurantOwnerAndRestaurant(combinedRegistrationEntity);
	}
	@Override
	public boolean findIfExists(CombinedRegistrationEntity combinedEntity) {
		// TODO Auto-generated method stub
		return restaurantOwnerRepository.findIfExists(combinedEntity);
	}

}

package com.ordereat.OrderEat.Service;

import com.ordereat.OrderEat.Entity.CombinedRegistrationEntity;

public interface RestaurantOwnerService {

	public void registerRestaurantOwner(CombinedRegistrationEntity combinedEntity);

	public boolean findIfExists(CombinedRegistrationEntity combinedEntity);

}

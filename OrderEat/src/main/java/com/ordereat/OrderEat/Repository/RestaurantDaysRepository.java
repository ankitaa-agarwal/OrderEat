package com.ordereat.OrderEat.Repository;

import java.util.List;

import com.ordereat.OrderEat.Entity.RestaurantDays;
import com.ordereat.OrderEat.Entity.RestaurantDaysRequest;

public interface RestaurantDaysRepository {

	public void updateRestaurantDays(RestaurantDaysRequest restaurantDaysRequest);

	RestaurantDays findIfDayExistsById(Long dayId);

	List<RestaurantDays> getDaysFromRestaurant(Long restaurantId);
}

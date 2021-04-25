package com.ordereat.OrderEat.Service;

import org.springframework.http.ResponseEntity;

import com.ordereat.OrderEat.Entity.ResponseEntityClass;
import com.ordereat.OrderEat.Entity.RestaurantDaysRequest;

public interface RestaurantDaysService {

	ResponseEntity<ResponseEntityClass> updateRestaurantDay(RestaurantDaysRequest restaurantDay);
}

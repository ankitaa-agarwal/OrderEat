package com.ordereat.OrderEat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ordereat.OrderEat.Entity.ResponseEntityClass;
import com.ordereat.OrderEat.Entity.RestaurantDaysRequest;
import com.ordereat.OrderEat.Service.RestaurantDaysService;

@RestController
@RequestMapping("/roe/day")
public class RestaurantDaysController {

	@Autowired
	RestaurantDaysService restaurantDaysService;

	@PutMapping("/updateDay")
	public ResponseEntity<ResponseEntityClass> updateRestaurantDay(@RequestBody RestaurantDaysRequest restaurantDay) {
		return restaurantDaysService.updateRestaurantDay(restaurantDay);
	}

}

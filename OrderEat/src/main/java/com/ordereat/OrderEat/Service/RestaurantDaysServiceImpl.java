package com.ordereat.OrderEat.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ordereat.OrderEat.Entity.ResponseEntityClass;
import com.ordereat.OrderEat.Entity.RestaurantDaysRequest;
import com.ordereat.OrderEat.Exception.BadRequestException;
import com.ordereat.OrderEat.Repository.RestaurantDaysRepository;
import com.ordereat.OrderEat.Service.RestaurantUserServiceImpl.STATUS;

@Service
public class RestaurantDaysServiceImpl implements RestaurantDaysService {

	@Autowired
	RestaurantDaysRepository restaurantDaysRepository;
	
	@Override
	public ResponseEntity<ResponseEntityClass> updateRestaurantDay(RestaurantDaysRequest restaurantDaysRequest) {
		if(restaurantDaysRequest.getId().isEmpty() || restaurantDaysRequest.getEndTime().isEmpty() || restaurantDaysRequest.getStartTime().isEmpty()
				|| restaurantDaysRequest.getSeats().isEmpty())
			throw new BadRequestException("Bad Request - Missing Fields, check the request again");

		restaurantDaysRepository.updateRestaurantDays(restaurantDaysRequest);
		ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), null, "");
		return new ResponseEntity<ResponseEntityClass>(response, HttpStatus.OK);
	}

}

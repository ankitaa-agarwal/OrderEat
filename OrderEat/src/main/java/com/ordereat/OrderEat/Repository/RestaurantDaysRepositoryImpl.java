package com.ordereat.OrderEat.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ordereat.OrderEat.Entity.RestaurantDays;
import com.ordereat.OrderEat.Entity.RestaurantDaysRequest;
import com.ordereat.OrderEat.Exception.NotFoundException;

@Repository
@Transactional
public class RestaurantDaysRepositoryImpl implements RestaurantDaysRepository {

	@Autowired
	EntityManager entityManager;
	
	@Override
	public RestaurantDays findIfDayExistsById(Long dayId) {
		RestaurantDays restaurantDay = entityManager.find(RestaurantDays.class, dayId);
		return restaurantDay;
	}
	

	@Override
	public List<RestaurantDays> getDaysFromRestaurant(Long restaurantId){
		String query = "Select days from RestaurantDays days where restaurant_id = :restaurantId";
		TypedQuery<RestaurantDays> typedQuery = entityManager.createQuery(query, RestaurantDays.class);
		typedQuery.setParameter("restaurantId", restaurantId);
		List<RestaurantDays> restaurantDaysList = typedQuery.getResultList();
		return restaurantDaysList;
	}
	
	@Override
	public void updateRestaurantDays(RestaurantDaysRequest restaurantDaysRequest) {
		RestaurantDays restaurantDay = findIfDayExistsById(Long.parseLong(restaurantDaysRequest.getId()));
		if(restaurantDay != null) {
			restaurantDay.setEnd_time(Integer.parseInt(restaurantDaysRequest.getEndTime()));
			restaurantDay.setStart_time(Integer.parseInt(restaurantDaysRequest.getStartTime()));
			restaurantDay.setSeats(Integer.parseInt(restaurantDaysRequest.getSeats()));
			restaurantDay.setOpen(restaurantDaysRequest.isOpen());
		}
		else
			throw new NotFoundException("Day does not Exists");
	}

}

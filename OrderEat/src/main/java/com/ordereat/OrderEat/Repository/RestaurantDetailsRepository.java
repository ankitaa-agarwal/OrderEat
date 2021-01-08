package com.ordereat.OrderEat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ordereat.OrderEat.Entity.RestaurantDetails;

@Repository
public interface RestaurantDetailsRepository extends JpaRepository<RestaurantDetails, Long>{

	@Query("SELECT details FROM RestaurantDetails details where details.id = :id ")
	RestaurantDetails findRestaurantOwnerById(@Param("id")Long id);
}

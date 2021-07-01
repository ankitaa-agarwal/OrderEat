package com.ordereat.OrderEat.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ordereat.OrderEat.Entity.RestaurantDetails;

import com.ordereat.OrderEat.Entity.Role;


public interface RoleRepository{

	
	Role getRoleById(Long id);
}

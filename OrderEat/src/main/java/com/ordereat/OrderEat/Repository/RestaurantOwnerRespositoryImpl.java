package com.ordereat.OrderEat.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.ordereat.OrderEat.Entity.CombinedRegistrationEntity;
import com.ordereat.OrderEat.Entity.RestaurantDetails;
import com.ordereat.OrderEat.Entity.RestaurantOwner;

public class RestaurantOwnerRespositoryImpl implements RestaurantOwnerRepository {

	@Autowired
	EntityManager entityManager;
	
	@Override
	public RestaurantOwner findRestaurantOwnerById(Long id) {
		TypedQuery<RestaurantOwner> typedQuery
	      = entityManager.createQuery("SELECT owner FROM RestaurantOwner owner where owner.id = :id", RestaurantOwner.class);
	    typedQuery.setParameter("id", id);
	    return typedQuery.getSingleResult();
	}

	@Override
	public void registerRestaurantOwnerAndRestaurant(CombinedRegistrationEntity combinedRegistrationEntity) {
		// TODO Auto-generated method stub
		RestaurantDetails restaurantDetails = new RestaurantDetails(combinedRegistrationEntity.getRestaurantDetails().getRestaurantName());
		RestaurantOwner restaurantOwner = new RestaurantOwner();
		restaurantOwner.setEmail(combinedRegistrationEntity.getRestaurantOwner().getEmail());
		restaurantOwner.setFullName(combinedRegistrationEntity.getRestaurantOwner().getFullName());
		restaurantOwner.setPassword(combinedRegistrationEntity.getRestaurantOwner().getPassword());
		restaurantOwner.setPhoneNumber(combinedRegistrationEntity.getRestaurantOwner().getPhoneNumber());
		restaurantOwner.setUsername(combinedRegistrationEntity.getRestaurantOwner().getUsername());
		
		restaurantOwner.setRestaurantDetails(restaurantDetails);
		restaurantDetails.setRestaurantOwner(restaurantOwner);
		
		entityManager.persist(restaurantOwner);
		entityManager.flush();
	}

	@Override
	public List<RestaurantOwner> findAllRestaurantOwner() {
		// TODO Auto-generated method stub
		TypedQuery<RestaurantOwner> typedQuery
	      = entityManager.createQuery("SELECT owner FROM RestaurantOwner owner", RestaurantOwner.class);
	    return typedQuery.getResultList();
	}

	@Override
	public boolean findIfExists(CombinedRegistrationEntity combinedEntity) {
		// TODO Auto-generated method stub
		if(entityManager.find(RestaurantDetails.class, combinedEntity.getRestaurantDetails().getId()) != null)
			return true;
		else if(entityManager.find(RestaurantOwner.class, combinedEntity.getRestaurantOwner().getId()) != null)
			return true;
		return false;
	}
}
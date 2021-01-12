package com.ordereat.OrderEat.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.ordereat.OrderEat.Entity.CombinedRegistrationEntity;
import com.ordereat.OrderEat.Entity.RestaurantDetails;
import com.ordereat.OrderEat.Entity.RestaurantOwner;
import com.ordereat.OrderEat.Entity.Role;

@Repository
@Transactional
public class RestaurantOwnerRespositoryImpl implements RestaurantOwnerRepository {

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
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
		Role role = new Role("OWNER");
		RestaurantDetails restaurantDetails = new RestaurantDetails(combinedRegistrationEntity.getRestaurantDetails().getRestaurantName());
		RestaurantOwner restaurantOwner = new RestaurantOwner();
		restaurantOwner.setEmail(combinedRegistrationEntity.getRestaurantOwner().getEmail());
		restaurantOwner.setFullName(combinedRegistrationEntity.getRestaurantOwner().getFullName());
		restaurantOwner.setPassword(bCryptPasswordEncoder.encode(combinedRegistrationEntity.getRestaurantOwner().getPassword()));
		restaurantOwner.setPhoneNumber(combinedRegistrationEntity.getRestaurantOwner().getPhoneNumber());
		restaurantOwner.setUsername(combinedRegistrationEntity.getRestaurantOwner().getUsername());
		restaurantOwner.addRole(role);
		role.setRestaurantOwner(restaurantOwner);
		restaurantOwner.setRestaurantDetails(restaurantDetails);
		restaurantDetails.setRestaurantOwner(restaurantOwner);
		entityManager.persist(role);
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
		String restaurantOwnerQuery = "Select user from RestaurantOwner user where email = :email";
		TypedQuery<RestaurantOwner> typedQuery = entityManager.createQuery(restaurantOwnerQuery, RestaurantOwner.class);
		typedQuery.setParameter("email", combinedEntity.getRestaurantOwner().getEmail());
		if(typedQuery.getResultList().size() > 0)
			return true;
		return false;
	}

	@Override
	public RestaurantOwner getOwnerFromRestaurantId(Long restaurant_id) {
		String query = "Select ownerDetails from RestaurantOwner ownerDetails where restaurant_id = : restaurant_id";
		TypedQuery<RestaurantOwner> typedQuery
	      = entityManager.createQuery(query, RestaurantOwner.class);
		typedQuery.setParameter("restaurant_id", restaurant_id);
	    return typedQuery.getSingleResult();
	}
	
	@Override
	public boolean findIfRestaurantExists(Long id) {
		String query = "Select restaurant From RestaurantDetails restaurant where id = :id";
		TypedQuery<RestaurantDetails> typedQuery = entityManager.createQuery(query, RestaurantDetails.class);
		typedQuery.setParameter("id", id);
		if(typedQuery.getResultList().size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public RestaurantDetails updateRestaurantDetails(RestaurantDetails restaurantDetails) {
		RestaurantOwner owner = getOwnerFromRestaurantId(restaurantDetails.getId());
		owner.setRestaurantDetails(restaurantDetails);
		restaurantDetails.setRestaurantOwner(owner);
		entityManager.merge(owner);
		entityManager.flush();
		return entityManager.find(RestaurantDetails.class, restaurantDetails.getId());
	}
	
	@Override
	public boolean findIfRestaurantOwnerExists(Long id) {
		String query = "Select owner From RestaurantOwner owner where id = :id";
		TypedQuery<RestaurantOwner> typedQuery = entityManager.createQuery(query, RestaurantOwner.class);
		typedQuery.setParameter("id", id);
		if(typedQuery.getResultList().size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public RestaurantOwner updateRestaurantOwner(RestaurantOwner restaurantOwner) {
		RestaurantDetails restaurantDetails = restaurantOwner.getRestaurantDetails();
		restaurantDetails.setRestaurantOwner(restaurantOwner);
		restaurantOwner.setRestaurantDetails(restaurantDetails);
		entityManager.merge(restaurantOwner);
		entityManager.flush();
		return entityManager.find(RestaurantOwner.class, restaurantOwner.getId());
	}

	@Override
	public RestaurantDetails getOwnerAndRestaurantDetails(Long owner_id) {
		String query = "Select owner.restaurantDetails from RestaurantOwner owner where owner.id = : id";
		TypedQuery<RestaurantDetails> typedQuery
	      = entityManager.createQuery(query, RestaurantDetails.class);
		typedQuery.setParameter("id", owner_id);
	    RestaurantDetails restaurantDetails = typedQuery.getSingleResult();
	    
	    return restaurantDetails;
	}

	@Override
	public RestaurantOwner getOwnerByUserName(String username) {
		String query = "Select owner from RestaurantOwner owner where owner.username = :username";
		TypedQuery<RestaurantOwner> typedQuery
	      = entityManager.createQuery(query, RestaurantOwner.class);
		typedQuery.setParameter("username", username);
		return typedQuery.getSingleResult();
	}
}

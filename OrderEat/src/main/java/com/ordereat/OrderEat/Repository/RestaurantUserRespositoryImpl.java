package com.ordereat.OrderEat.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.ordereat.OrderEat.Entity.CustomerEntity;
import com.ordereat.OrderEat.Entity.LoginCredentials;
import com.ordereat.OrderEat.Entity.OrderListForCustomer;
import com.ordereat.OrderEat.Entity.RestaurantDays;
import com.ordereat.OrderEat.Entity.RestaurantDetails;
import com.ordereat.OrderEat.Entity.RestaurantStaff;
import com.ordereat.OrderEat.Entity.RestaurantUser;
import com.ordereat.OrderEat.Entity.Role;
import com.ordereat.OrderEat.Entity.UserRegistrationEntity;
import com.ordereat.OrderEat.Exception.NotFoundException;
import com.ordereat.OrderEat.SpringSecurity.CustomerUserDetails;
import com.ordereat.OrderEat.SpringSecurity.RestaurantUserDetails;

@Repository
@Transactional
public class RestaurantUserRespositoryImpl implements RestaurantUserRepository {

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	  enum DAY_OF_THE_WEEK { MONDAY("Monday"), TUESDAY("Tuesday"),
	  WEDNESDAY("Wednesday"), THURSDAY("Thursday"), FRIDAY("Friday"),
	  SATURDAY("Saturday"), SUNDAY("Sunday");
	  
	  public final String status;
	  
	  private DAY_OF_THE_WEEK(String status) { this.status = status; } }
	 
	public static Map<Integer, String> daysMap = new HashMap<>();
	
	
	@Autowired
	EntityManager entityManager;

	@Autowired
	RestaurantDetailsRepository restaurantDetailsRepository;
	
	@Autowired
	RoleRepository roleRepository;

	List<RestaurantDays> restaurantDays = new ArrayList<RestaurantDays>();
	
	public void populateDaysForRestaurant(RestaurantDetails restaurantDetails) {
		
		daysMap.put(1, DAY_OF_THE_WEEK.MONDAY.toString());
		daysMap.put(2, DAY_OF_THE_WEEK.TUESDAY.toString());
		daysMap.put(3, DAY_OF_THE_WEEK.WEDNESDAY.toString());
		daysMap.put(4, DAY_OF_THE_WEEK.THURSDAY.toString());
		daysMap.put(5, DAY_OF_THE_WEEK.FRIDAY.toString());
		daysMap.put(6, DAY_OF_THE_WEEK.SATURDAY.toString());
		daysMap.put(7, DAY_OF_THE_WEEK.SUNDAY.toString());
		
		for(int i=1; i<=7; i++) {
			RestaurantDays restaurantDay = new RestaurantDays();
			restaurantDay.setDayNumber(i);
			restaurantDay.setDayName(daysMap.get(i));
			restaurantDay.setRestaurantDetails(restaurantDetails);
			entityManager.persist(restaurantDay);
			restaurantDays.add(restaurantDay);
		}
	}
	/**
	 * Find Restaurant OWner with give ID
	 */
	@Override
	public RestaurantUser findRestaurantUserById(Long id) {
		TypedQuery<RestaurantUser> typedQuery = entityManager
				.createQuery("SELECT user FROM RestaurantUser user where user.id = :id", RestaurantUser.class);
		typedQuery.setParameter("id", id);
		return typedQuery.getSingleResult();
	}

	/**
	 * Register Restaurant User or a Restaurant User depending on the Role passed
	 * in the Combined Entity
	 */
	@Override
	public void registerAdminAndRestaurant(UserRegistrationEntity userRegistrationEntity) {
		Role role = roleRepository.getRoleById(1L);
		
		RestaurantUser restaurantUser = new RestaurantUser();
		restaurantUser.setEmail(userRegistrationEntity.getRestaurantUser().getEmail());
		restaurantUser.setUsername(userRegistrationEntity.getRestaurantUser().getUsername());
		restaurantUser.setFullName(userRegistrationEntity.getRestaurantUser().getFullName());
		restaurantUser.setPassword(bCryptPasswordEncoder.encode(userRegistrationEntity.getRestaurantUser().getPassword()));
		restaurantUser.setPhoneNumber(userRegistrationEntity.getRestaurantUser().getPhoneNumber());
		restaurantUser.setRoleId(role);
		
		RestaurantDetails restaurantDetails = new RestaurantDetails();
		restaurantDetails.setRestaurantName(userRegistrationEntity.getRestaurantDetails().getRestaurantName());
		restaurantDetails.setCity(userRegistrationEntity.getRestaurantDetails().getCity());
		restaurantDetails.addRestaurantUser(restaurantUser);
		restaurantUser.setRestaurantDetails(restaurantDetails);
		populateDaysForRestaurant(restaurantDetails);
		restaurantDetails.setRestaurantDaysList(restaurantDays);
		entityManager.persist(restaurantUser);
		entityManager.flush();
		audit(restaurantUser.getId(),restaurantDetails.getId());
	}
	
	public void audit(Long userId,Long resId) {
		RestaurantUser admin = entityManager.find(RestaurantUser.class, userId);
		admin.setCreatedBy(userId.toString());
		admin.setCreatedOn(new Date());
		RestaurantDetails details = entityManager.find(RestaurantDetails.class, resId);
		details.setCreatedBy(userId.toString());
		details.setCreatedOn(new Date());
	}
	/**
	 * Create the Restaurant User with Roles Like Staff, Employee
	 */
	@Override
	public void registerRestaurantStaffOrManager(RestaurantStaff restaurantStaff) {
		Role role = roleRepository.getRoleById(Long.parseLong(restaurantStaff.getRole()));

		RestaurantUser restaurantUser = new RestaurantUser();
		restaurantUser.setEmail(restaurantStaff.getEmail());
		restaurantUser.setPhoneNumber(restaurantStaff.getPhoneNumber());
		restaurantUser.setUsername(restaurantStaff.getUsername());
		restaurantUser.setFullName(restaurantStaff.getFullName());
		restaurantUser.setPassword("");
		restaurantUser.setRoleId(role);

		RestaurantDetails restaurantDetails = entityManager.find(RestaurantDetails.class,
				Long.parseLong(restaurantStaff.getRestaurantId()));
		restaurantDetails.addRestaurantUser(restaurantUser);

		restaurantUser.setRestaurantDetails(restaurantDetails);

		entityManager.persist(restaurantUser);
	}
	
	/**
	 * Get List of all the Restaurant Users/Users
	 */
	@Override
	public List<RestaurantUser> findAllRestaurantUser() {
		TypedQuery<RestaurantUser> typedQuery = entityManager.createQuery("SELECT user FROM RestaurantUser user",
				RestaurantUser.class);
		return typedQuery.getResultList();
	}

	/**
	 * Extract User Details from the given Restaurant Id
	 */
	@Override
	public List<RestaurantUser> getUserDetailsFromRestaurantId(Long restaurant_id) {
		String query = "Select userDetails from RestaurantUser userDetails where restaurant_id = : restaurant_id";
		TypedQuery<RestaurantUser> typedQuery = entityManager.createQuery(query, RestaurantUser.class);
		typedQuery.setParameter("restaurant_id", restaurant_id);
		return typedQuery.getResultList();
	}

	/**
	 * Check if Restaurant with the given ID exists or not
	 */
	@Override
	public boolean findIfRestaurantExists(Long id) {
		String query = "Select restaurant From RestaurantDetails restaurant where id = :id";
		TypedQuery<RestaurantDetails> typedQuery = entityManager.createQuery(query, RestaurantDetails.class);
		typedQuery.setParameter("id", id);
		if (typedQuery.getResultList().size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Update the Restaurant Details with the given Details<br>
	 * Return the Updated Restaurant Details Object
	 */
	@Override
	public void updateRestaurantDetails(Long restaurantId, RestaurantDetails restaurantDetails) {
		RestaurantDetails currentRestaurantDetails = entityManager.find(RestaurantDetails.class, restaurantId);
		currentRestaurantDetails.setRestaurantName(restaurantDetails.getRestaurantName());
	}

	/**
	 * Checks if the Restaurant User/User Exists with the give ID
	 */
	@Override
	public boolean findIfRestaurantUserExists(Long id) {
		String query = "Select user From RestaurantUser user where id = :id";
		TypedQuery<RestaurantUser> typedQuery = entityManager.createQuery(query, RestaurantUser.class);
		typedQuery.setParameter("id", id);
		if (typedQuery.getResultList().size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Update the Restaurant User/User details with the given Details<br>
	 * Return the updated Restaurant User/User Object
	 */
	@Override
	public void updateRestaurantUser(Long userId, RestaurantUser restaurantUser) {
		RestaurantUser currentUser = entityManager.find(RestaurantUser.class, userId);
		currentUser.setFullName(restaurantUser.getFullName());
		currentUser.setEmail(restaurantUser.getEmail());
		currentUser.setUsername(restaurantUser.getUsername());
		currentUser.setPhoneNumber(restaurantUser.getPhoneNumber());
		currentUser.setPassword(bCryptPasswordEncoder.encode(restaurantUser.getPassword()));
		/*
		 * RestaurantDetails restaurantDetails = currentUser.getRestaurantDetails();
		 * restaurantUser.setRestaurantDetails(restaurantDetails);
		 */
	}

	/**
	 * Extract Restaurant details from the User ID
	 */
	@Override
	public Object[] getRestaurantDetailsFromUser(Long user_id) {
		String query = "select r.id, r.name, r.created_by, r.created_on  from restaurant_users u left join restaurant_details r on r.id = u.restaurant_id where u.id = :id";
		Query typedQuery = entityManager.createNativeQuery(query);
		typedQuery.setParameter("id", user_id);
		Object[] details = (Object[]) typedQuery.getSingleResult();
		return details;
	}

	/**
	 * Get Restaurant User/User from the Username
	 */
	@Override
	public Object getUserByUserName(String username) {
		String query = "Select user from RestaurantUser user where user.username = :username";
		Query typedQuery = entityManager.createQuery(query);
		typedQuery.setParameter("username", username);
		List<Object> list = typedQuery.getResultList();
		return list.get(0);
	}

	@Override
	public List<RestaurantUser> getUserByEmailId(String username) {
		String query = "Select user from RestaurantUser user where user.email = :email";
		TypedQuery<RestaurantUser> typedQuery = entityManager.createQuery(query, RestaurantUser.class);
		typedQuery.setParameter("email", username);
		return typedQuery.getResultList();
	}

	/*
	 * public RestaurantUser isValidCredentials(String loginId, String password) {
	 * List<RestaurantUser> list = getUserByEmailId(loginId); RestaurantUser user =
	 * new RestaurantUser(); if (list.isEmpty()) { user =
	 * getUserByUserName(loginId); if (bCryptPasswordEncoder.matches(password,
	 * user.getPassword())) return user; } else if
	 * (bCryptPasswordEncoder.matches(password, list.get(0).getPassword())) { user =
	 * list.get(0); return user; } else throw new
	 * NotFoundException("User not registered"); return user; }
	 */

	@Override
	public UserRegistrationEntity checkIfValidUser() {
		/*
		 * String password = loginCredentials.getPassword(); RestaurantUser validUser =
		 * new RestaurantUser(); String loginId = loginCredentials.getLoginId();
		 * validUser = isValidCredentials(loginId, password);
		 * 
		 * if (!validUser.getPassword().isEmpty()) {
		 * 
		 * Object[] restaurantDetails = getRestaurantDetailsFromUser(validUser.getId());
		 * Long longId = ((BigInteger) restaurantDetails[0]).longValue();
		 * RestaurantDetails resDetails = new RestaurantDetails();
		 * resDetails.setId(longId);
		 * resDetails.setRestaurantName(restaurantDetails[1].toString());
		 * resDetails.addRestaurantUser(validUser); entityManager.merge(validUser);
		 * return new UserRegistrationEntity(resDetails, validUser,
		 * validUser.getRoleId().getName()); }
		 */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		RestaurantUser customer = ((RestaurantUserDetails) auth.getPrincipal()).getLoggedInUser();
		UserRegistrationEntity user = new UserRegistrationEntity();
		user.setRestaurantUser(customer);
		Object[] restaurantDetails = getRestaurantDetailsFromUser(customer.getId());
		Long longId = ((BigInteger) restaurantDetails[0]).longValue();
		RestaurantDetails resDetails = new RestaurantDetails();
		resDetails.setId(longId);
		resDetails.setRestaurantName(restaurantDetails[1].toString());
		resDetails.setCreatedBy(restaurantDetails[2].toString());
		resDetails.setCreatedOn((Date) restaurantDetails[3]);
		resDetails.addRestaurantUser(customer);
		user.setRestaurantDetails(resDetails);
		user.setRole(customer.getRoleId().getName());
		return user;
	}

	public boolean isEmptyOrNull(String string) {
		if (string == null || string.isEmpty())
			return true;
		return false;
	}
	@Override
	public RestaurantUser isValidCredentials(String emailId, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

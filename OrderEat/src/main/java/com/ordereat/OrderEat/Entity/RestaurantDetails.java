package com.ordereat.OrderEat.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.ordereat.OrderEat.Auditable;

@Entity
@Table(name = "restaurant_details")
public class RestaurantDetails extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false, length = 20)
	private String restaurantName;

	@Column(name = "city", nullable = true)
	private String city;

	@JsonManagedReference(value = "user")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "restaurantDetails")
	private List<RestaurantUser> restaurantUsers = new ArrayList<RestaurantUser>();

	@JsonManagedReference(value = "product")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "restaurantDetails")
	private List<RestaurantProduct> restaurantProducts = new ArrayList<RestaurantProduct>();

	@JsonManagedReference(value = "days")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "restaurantDetails")
	private List<RestaurantDays> restaurantDays = new ArrayList<RestaurantDays>();

	@JsonManagedReference(value = "order")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "restaurant")
	private List<Order> restaurantOrders = new ArrayList<Order>();

	@JsonManagedReference("branch")
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval =
	true, mappedBy = "restaurantDetails") 
	private List<RestaurantBranch> restaurantBranchList = new ArrayList<RestaurantBranch>();
	 
	public RestaurantDetails() {};

	public RestaurantDetails(String restaurantName, String city) {
		super();
		this.restaurantName = restaurantName;
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public List<RestaurantUser> getRestaurantUsers() {
		return restaurantUsers;
	}

	public void addRestaurantUser(RestaurantUser user) {
		this.restaurantUsers.add(user);
		user.setRestaurantDetails(this);
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public List<RestaurantProduct> getRestaurantProducts() {
		return restaurantProducts;
	}

	public void addRestaurantProduct(RestaurantProduct product) {
		this.restaurantProducts.add(product);
		product.setRestaurantDetails(this);
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public List<RestaurantDays> getRestaurantDays() {
		return restaurantDays;
	}

	public void addRestaurantDay(RestaurantDays days) {
		this.restaurantDays.add(days);
		days.setRestaurantDetails(this);
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public List<Order> getRestauarntOrders() {
		return restaurantOrders;
	}

	public void addRestaurantOrder(Order order) {
		this.restaurantOrders.add(order);
		order.setRestaurant(this);
	}

	public void setRestaurantDaysList(List<RestaurantDays> list) {
		this.restaurantDays = list;
	}
	
	public void addRestaurantBranch(RestaurantBranch restaurantBranch) {
		this.restaurantBranchList.add(restaurantBranch);
		restaurantBranch.setRestaurantDetails(this);
	}

	@JsonProperty(access = Access.WRITE_ONLY)
	public List<RestaurantBranch> getRestauarntBranchList() {
		return restaurantBranchList;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}

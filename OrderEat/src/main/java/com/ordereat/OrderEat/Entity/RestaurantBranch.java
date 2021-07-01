package com.ordereat.OrderEat.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "restaurant_branch")
public class RestaurantBranch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonBackReference("branch")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name="restaurant_id")//Owner of relation, first insertUser then
	private RestaurantDetails restaurantDetails;

	@Column(name = "address_one")
	private String address_one;

	@Column(name = "address_two")
	private String address_two;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "pincode")
	private Long pincode;
	/*
	 * public RestaurantDetails getRestaurant() { return restaurantDetails; }
	 * 
	 * public void setRestaurant(RestaurantDetails restaurant) {
	 * this.restaurantDetails = restaurant; }
	 */

	public String getAddress_one() {
		return address_one;
	}

	public void setAddress_one(String address_one) {
		this.address_one = address_one;
	}

	public String getAddress_two() {
		return address_two;
	}

	public void setAddress_two(String address_two) {
		this.address_two = address_two;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	public Long getId() {
		return id;
	}

	public RestaurantDetails getRestaurantDetails() {
		return restaurantDetails;
	}

	public void setRestaurantDetails(RestaurantDetails restaurantDetails) {
		this.restaurantDetails = restaurantDetails;
	}

}

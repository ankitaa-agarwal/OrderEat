package com.ordereat.OrderEat.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="restaurant_details")
public class RestaurantDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@Column(name = "name", nullable = false, length = 20)
	private String restaurantName;
	
	@JsonManagedReference
	@OneToOne(fetch = FetchType.LAZY,
	            cascade =  CascadeType.ALL, mappedBy = "restaurantDetails")
	private RestaurantOwner restaurantOwner;
	
	public RestaurantDetails() {};
	
	public RestaurantDetails(String restaurantName) {
		super();
		this.restaurantName = restaurantName;
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

	public RestaurantOwner getRestaurantOwner() {
		return restaurantOwner;
	}

	public void setRestaurantOwner(RestaurantOwner restaurantOwner) {
		this.restaurantOwner = restaurantOwner;
	}
	
}

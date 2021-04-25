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
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "restaurant_days")
public class RestaurantDays {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonBackReference(value = "days")
	@ManyToOne(fetch = FetchType.LAZY,cascade =  CascadeType.ALL)
	@JoinColumn(name="restaurant_id")
	private RestaurantDetails restaurantDetails;
	
	@Column(nullable = false)
	private int dayNumber;
	
	@Column(name = "dayName", nullable = false)
	private String day;
	
	@Column(nullable = false)
	private int start_time;
	
	@Column(nullable = false)
	private int end_time;
	
	@Column(nullable = false)
	private int seats;
	
	@Column(nullable = false)
	private boolean isOpen;
	
	public RestaurantDays() {}
	
	public RestaurantDetails getRestaurantDetails() {
		return restaurantDetails;
	}

	public void setRestaurantDetails(RestaurantDetails restaurantDetails) {
		this.restaurantDetails = restaurantDetails;
	}

	public int getStart_time() {
		return start_time;
	}

	public void setStart_time(int start_time) {
		this.start_time = start_time;
	}

	public int getEnd_time() {
		return end_time;
	}

	public void setEnd_time(int end_time) {
		this.end_time = end_time;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public Long getId() {
		return id;
	}
	
	@JsonProperty(access = Access.WRITE_ONLY)
	public int getDayNumber() {
		return dayNumber;
	}

	public void setDayNumber(int dayNumber) {
		this.dayNumber = dayNumber;
	}

	public String getDayName() {
		return day;
	}

	public void setDayName(String dayName) {
		this.day = dayName;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	
}

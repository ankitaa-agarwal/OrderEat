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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "restaurant_products")
public class RestaurantProduct {

	@Transient
	private int quantity;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@JsonBackReference(value = "product")
	@ManyToOne(fetch = FetchType.LAZY,cascade =  CascadeType.ALL)
	@JoinColumn(name="restaurant_id")//Owner of relation, first insertUser then restaurant details
	private RestaurantDetails restaurantDetails;
	
	@Column(name = "name", nullable = false)
	private String productName;
	
	@Column(name = "description", nullable = false)
	private String productDescription;
	
	@Column(name = "price", nullable = false)
	private Double productPrice;
	
	@Column(name = "prepTime", nullable = false)
	private Integer productPrepTime;

	public RestaurantProduct() {}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getProductPrepTime() {
		return productPrepTime;
	}

	public void setProductPrepTime(Integer productPrepTime) {
		this.productPrepTime = productPrepTime;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}

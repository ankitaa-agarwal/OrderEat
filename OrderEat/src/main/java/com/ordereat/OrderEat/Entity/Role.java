package com.ordereat.OrderEat.Entity;

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
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
     
    private String name;
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private RestaurantOwner restaurantOwner;
    
    public Role() {}
    
    public Role(String name) {
    	this.name = name;
    }
    
    public Integer getId() {
        return id;
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RestaurantOwner getRestaurantOwner() {
		return restaurantOwner;
	}
	public void setRestaurantOwner(RestaurantOwner restaurantOwner) {
		this.restaurantOwner = restaurantOwner;
	}
    
    
}

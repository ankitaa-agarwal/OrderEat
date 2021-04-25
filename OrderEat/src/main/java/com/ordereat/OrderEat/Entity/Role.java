package com.ordereat.OrderEat.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "user_role", nullable = false)
    private String name;
    
    @JsonBackReference(value = "userRole")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private RestaurantUser restaurantUser;
    
    @JsonBackReference(value = "customerRole")
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
    
    public Role() {}
    
    public Role(String name) {
    	this.name = name;
    }
    
    @JsonProperty(access = Access.WRITE_ONLY)
    public Integer getId() {
        return id;
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public RestaurantUser getRestaurantUser() {
		return restaurantUser;
	}
	public void setRestaurantUser(RestaurantUser restaurantUser) {
		this.restaurantUser = restaurantUser;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}
	
}

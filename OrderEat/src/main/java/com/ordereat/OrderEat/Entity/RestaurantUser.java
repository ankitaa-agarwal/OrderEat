package com.ordereat.OrderEat.Entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name="restaurant_users")//PARENT CLASS of RestaurantDetails //Child Class for Roles
public class RestaurantUser {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	@JsonBackReference(value = "user")
	@ManyToOne(fetch = FetchType.LAZY,cascade =  CascadeType.ALL)
	@JoinColumn(name="restaurant_id")//Owner of relation, first insertUser then restaurant details
	private RestaurantDetails restaurantDetails;
	
	@JsonManagedReference(value = "userRole")
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "restaurantUser")
	private Set<Role> roles = new HashSet<Role>();
	
    @Column(nullable = false, unique = true, length = 45)
    private String email;
    
    @Column(name = "username", nullable = false, length = 20,  unique = true)
    private String username;
     
    @Column(nullable = false, length = 64)
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    
    @Column(name = "name", nullable = false, length = 20)
    private String fullName;
	
    @Column(name = "phonenumber", nullable = false, length = 13)
    private String phoneNumber;

    public RestaurantUser() {}
    
	/*
	 * public RestaurantOwner(String email, String password, String fullName, String
	 * phoneNumber, String username) { super(); this.email = email; this.password =
	 * password; this.fullName = fullName; this.phoneNumber = phoneNumber;
	 * this.username = username; }
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public RestaurantDetails getRestaurantDetails() {
		return restaurantDetails;
	}

	public void setRestaurantDetails(RestaurantDetails restaurantDetails) {
		this.restaurantDetails = restaurantDetails;
	}
	
	public void addRole(Role role) {
		this.roles.add(role);
		role.setRestaurantUser(this);
	}
	
	public Set<Role> getRoles() {
		return this.roles;
	}
	
}

package com.ordereat.OrderEat.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.ordereat.OrderEat.Auditable;

@Entity
@Table(name = "customer")
public class CustomerEntity extends Auditable<String>{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "firstname", nullable = false, length = 20)
	private String firstName;
	
	@Column(name = "lastname", nullable = false, length = 20)
	private String lastName;
	
	@Column(name = "email", nullable = false, length = 30,  unique = true)
	private String emailId;

	@Column(name = "phonenumber", nullable = false, length = 13)
	private String phone;
	
	@Column(name = "username", nullable = false, length = 13, unique = true)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "city", nullable = false, length = 20)
	private String customerCity;
	
	@OneToOne
    @JoinColumn(name = "roleId")
	private Role roleId;

	@JsonManagedReference(value = "customer")
	@OneToMany(fetch = FetchType.LAZY,
	            cascade =  CascadeType.ALL, orphanRemoval = true, mappedBy = "customer")
	private List<Order> restaurantOrders = new ArrayList<Order>();
	
	public CustomerEntity() {}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public Long getId() {
		return id;
	}
	
	@JsonProperty(access = Access.WRITE_ONLY)
	public List<Order> getRestaurantOrders() {
		return restaurantOrders;
	}

	public void addRestaurantOrders(Order order) {
		this.restaurantOrders.add(order);
		order.setCustomer(this);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}

}

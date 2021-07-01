package com.ordereat.OrderEat.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
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
import com.ordereat.OrderEat.Auditable;

@Entity
@Table(name = "order_table")
public class Order extends Auditable<String> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonBackReference(value = "customer")
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id")
	private CustomerEntity customer;
	
	@Column(name = "status")
	@Convert(converter = OrderStatusConverter.class)
	private OrderStatus orderStatus;
	
	@Column(name = "reason")
	private String orderReason;
	
	@Column(name = "seats")
	private int seats;
	
	@JsonBackReference(value = "order")
	@ManyToOne(fetch = FetchType.LAZY,cascade =  CascadeType.ALL)
	@JoinColumn(name="restaurant_id")//Owner of relation, first insertUser then restaurant details
	private RestaurantDetails restaurant;

	@JsonManagedReference(value = "orderRef")
	@OneToMany(fetch = FetchType.LAZY,
	            cascade =  CascadeType.ALL, orphanRemoval = true, mappedBy = "order")
	private List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();
	
	public Order() {}
	
	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public RestaurantDetails getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(RestaurantDetails restaurant) {
		this.restaurant = restaurant;
	}

	public Long getId() {
		return id;
	}
	
	@JsonProperty(access = Access.WRITE_ONLY)
	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void addOrderProduct(OrderProduct product) {
		this.orderProducts.add(product);
		product.setOrder(this);
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderReason() {
		return orderReason;
	}

	public void setOrderReason(String orderReason) {
		this.orderReason = orderReason;
	}

}

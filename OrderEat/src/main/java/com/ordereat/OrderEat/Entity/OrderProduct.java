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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "order_products")
public class OrderProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "quantity", nullable = false)
	private int quantity;
	
	@Transient
	private Long product_id;
	
	@OneToOne(targetEntity = RestaurantProduct.class)
	@JoinColumn(name = "product_id")
	RestaurantProduct product;

	@JsonBackReference(value = "orderRef")
	@ManyToOne(fetch = FetchType.LAZY,cascade =  CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private Order order;
	
	public OrderProduct() {}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public RestaurantProduct getProduct() {
		return product;
	}

	public void setProduct(RestaurantProduct product) {
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	
}

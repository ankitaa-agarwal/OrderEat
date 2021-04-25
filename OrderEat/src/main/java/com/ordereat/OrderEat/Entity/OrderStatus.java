package com.ordereat.OrderEat.Entity;

public enum OrderStatus {
	ORDER_PLACED("placed"),
	ORDER_ACCEPTED("accepted"),
	ORDER_ONHOLD("onHold"),
	ORDER_REJECTED("rejected"),
	ORDER_PROCESSING("processing"),
	ORDER_OUT_FOR_DELIVERY("outForDelivery"),
	ORDER_DELIVERED("delivered"),
	ORDER_NOT_DELIVERED("notDelivered");
	
	private String status;
	
	private OrderStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
}

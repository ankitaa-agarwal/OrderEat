package com.ordereat.OrderEat.Service;

import org.springframework.http.ResponseEntity;

import com.ordereat.OrderEat.Entity.ResponseEntityClass;
import com.ordereat.OrderEat.Entity.RestaurantProductRequest;

public interface RestaurantProductsService {
	
	ResponseEntity<ResponseEntityClass> addProduct(RestaurantProductRequest restaurantProduct);
	
	ResponseEntity<ResponseEntityClass> updateProduct(RestaurantProductRequest restaurantProduct);
	
	ResponseEntity<ResponseEntityClass> deleteProduct(Long productId);

	ResponseEntity<ResponseEntityClass> getAllProductsForRestaurant(Long restaurantId);
}

package com.ordereat.OrderEat.Repository;

import java.util.List;

import com.ordereat.OrderEat.Entity.RestaurantProduct;
import com.ordereat.OrderEat.Entity.RestaurantProductRequest;

public interface RestaurantProductsRepository {

	void addProduct(RestaurantProductRequest restaurantProduct);
	
	void updateProduct(RestaurantProductRequest restaurantProductReq);
	
	void deleteProduct(Long productId);
	
	List<RestaurantProduct> getProductsFromRestaurant(Long restaurantId);

	RestaurantProduct findIfProductExistById(Long l);

	RestaurantProduct findProductByNameAndRestaurantId(String productName, Long restaurantId);
	
	
}

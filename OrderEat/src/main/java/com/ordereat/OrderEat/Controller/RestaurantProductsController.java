package com.ordereat.OrderEat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ordereat.OrderEat.Entity.ResponseEntityClass;
import com.ordereat.OrderEat.Entity.RestaurantProductRequest;
import com.ordereat.OrderEat.Service.RestaurantProductsService;

@RestController
@RequestMapping(value = {"/roe/product","/oe"})
public class RestaurantProductsController {

	@Autowired
	RestaurantProductsService restaurantProductsService;
	
	@PostMapping("/addProduct")
	public ResponseEntity<ResponseEntityClass> addProduct(
			@RequestBody RestaurantProductRequest restaurantProduct) {
		return restaurantProductsService.addProduct(restaurantProduct);
	}
	
	@PutMapping("/updateProduct")
	public ResponseEntity<ResponseEntityClass> updateProduct(@RequestBody RestaurantProductRequest restaurantProduct){
		return restaurantProductsService.updateProduct(restaurantProduct);
	}
	
	@GetMapping("/getProductsForRestaurant/{id}")
	public ResponseEntity<ResponseEntityClass> getProductsForRestaurant(@PathVariable("id") Long restaurantId){
		return restaurantProductsService.getAllProductsForRestaurant(restaurantId);
	}
	
	@DeleteMapping("/removeProduct/{id}")
	public ResponseEntity<ResponseEntityClass> deleteProduct(@PathVariable("id") Long productId){
		return restaurantProductsService.deleteProduct(productId);
	}
}

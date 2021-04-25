package com.ordereat.OrderEat.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ordereat.OrderEat.Entity.ResponseEntityClass;
import com.ordereat.OrderEat.Entity.RestaurantProduct;
import com.ordereat.OrderEat.Entity.RestaurantProductRequest;
import com.ordereat.OrderEat.Exception.BadRequestException;
import com.ordereat.OrderEat.Exception.NotFoundException;
import com.ordereat.OrderEat.Repository.RestaurantProductsRepository;
import com.ordereat.OrderEat.Service.RestaurantUserServiceImpl.STATUS;

@Service
public class RestaurantProductServiceImpl implements RestaurantProductsService {

	@Autowired
	RestaurantProductsRepository restaurantProductsRepository;
	
	@Override
	public ResponseEntity<ResponseEntityClass> addProduct(RestaurantProductRequest restaurantProductReq) {
		if (restaurantProductReq.getRestaurant_id().isEmpty() || restaurantProductReq.getProductName().isEmpty()
				|| restaurantProductReq.getProductDescription().isEmpty() || restaurantProductReq.getProductPrice().isEmpty()
				|| restaurantProductReq.getProductPrepTime().isEmpty())
			throw new BadRequestException("Bad Request - Missing Fields, check the request again");

		restaurantProductsRepository.addProduct(restaurantProductReq);
		ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), null, "");
		return new ResponseEntity<ResponseEntityClass>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseEntityClass> updateProduct(RestaurantProductRequest restaurantProductReq) {
		if (restaurantProductReq.getProductID().isEmpty() || restaurantProductReq.getProductName().isEmpty() || restaurantProductReq.getProductDescription().isEmpty()
				|| restaurantProductReq.getProductPrice().isEmpty()
				|| restaurantProductReq.getProductPrepTime().isEmpty())
			throw new BadRequestException("Bad Request - Missing Fields, check the request again");

		restaurantProductsRepository.updateProduct(restaurantProductReq);
		ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), null, "");
		return new ResponseEntity<ResponseEntityClass>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseEntityClass> deleteProduct(Long productId) {
		restaurantProductsRepository.deleteProduct(productId);
		ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), null, "");
		return new ResponseEntity<ResponseEntityClass>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseEntityClass> getAllProductsForRestaurant(Long restaurantId) {
		List<RestaurantProduct> restaurantProducts = restaurantProductsRepository.getProductsFromRestaurant(restaurantId);
		if (restaurantProducts == null) {
			throw new NotFoundException("Restaurant Product does not exist");
		} else {
			ResponseEntityClass response = new ResponseEntityClass(STATUS.SUCCESS.toString(), restaurantProducts, "");
			return new ResponseEntity<ResponseEntityClass>(response, HttpStatus.OK);
		}
	}

}

package com.ordereat.OrderEat.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ordereat.OrderEat.Entity.RestaurantDetails;
import com.ordereat.OrderEat.Entity.RestaurantProduct;
import com.ordereat.OrderEat.Entity.RestaurantProductRequest;
import com.ordereat.OrderEat.Exception.AlreadyExistsException;
import com.ordereat.OrderEat.Exception.NotFoundException;

@Repository
@Transactional
public class RestaurantProductRepositoryImpl implements RestaurantProductsRepository{

	@Autowired
	EntityManager entityManager;
	
	@Override
	public List<RestaurantProduct> getProductsFromRestaurant(Long restaurantId){
		String query = "Select product from RestaurantProduct product where restaurant_id = :restaurantId";
		TypedQuery<RestaurantProduct> typedQuery = entityManager.createQuery(query, RestaurantProduct.class);
		typedQuery.setParameter("restaurantId", restaurantId);
		List<RestaurantProduct> productsList = typedQuery.getResultList();
		return productsList;
	}
	
	@Override
	public RestaurantProduct findProductByNameAndRestaurantId(String productName, Long restaurantId) {
		String query = "Select product from RestaurantProduct product where productName = : name AND restaurant_id = :restaurantId";
		TypedQuery<RestaurantProduct> typedQuery = entityManager.createQuery(query, RestaurantProduct.class);
		typedQuery.setParameter("name", productName);
		typedQuery.setParameter("restaurantId", restaurantId);
		List<RestaurantProduct> productsList = typedQuery.getResultList();
		if(productsList.size() > 0)
			return productsList.get(0);
		else
			return null;
	}
	
	@Override
	public void addProduct(RestaurantProductRequest restaurantProductReq) {
		// TODO Auto-generated method stub
		RestaurantDetails restaurantDetails = entityManager.find(RestaurantDetails.class, Long.parseLong(restaurantProductReq.getRestaurant_id()));
		//List<RestaurantProduct> productsList = getProductsFromRestaurant(restaurantDetails.getId());
		RestaurantProduct existsingProduct = findProductByNameAndRestaurantId(restaurantProductReq.getProductName(), restaurantDetails.getId());
		if(existsingProduct != null) {
			throw new AlreadyExistsException("The Product already exists for the selected restaurant");
		}
		RestaurantProduct restaurantProduct = new RestaurantProduct();
		restaurantProduct.setRestaurantDetails(restaurantDetails);
		restaurantProduct.setProductName(restaurantProductReq.getProductName());
		restaurantProduct.setProductDescription(restaurantProductReq.getProductDescription());
		restaurantProduct.setProductPrepTime(Integer.parseInt(restaurantProductReq.getProductPrepTime()));
		restaurantProduct.setProductPrice(Double.parseDouble(restaurantProductReq.getProductPrice()));
		restaurantDetails.addRestaurantProduct(restaurantProduct);
		
		entityManager.persist(restaurantProduct);
		
	}

	@Override
	public void updateProduct(RestaurantProductRequest restaurantProductReq) {
		RestaurantProduct restaurantProduct = findIfProductExistById(Long.parseLong(restaurantProductReq.getProductID()));
		if(restaurantProduct != null) {
			restaurantProduct.setProductDescription(restaurantProductReq.getProductDescription());
			restaurantProduct.setProductName(restaurantProductReq.getProductName());
			restaurantProduct.setProductPrice(Double.parseDouble(restaurantProductReq.getProductPrice()));
			restaurantProduct.setProductPrepTime(Integer.parseInt(restaurantProductReq.getProductPrepTime()));
		}
		else
			throw new NotFoundException("Product does not Exists");
	}

	@Override
	public void deleteProduct(Long productId) {
		// TODO Auto-generated method stub
		RestaurantProduct restaurantProduct = findIfProductExistById(productId);
		if(restaurantProduct != null) {
			RestaurantDetails restaurantDetails = restaurantProduct.getRestaurantDetails();
			restaurantDetails.getRestaurantProducts().remove(restaurantProduct);
		}
	}

	@Override
	public RestaurantProduct findIfProductExistById(Long productID) {
		RestaurantProduct restaurantProduct = entityManager.find(RestaurantProduct.class, productID);
		return restaurantProduct;
	}

}

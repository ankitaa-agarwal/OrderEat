package com.ordereat.OrderEat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.ordereat.OrderEat.Entity.OrderRequestEntity;
import com.ordereat.OrderEat.Entity.ResponseEntityClass;
import com.ordereat.OrderEat.Service.OrderService;

@RestController
@RequestMapping("/oe")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping("/order")
	public ResponseEntity<ResponseEntityClass> placeOrder(@RequestBody OrderRequestEntity orderRequest){
		return orderService.placeOrder(orderRequest);
	}
	
	@GetMapping("/getOrders")
	public ResponseEntity<ResponseEntityClass> getOrder(){
		return orderService.getOrders();
	}
	
	@GetMapping("/getOrder/{id}")
	public ResponseEntity<ResponseEntityClass> getOrderFromOrderId(@PathVariable("id") Long orderId ){
		return orderService.getOrderFromOrderId(orderId);
	}
	
	@GetMapping("/getOrderForCustomer/{id}")
	public MappingJacksonValue getOrderFromCustomerId(@PathVariable("id") Long customerId ){
		 SimpleBeanPropertyFilter simpleBeanPropertyFilter =
				                 SimpleBeanPropertyFilter.serializeAllExcept("customer_id", "customer_name");
		 FilterProvider filterProvider = new SimpleFilterProvider()
				                 .addFilter("customerOrderFilter", simpleBeanPropertyFilter);
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(orderService.getOrderFromCustomerId(customerId));
		mappingJacksonValue.setFilters(filterProvider);
		
		return mappingJacksonValue;
		
	}
}

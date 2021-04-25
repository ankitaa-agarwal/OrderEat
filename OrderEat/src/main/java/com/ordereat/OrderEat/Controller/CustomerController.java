package com.ordereat.OrderEat.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.ordereat.OrderEat.Entity.CustomerEntity;
import com.ordereat.OrderEat.Entity.LoginCredentials;
import com.ordereat.OrderEat.Entity.ResponseEntityClass;
import com.ordereat.OrderEat.Service.CustomerService;

@RestController
@RequestMapping("/cus")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@PostMapping("/register")
	public ResponseEntity<ResponseEntityClass> registerCustomer(
			@RequestBody CustomerEntity customerEntity) {
		return customerService.registerCustomer(customerEntity);
	}

	@PostMapping("/login")
	public MappingJacksonValue Login(@RequestBody LoginCredentials loginCredentials ){
		 SimpleBeanPropertyFilter simpleBeanPropertyFilter =
				                 SimpleBeanPropertyFilter.serializeAllExcept("customer_id", "customer_name");
		 FilterProvider filterProvider = new SimpleFilterProvider()
				                 .addFilter("customerOrderFilter", simpleBeanPropertyFilter);
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(customerService.loginCustomer(loginCredentials));
		mappingJacksonValue.setFilters(filterProvider);
		
		return mappingJacksonValue;
		
	}
}

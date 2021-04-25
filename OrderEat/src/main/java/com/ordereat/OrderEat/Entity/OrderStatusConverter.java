package com.ordereat.OrderEat.Entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {

	@Override
	public String convertToDatabaseColumn(OrderStatus attribute) {
		// TODO Auto-generated method stub
		if(attribute == null)
			return null;
		
		return attribute.getStatus();
	}

	@Override
	public OrderStatus convertToEntityAttribute(String dbData) {
		// TODO Auto-generated method stub
		if(dbData == null)
			return null;
		OrderStatus status = null;
		for(OrderStatus s: OrderStatus.values()) {
			if(s.getStatus().equals(dbData))
				status = s;
		}
		return status;
	}

	
}

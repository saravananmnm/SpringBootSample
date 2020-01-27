package com.app.util;

import com.app.model.Customer;

public interface CustomerUtil {
	public static void mapNotNullValues(Customer source, Customer customer) {
		if(customer!=null ) {
			
			if(customer.getcName()!=null) source.setcName(customer.getcName());
			if(customer.getServiceId()!=null) source.setServiceId(customer.getServiceId());
			if(customer.getMode()!=null && !customer.getMode().isEmpty()) source.setMode(customer.getMode());
			
		}
	}
}

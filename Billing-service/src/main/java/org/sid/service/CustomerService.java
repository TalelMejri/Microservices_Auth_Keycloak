package org.sid.service;

import org.sid.entity.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="customer-service")
public interface CustomerService {
	
	 @GetMapping("/customers/{id}")
	 public Customer findCustomerById(@PathVariable(name="id") Long id);
	 
}

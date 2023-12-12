package org.sid.service;

import org.sid.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="inventory-service")
public interface InventoryServiceClient{
	
	@GetMapping("/products/{id}") 
	public Product findProductById(@PathVariable("id") Long id);
	
	@GetMapping("/products?projection=fullInventory")
	public PagedModel<Product> findAll();
	
}
package org.sid.controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.sid.entity.Bill;
import org.sid.entity.Customer;
import org.sid.entity.Product;
import org.sid.entity.ProductItem;
import org.sid.repository.BillRepo;
import org.sid.repository.ProductItemRepo;
import org.sid.service.CustomerService;
import org.sid.service.InventoryServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.JSONObject;

@RestController
public class BillRestController {
	
	@Autowired
	private BillRepo billRepository;
	
	@Autowired 
	private ProductItemRepo productItemRepository;
	
	@Autowired
	private CustomerService customerServiceClient;
	
	@Autowired 
	private InventoryServiceClient inventoryServiceClient; 

	@PostMapping("/bills/full")
	public JSONObject getBill(@RequestBody JSONObject requestBody) {
		
		    ObjectMapper objectMapper = new ObjectMapper();
		    
		    TypeFactory typeFactory = objectMapper.getTypeFactory();
		    
		    Customer cust = objectMapper.convertValue(requestBody.get("customer"), Customer.class);

		    List<Product> inventory = objectMapper.convertValue(
		            requestBody.get("inventory"),
		            typeFactory.constructCollectionType(List.class, Product.class)
		    );

	        Bill bill = new Bill();
	        bill.setBillingDate(new Date());
	        bill.setCustomerID(cust.getId());
	        bill.setCustomer(cust);
	        billRepository.save(bill);
	        
	        Collection<ProductItem> productItems = new ArrayList<>();;
	        inventory.forEach(p -> {
	        	ProductItem prod=new ProductItem();
				Product ProdItemNew=new Product();
				ProdItemNew.setName(p.getName());
				ProdItemNew.setPrice(p.getPrice());
				ProdItemNew.setId(p.getId());
				prod.setProduct(ProdItemNew);
				prod.setBill(null);
				prod.setQuantity((int)(1+Math.random()*1000));
				prod.setPrice(p.getPrice());
				prod.setProductID(p.getId());
				productItems.add(prod);
				System.out.println("2222222222222222222222222222222222222222222222222222222");
				productItemRepository.save(prod);
			});
	       
	        
	        Bill billResult = billRepository.findById(bill.getId()).get();
	        billResult.setCustomer(cust);
	        billResult.setProductItems(productItems);
	        JSONObject json=new JSONObject();
	        json.appendField("bill",billResult);
		    return json;
	}

		
	/*Bill bill = billRepository.findById(id).get();
    Customer cust = customerServiceClient.findCustomerById(bill.getCustomerID());
    cust.setId(bill.getCustomerID());
    bill.setCustomer(cust);
    
     bill.getProductItems().forEach(pi -> {
    	 	Product prod=inventoryServiceClient.findProductById(pi.getProductID());
    	 	Product ProdItemNew=new Product();
			ProdItemNew.setName(prod.getName());
			ProdItemNew.setPrice(prod.getPrice());
			ProdItemNew.setId(pi.getProductID());
			pi.setProduct(ProdItemNew);
    }); */
	
}
@Data @AllArgsConstructor @NoArgsConstructor
class payload{
	PagedModel<Product> inv;
	ResponseEntity<Customer> supp;
}
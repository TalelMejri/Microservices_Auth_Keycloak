package org.sid;


import java.util.Date;

import org.sid.entity.Bill;
import org.sid.entity.Customer;
import org.sid.entity.Product;
import org.sid.entity.ProductItem;
import org.sid.repository.BillRepo;
import org.sid.repository.ProductItemRepo;
import org.sid.service.CustomerService;
import org.sid.service.InventoryServiceClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}
	
	
	/*@Bean
	public CommandLineRunner start(BillRepo billRepository, ProductItemRepo productItemRepository,
								   InventoryServiceClient inventoryServiceClient,
								   CustomerService customerServiceClient) {

		return args -> {
			System.out.println("Before customerServiceClient.findCustomerById");
			Customer customer = customerServiceClient.findCustomerById((long) 1);
			System.out.println("After customerServiceClient.findCustomerById");
			Customer cust=new Customer();
		    cust.setEmail(customer.getEmail());
		    cust.setName(customer.getName());
		    cust.setId((long)1);
	        Bill bill = new Bill();
	        bill.setBillingDate(new Date());
	        bill.setCustomerID(cust.getId());
	        bill.setCustomer(cust);
	        billRepository.save(bill); 
	        System.out.println("1111111111111111111111111111111111111111111111111111111");

	      inventoryServiceClient.findAll().getContent().forEach(p -> {
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
				  System.out.println("2222222222222222222222222222222222222222222222222222222");
				productItemRepository.save(prod);
			});
		};
	}*/
	

}


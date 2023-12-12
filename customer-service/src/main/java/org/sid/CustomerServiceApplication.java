package org.sid;

import org.sid.entity.Customer;
import org.sid.repository.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(CustomerRepo customerRepo)
	{
		return args->{
			customerRepo.save(new Customer(null,"eni","contact@eni.tn"));
			customerRepo.save(new Customer(null,"FST","contact@fst.tn"));
			customerRepo.save(new Customer(null,"Ensi","contact@ensi.tn"));
			customerRepo.findAll().forEach(System.out::println);
		};
	}
}

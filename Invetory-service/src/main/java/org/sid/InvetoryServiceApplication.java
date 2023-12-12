package org.sid;
import org.sid.entity.Product;
import org.sid.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InvetoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvetoryServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(ProductRepository productRepository)
	{
		return args->{
			productRepository.save(new Product(null,"pc",1.5));
			productRepository.save(new Product(null,"clavier",1.5));
			productRepository.save(new Product(null,"souris",1.5));
			productRepository.findAll().forEach(System.out::println);
		};
	}

}

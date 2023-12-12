package org.sid;

import java.util.HashMap;
import java.util.Map;
import org.springframework.cloud.gateway.filter.factory.SpringCloudCircuitBreakerFilterFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.filter.factory.SpringCloudCircuitBreakerFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}
	
	  
	/*@Bean
	RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
	    return builder.routes()
	        .route(r -> r
	            .path("/muslim/**")
	            .filters(f -> f
	                .addRequestHeader("X-RapidAPI-Host", "muslimsalat.p.rapidapi.com")
	                .addRequestHeader("X-RapidAPI-Key", "edee54f4f7msh01a0859837acbaap192bf4jsne43e2b11af3d")
	                .rewritePath("/muslimsalat/(?<segment>.*)","/${segment}")
	                .circuitBreaker(c->c.setName("muslim").setFallbackUri("forward:/default"))
	            		)
	            .uri("https://muslimsalat.com/") 
	        )
	        .build();
	}*/
	
	
	/*@Bean
		RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
		return builder.routes()
				.route(r->r.path("/customers/**").uri("lb://CUSTOMER-SERVICE") )
				.route(r->r.path("/products/**").uri("lb://INVENTORY-SERVICE") ).build();
	}*/

	@Bean
	DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc,
			DiscoveryLocatorProperties dlp) {
		return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
	}

	
	
}

/*@RestController
class CircuitBreakerController{
	
	@GetMapping("/default")
	public Map<String,String> salat(){
		Map<String,String> map=new HashMap<>();
		map.put("message","Default Muslim Fallback service");
		map.put("Fajr","5:30");
		map.put("DOHR","12:20");
		map.put("Asr","15:10");
		map.put("Maghreb","18:30");
		map.put("Icha","19:30");
		return map;
	}
}*/

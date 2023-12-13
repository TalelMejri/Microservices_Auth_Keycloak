package ma.app.productsapp.web;

import ma.app.productsapp.entities.Product;
import ma.app.productsapp.repositories.ProductRepository;
import net.minidev.json.JSONObject;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;


@EnableMethodSecurity
@Controller
public class ProductController{
	
	@Autowired
	OAuth2AuthorizedClientService oauth2ClientService;
	
	@Autowired
	RestTemplate restTemplate;
	
    @Autowired
    private ProductRepository productRepository;
   

    @GetMapping("/")
    //@PreAuthorize("hasRole('client-user')")
    public String index(){
        return "index";
    }
    @GetMapping("/products")
    //@PreAuthorize("hasRole('client-admin')")
    public String products(Model model,@AuthenticationPrincipal OidcUser principal
			) {
		
		  Authentication authentication =
		  SecurityContextHolder.getContext().getAuthentication();
		  OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken)
		  authentication;
		  
		  OAuth2AuthorizedClient oauth2Client =
		  oauth2ClientService.loadAuthorizedClient(oauthToken.
		  getAuthorizedClientRegistrationId(), oauthToken.getName());
		  
		  String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
		  System.out.println("jwtAccessToken = " + jwtAccessToken);
		  
		  
		  System.out.println("Principal = " + principal);
		  
		  OidcIdToken idToken = principal.getIdToken(); 
		  String idTokenValue = idToken.getTokenValue(); 
		  System.out.println("idTokenValue = " + idTokenValue);
		  model.addAttribute("products",productRepository.findAll());
		  return "products";
    }
    
    @GetMapping("/suppliers")
    public String suppliers(Model model,@AuthenticationPrincipal OidcUser principal
			) {
		
		  Authentication authentication =
		  SecurityContextHolder.getContext().getAuthentication();
		  OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken)
		  authentication;
		  
		  OAuth2AuthorizedClient oauth2Client =
		  oauth2ClientService.loadAuthorizedClient(oauthToken.
		  getAuthorizedClientRegistrationId(), oauthToken.getName());
		  
		  String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
		  System.out.println("jwtAccessToken = " + jwtAccessToken);
		  
		  
		  System.out.println("Principal = " + principal);
		  
		  OidcIdToken idToken = principal.getIdToken(); 
		  String idTokenValue = idToken.getTokenValue(); 
		  System.out.println("idTokenValue = " + idTokenValue);
    	  String url = "http://localhost:8888/SUPPLIER-SERVICE/all"; 
		  HttpHeaders headers = new HttpHeaders(); 
		  headers.add("Authorization", "Bearer " + jwtAccessToken);
		  HttpEntity<List<Supplier>> entity = new HttpEntity<>(headers);
		  ResponseEntity<List<Supplier>> responseEntity = restTemplate.exchange(url,
		  HttpMethod.GET, entity, new ParameterizedTypeReference<List<Supplier>>()
		  {});
		  
		  List<Supplier> pageSuppliers = responseEntity.getBody();
		  model.addAttribute("suppliers",pageSuppliers);
		  return "suppliers";
    }
    
    @GetMapping("/all")
    public String allsuppliers(Model model,@AuthenticationPrincipal OidcUser principal
			) {
		
		  Authentication authentication =
		  SecurityContextHolder.getContext().getAuthentication();
		  OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken)
		  authentication;
		  
		  OAuth2AuthorizedClient oauth2Client =
		  oauth2ClientService.loadAuthorizedClient(oauthToken.
		  getAuthorizedClientRegistrationId(), oauthToken.getName());
		  
		  String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
		  System.out.println("jwtAccessToken = " + jwtAccessToken);
		  
		  
		  System.out.println("Principal = " + principal);
		  
		  OidcIdToken idToken = principal.getIdToken(); 
		  String idTokenValue = idToken.getTokenValue(); 
		  System.out.println("idTokenValue = " + idTokenValue);
    
		  String url = "http://localhost:8082/suppliers"; 
		  HttpHeaders headers = new HttpHeaders(); 
		  headers.add("Authorization", "Bearer " + jwtAccessToken);
		  HttpEntity<List<Supplier>> entity = new HttpEntity<>(headers);
		  ResponseEntity<PagedModel<Supplier>>response = restTemplate.exchange(url,
				  HttpMethod.GET, entity, new ParameterizedTypeReference<PagedModel<Supplier>>()
				  {});
		  
	        model.addAttribute("supplier",response.getBody().toString());
	        return "supphateoas";
    }
    
    
    
    @GetMapping("/Bill")
    public String Bill(Model model,@AuthenticationPrincipal OidcUser principal
			) {
		
		  Authentication authentication =
		  SecurityContextHolder.getContext().getAuthentication();
		  OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken)
		  authentication;
		  
		  OAuth2AuthorizedClient oauth2Client =
		  oauth2ClientService.loadAuthorizedClient(oauthToken.
		  getAuthorizedClientRegistrationId(), oauthToken.getName());
		  
		  String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
		  System.out.println("jwtAccessToken = " + jwtAccessToken);
	
		  System.out.println("Principal = " + principal);
		  
		  OidcIdToken idToken = principal.getIdToken(); 
		  String idTokenValue = idToken.getTokenValue(); 
		  System.out.println("idTokenValue = " + idTokenValue);
  
		  String url = "http://localhost:8888/CUSTOMER-SERVICE/customers/1?projection=fullcustomer"; 
		  HttpHeaders headers = new HttpHeaders(); 
		  headers.add("Authorization", "Bearer " + jwtAccessToken);
		  HttpEntity<Supplier> entity = new HttpEntity<>(headers);
		  ResponseEntity<Supplier>responsecustomer = restTemplate.exchange(url,
				  HttpMethod.GET, entity, new ParameterizedTypeReference<Supplier>()
				  {});
	  
		  String url1 = "http://localhost:8888/INVENTORY-SERVICE/products?projection=fullInventory"; 
		  HttpHeaders headers1 = new HttpHeaders(); 
		  headers1.add("Authorization", "Bearer " + jwtAccessToken);
		  HttpEntity<List<Invetory>> entity1 = new HttpEntity<>(headers1);
		  ResponseEntity<PagedModel<Invetory>>responseinventory = restTemplate.exchange(url1,
				  HttpMethod.GET, entity1, new ParameterizedTypeReference<PagedModel<Invetory>>()
				  {});

		  String url3 = "http://localhost:8888/BILL-SERVICE/bills/full"; 

		  HttpHeaders headers3 = new HttpHeaders();
		  headers3.add("Authorization", "Bearer " + jwtAccessToken);
		  PagedModel<Invetory> inventoryBody = responseinventory.getBody();
		  Supplier responsecustomerbody = responsecustomer.getBody();
  
		  JSONObject json=new JSONObject();
		  json.appendField("customer",responsecustomerbody);
		  json.appendField("inventory", inventoryBody.getContent());

		  HttpEntity<JSONObject> entity3 = new HttpEntity<>(json, headers3);
		  ResponseEntity<JSONObject> responseBill = restTemplate.exchange(
		          url3,
		          HttpMethod.POST,
		          entity3,
		          new ParameterizedTypeReference<JSONObject>() {}
		  );
		  
		  /*ObjectMapper objectMapper = new ObjectMapper();
		  Bill bill = objectMapper.convertValue(responseBill, Bill.class);*/
		  //System.out.println(bill);
		  model.addAttribute("bill", responseBill.getBody());
		  return "Bill";

    }
    
    
    
    @GetMapping("/AllCustomers")
    public String AllCustomers(Model model,@AuthenticationPrincipal OidcUser principal
			) {
		
		  Authentication authentication =
		  SecurityContextHolder.getContext().getAuthentication();
		  OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken)
		  authentication;
		  
		  OAuth2AuthorizedClient oauth2Client =
		  oauth2ClientService.loadAuthorizedClient(oauthToken.
		  getAuthorizedClientRegistrationId(), oauthToken.getName());
		  
		  String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
		  System.out.println("jwtAccessToken = " + jwtAccessToken);
		  	  
		  System.out.println("Principal = " + principal);
		  
		  OidcIdToken idToken = principal.getIdToken(); 
		  String idTokenValue = idToken.getTokenValue(); 
		  System.out.println("idTokenValue = " + idTokenValue);
    
		  String url = "http://localhost:8888/CUSTOMER-SERVICE/customers?projection=fullcustomer"; 
		  HttpHeaders headers = new HttpHeaders(); 
		  headers.add("Authorization", "Bearer " + jwtAccessToken);
		  HttpEntity<List<Supplier>> entity = new HttpEntity<>(headers);
		  ResponseEntity<PagedModel<Supplier>>response = restTemplate.exchange(url,
				  HttpMethod.GET, entity, new ParameterizedTypeReference<PagedModel<Supplier>>()
				  {});

	      model.addAttribute("cutomers",response.getBody());
	      return "cutomers";
    }
    
    @GetMapping("/AllInventory")
    public String AllInventory(Model model,@AuthenticationPrincipal OidcUser principal
			) {
		
		  Authentication authentication =
		  SecurityContextHolder.getContext().getAuthentication();
		  OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken)
		  authentication;
		  
		  OAuth2AuthorizedClient oauth2Client =
		  oauth2ClientService.loadAuthorizedClient(oauthToken.
		  getAuthorizedClientRegistrationId(), oauthToken.getName());
		  
		  String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
		  System.out.println("jwtAccessToken = " + jwtAccessToken);
		  	  
		  System.out.println("Principal = " + principal);
		  
		  OidcIdToken idToken = principal.getIdToken(); 
		  String idTokenValue = idToken.getTokenValue(); 
		  System.out.println("idTokenValue = " + idTokenValue);
    
		  String url = "http://localhost:8888/INVENTORY-SERVICE/products?projection=fullInventory"; 
		  HttpHeaders headers = new HttpHeaders(); 
		  headers.add("Authorization", "Bearer " + jwtAccessToken);
		  HttpEntity<List<Invetory>> entity = new HttpEntity<>(headers);
		  ResponseEntity<PagedModel<Invetory>>response = restTemplate.exchange(url,
				  HttpMethod.GET, entity, new ParameterizedTypeReference<PagedModel<Invetory>>()
				  {});
		  System.out.println(response.getBody());

	      model.addAttribute("inventory",response.getBody());
	      return "inventory";
    }
  
}





class payload{
	PagedModel<Invetory> inv;
	ResponseEntity<Supplier> supp;
	
	public payload(PagedModel<Invetory> inventoryBody,ResponseEntity<Supplier> responsecustomer) {
		super();
		this.inv = inventoryBody;
		this.supp = responsecustomer;
	}


	@Override
	public String toString() {
		return "payload [inv=" + inv.toString() + ", supp=" + supp.toString()+ "]";
	}
	
}
class Invetory{
	private Long id;
	private String name;
	private double price;
	public Invetory() {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Inventory [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
}
class Supplier{
    private Long id;
    private String name;
    private String email;
   
	public Supplier() {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	
    
}

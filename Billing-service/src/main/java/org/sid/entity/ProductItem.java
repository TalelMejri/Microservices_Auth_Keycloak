package org.sid.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class ProductItem {
	
	@Id 
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	
	@Transient
	private Product product;
	
	@JsonProperty(access =JsonProperty.Access.WRITE_ONLY)
	private Long productID;
	
	private double price;
	private double quantity;
	
	@ManyToOne
	@JsonProperty(access =JsonProperty.Access.WRITE_ONLY)
	private Bill bill;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getProductID() {
		return productID;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public ProductItem(Long id, Product product, Long productID, double price, double quantity, Bill bill) {
		super();
		this.id = id;
		this.product = product;
		this.productID = productID;
		this.price = price;
		this.quantity = quantity;
		this.bill = bill;
	}

	public ProductItem() {
		super();
	}
	
	
	
}

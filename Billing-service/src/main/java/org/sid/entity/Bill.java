package org.sid.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Bill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date billingDate;
	
	@Transient
	Customer customer;
	
	@JsonProperty(access =JsonProperty.Access.WRITE_ONLY)
	private Long customerID;
	
	@OneToMany(mappedBy="bill")
	private Collection<ProductItem>	productItems;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}

	public Collection<ProductItem> getProductItems() {
		return productItems;
	}

	public void setProductItems(Collection<ProductItem> productItems) {
		this.productItems = productItems;
	}

	public Bill(Long id, Date billingDate, Customer customer, long customerID, Collection<ProductItem> productItems) {
		super();
		this.id = id;
		this.billingDate = billingDate;
		this.customer = customer;
		this.customerID = customerID;
		this.productItems = productItems;
	}

	public Bill() {
		super();
	}
	
	@Override
	public String toString() {
		return "Bill [id=" + id + ", billingDate=" + billingDate + ", customer=" + customer + ", customerID="
				+ customerID + ", productItems=" + productItems + "]";
	}
	
	
	
	
}

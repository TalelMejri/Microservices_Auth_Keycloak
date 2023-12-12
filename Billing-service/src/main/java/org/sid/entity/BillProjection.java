package org.sid.entity;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.rest.core.config.Projection;
@Projection(name = "fullBill",types = Bill.class)
public interface BillProjection extends Projection {
	public Long getId();
	public Date getBillingDate();
	public Long getCustomerID();
	public Collection<ProductItem> getProductItems();
}


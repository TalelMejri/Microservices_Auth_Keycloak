package org.sid.entity;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullInventory",types = Product.class)
public interface ProductProjection extends Projection {
	public Long getId();
	public String getName();
	public double getPrice();
}

package com.bicgraphic.ods.order.model;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderBillings {
	
	@Valid
	@JsonProperty("OrderBilling")
	private List<OrderBilling> orderBilling = null;

	public List<OrderBilling> getOrderBilling() {
		return orderBilling;
	}

	public void setOrderBilling(List<OrderBilling> orderBilling) {
		this.orderBilling = orderBilling;
	}
	
	
}

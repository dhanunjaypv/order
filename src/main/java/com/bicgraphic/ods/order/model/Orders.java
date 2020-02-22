package com.bicgraphic.ods.order.model;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Orders {
	
	@Valid
	@JsonProperty("Order")
	private List<Order> Order = null;

	public List<Order> getOrder() {
		return Order;
	}

	public void setOrder(List<Order> order) {
		Order = order;
	}


}

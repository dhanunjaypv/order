package com.bicgraphic.ods.order.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderList {

	@JsonProperty("Orders")
	private List<PullOrders> orders;

	public List<PullOrders> getOrders() {
		return orders;
	}

	public void setOrders(List<PullOrders> orders) {
		this.orders = orders;
	}

}

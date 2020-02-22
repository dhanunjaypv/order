package com.bicgraphic.ods.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PullOrders {
	
	@JsonProperty("OrderData")
	private PullOrderData orderData;

	public PullOrderData getOrderData() {
		return orderData;
	}

	public void setOrderData(PullOrderData orderData) {
		this.orderData = orderData;
	}


}

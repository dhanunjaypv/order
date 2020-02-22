package com.bicgraphic.ods.order.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gowtham.murikipudi
 *
 */
public class PullOrderData {
	
	@JsonProperty("Order")
	private List<Order> order;

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

}

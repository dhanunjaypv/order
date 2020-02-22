package com.bicgraphic.ods.order.model;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Holds {
	
	@Valid
	@JsonProperty("HoldArray")
	private List<HoldArray> holdArray = null;

	public List<HoldArray> getHoldArray() {
		return holdArray;
	}

	public void setHoldArray(List<HoldArray> holdArray) {
		this.holdArray = holdArray;
	}

}

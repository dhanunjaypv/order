
package com.bicgraphic.ods.order.model;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PullRequest {
	
	@Valid
	@JsonProperty("EventRequest")
	private PullEventRequest pullEventRequest;

	public PullEventRequest getPullEventRequest() {
		return pullEventRequest;
	}

	public void setPullEventRequest(PullEventRequest pullEventRequest) {
		this.pullEventRequest = pullEventRequest;
	}

}

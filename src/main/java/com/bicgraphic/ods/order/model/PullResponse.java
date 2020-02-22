package com.bicgraphic.ods.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PullResponse {
	
	@JsonProperty("EventResponse")
	private PullEventResponse eventResponse;
	
	public PullResponse() {
		super();
	}

	public PullEventResponse getEventResponse() {
		return eventResponse;
	}

	public void setEventResponse(PullEventResponse eventResponse) {
		this.eventResponse = eventResponse;
	}

	public PullResponse(PullEventResponse eventResponse) {
		super();
		this.eventResponse = eventResponse;
	}
	
}

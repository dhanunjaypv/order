package com.bicgraphic.ods.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PushRequest {

	@JsonProperty("EventRequest")
	private Event eventRequest;

}

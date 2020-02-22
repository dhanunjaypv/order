
package com.bicgraphic.ods.order.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class PullEventRequest {

	@NotNull(message = "{validation.eventObject.notNull}")
	@JsonProperty("EventObject")
	private String eventObject;

	@NotNull(message = "{validation.eventType.notNull}")
	@JsonProperty("EventType")
	private String eventType;

	@NotNull(message = "{validation.eventDateTime.notNull}")
	@JsonProperty("EventDateTime")
	private String eventDateTime;

	@NotNull(message = "{validation.eventRequestSystem.notNull}")
	@JsonProperty("EventRequestSystem")
	private String eventRequestSystem;
	
	@NotNull(message = "{validation.eventSourceSystem.notNull}")
	@JsonProperty("EventSourceSystem")
	private String eventSourceSystem;

	@JsonProperty("EventSystemOfTruth")
	private String eventSystemOfTruth;

	@NotNull(message = "{validation.eventBusinessID.notNull}")
	@JsonProperty("EventBusinessID")
	private String eventBusinessID;

	@Valid
	@JsonProperty("RequestRecords")
	private RequestRecords requestRecords;

	public String getEventObject() {
		return eventObject;
	}

	public void setEventObject(String eventObject) {
		this.eventObject = eventObject;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventDateTime() {
		return eventDateTime;
	}

	public void setEventDateTime(String eventDateTime) {
		this.eventDateTime = eventDateTime;
	}

	public String getEventRequestSystem() {
		return eventRequestSystem;
	}

	public void setEventRequestSystem(String eventRequestSystem) {
		this.eventRequestSystem = eventRequestSystem;
	}

	public String getEventSystemOfTruth() {
		return eventSystemOfTruth;
	}

	public void setEventSystemOfTruth(String eventSystemOfTruth) {
		this.eventSystemOfTruth = eventSystemOfTruth;
	}

	public String getEventBusinessID() {
		return eventBusinessID;
	}

	public void setEventBusinessID(String eventBusinessID) {
		this.eventBusinessID = eventBusinessID;
	}

	public RequestRecords getRequestRecords() {
		return requestRecords;
	}

	public void setRequestRecords(RequestRecords requestRecords) {
		this.requestRecords = requestRecords;
	}

}

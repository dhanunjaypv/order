package com.bicgraphic.ods.order.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {
	
	@NotNull(message = "{validation.eventObject.notNull}")
	@JsonProperty("EventObject")
	private String eventObject = null;
	
	@NotNull(message = "{validation.eventType.notNull}")
	@JsonProperty("EventType")
	private String eventType = null;
	
	@NotNull(message = "{validation.eventDateTime.notNull}")
	@JsonProperty("EventDateTime")
	private String eventDateTime = null;

	@NotNull(message = "{validation.eventSourceSystem.notNull}")
	@JsonProperty("EventSourceSystem")
	private String eventSourceSystem = null;
	
	@NotNull(message = "{validation.eventBusinessID.notNull}")
	@JsonProperty("EventBusinessID")
	private String eventBusinessID = null;
	
	@Valid
	@JsonProperty("Orders")
	private Orders orders = null;

	@JsonProperty("EventDatabaseName")
	private String eventDatabaseName = null;

	@JsonProperty("EventCollectionName")
	private String eventCollectionName = null;

	public Event() {
		super();
	}

	public Event(String eventObject, String eventType, String eventDateTime, String eventSourceSystem, String eventBusinessID, Order order) {
		super();
		this.eventObject = eventObject;
		this.eventType = eventType;
		this.eventDateTime = eventDateTime;
		this.eventSourceSystem = eventSourceSystem;
		this.eventBusinessID = eventBusinessID;
		//this.order = order;
	}

	// This constructor used for to expose event object to event api
	public Event(String eventObject, String eventType, String eventDateTime, String eventSourceSystem, String eventBusinessID,
			String eventDatabaseName, String eventCollectionName) {
		super();
		this.eventObject = eventObject;
		this.eventType = eventType;
		this.eventDateTime = eventDateTime;
		this.eventSourceSystem = eventSourceSystem;
		this.eventBusinessID = eventBusinessID;
		this.eventDatabaseName = eventDatabaseName;
		this.eventCollectionName = eventCollectionName;
	}

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

	public String getEventSourceSystem() {
		return eventSourceSystem;
	}

	public void setEventSourceSystem(String eventSourceSystem) {
		this.eventSourceSystem = eventSourceSystem;
	}

	public String getEventBusinessID() {
		return eventBusinessID;
	}

	public void setEventBusinessID(String eventBusinessID) {
		this.eventBusinessID = eventBusinessID;
	}

	public String getEventDatabaseName() {
		return eventDatabaseName;
	}

	public void setEventDatabaseName(String eventDatabaseName) {
		this.eventDatabaseName = eventDatabaseName;
	}

	public String getEventCollectionName() {
		return eventCollectionName;
	}

	public void setEventCollectionName(String eventCollectionName) {
		this.eventCollectionName = eventCollectionName;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	
}

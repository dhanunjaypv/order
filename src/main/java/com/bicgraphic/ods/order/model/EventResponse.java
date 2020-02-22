package com.bicgraphic.ods.order.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventResponse {

	@JsonProperty("EnterpriseEventID")
	private String enterpriseEventID = "";

	@JsonProperty("EventBusinessID")
	private String eventBusinessID = "";

	@JsonProperty("EventStatus")
	private String eventStatus = "";

	@JsonProperty("EventStatusMessage")
	private String eventStatusMessage = "";

	@JsonProperty("MustRetryFlag")
	private String mustRetryFlag = "true";

	@JsonProperty("EventErrorCode")
	private String eventErrorCode = "";


	public EventResponse(String eventErrorCode, String eventStatusMessage) {
		this.eventErrorCode = eventErrorCode;
		this.eventStatusMessage = eventStatusMessage;
	}

	public String getEnterpriseEventID() {
		return enterpriseEventID;
	}

	public void setEnterpriseEventID(String enterpriseEventID) {
		this.enterpriseEventID = enterpriseEventID;
	}

	public String getEventBusinessID() {
		return eventBusinessID;
	}

	public void setEventBusinessID(String eventBusinessID) {
		this.eventBusinessID = eventBusinessID;
	}

	public String getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}

	public String getEventStatusMessage() {
		return eventStatusMessage;
	}

	public void setEventStatusMessage(String eventStatusMessage) {
		this.eventStatusMessage = eventStatusMessage;
	}

	public String getMustRetryFlag() {
		return mustRetryFlag;
	}

	public void setMustRetryFlag(String mustRetryFlag) {
		this.mustRetryFlag = mustRetryFlag;
	}

	public String getEventErrorCode() {
		return eventErrorCode;
	}

	public void setEventErrorCode(String eventErrorCode) {
		this.eventErrorCode = eventErrorCode;
	}

	public EventResponse() {
		super();
	}


	@Override
	public String toString() {
		return "ClassPojo [EventStatus = " + eventStatus + ", EventStatusMessage = " + eventStatusMessage + ", EnterpriseEventID = "
				+ enterpriseEventID + ", MustRetryFlag = " + mustRetryFlag + ", EventBusinessID = " + eventBusinessID + "]";
	}

}

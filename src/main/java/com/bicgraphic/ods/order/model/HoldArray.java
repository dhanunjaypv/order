package com.bicgraphic.ods.order.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HoldArray {

	@JsonProperty("At")
	private String at;

	@NotNull(message = "{validation.orderHoldStatus.notNull}")
	@JsonProperty("OrderHoldStatus")
	private String orderHoldStatus;

	@NotNull(message = "{validation.holdType.notNull}")
	@JsonProperty("HoldType")
	private String holdType;

	@NotNull(message = "{validation.orderHoldReason.notNull}")
	@JsonProperty("OrderHoldReason")
	private String orderHoldReason;

	@JsonProperty("Criteria")
	private String criteria;

	@JsonProperty("HoldUntil")
	private String holdUntil;

	@JsonProperty("AppliedDate")
	private String appliedDate;

	@JsonProperty("AppliedBy")
	private String appliedBy;

	@JsonProperty("ReleaseDate")
	private String releaseDate;

	@JsonProperty("ReleaseBy")
	private String releaseBy;

	@JsonProperty("ReleaseReason")
	private String releaseReason;

	public String getAt() {
		return at;
	}

	public void setAt(String at) {
		this.at = at;
	}

	public String getOrderHoldStatus() {
		return orderHoldStatus;
	}

	public void setOrderHoldStatus(String orderHoldStatus) {
		this.orderHoldStatus = orderHoldStatus;
	}

	public String getHoldType() {
		return holdType;
	}

	public void setHoldType(String holdType) {
		this.holdType = holdType;
	}

	public String getOrderHoldReason() {
		return orderHoldReason;
	}

	public void setOrderHoldReason(String orderHoldReason) {
		this.orderHoldReason = orderHoldReason;
	}

	public String getCriteria() {
		return criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public String getHoldUntil() {
		return holdUntil;
	}

	public void setHoldUntil(String holdUntil) {
		this.holdUntil = holdUntil;
	}

	public String getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getAppliedBy() {
		return appliedBy;
	}

	public void setAppliedBy(String appliedBy) {
		this.appliedBy = appliedBy;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getReleaseBy() {
		return releaseBy;
	}

	public void setReleaseBy(String releaseBy) {
		this.releaseBy = releaseBy;
	}

	public String getReleaseReason() {
		return releaseReason;
	}

	public void setReleaseReason(String releaseReason) {
		this.releaseReason = releaseReason;
	}

}

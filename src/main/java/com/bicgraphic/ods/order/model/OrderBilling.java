package com.bicgraphic.ods.order.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderBilling {

	@NotNull(message = "{validation.addressLine1.notNull}")
	@JsonProperty("AddressLine1")
	private String addressLine1;

	@JsonProperty("AddressLine2")
	private String addressLine2;

	@JsonProperty("AddressLine3")
	private String addressLine3;

	@JsonProperty("AddressLine4")
	private String addressLine4;

	@JsonProperty("County")
	private String county;

	@JsonProperty("Province")
	private String province;

	@NotNull(message = "{validation.city.notNull}")
	@JsonProperty("City")
	private String city;

	@JsonProperty("State")
	private String state;

	@JsonProperty("AddressSite")
	private String addressSite;

	@NotNull(message = "{validation.country.notNull}")
	@JsonProperty("Country")
	private String country;

}

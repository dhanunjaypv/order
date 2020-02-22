package com.bicgraphic.ods.order.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KeysValues {
	
	@NotNull(message = "{validation.keyName.notNull}")
	@JsonProperty("KeyName")
	private String keyName;
	
	@NotNull(message = "{validation.keyValue.notNull}")
	@JsonProperty("KeyValue")
	private String keyValue;

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

}

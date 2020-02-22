
package com.bicgraphic.ods.order.model;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestRecords {

	@Valid
	@JsonProperty("RequestRecord")
	private List<KeysValues> requestRecord = null;

	public List<KeysValues> getRequestRecord() {
		return requestRecord;
	}

	public void setRequestRecord(List<KeysValues> requestRecord) {
		this.requestRecord = requestRecord;
	}
	
}

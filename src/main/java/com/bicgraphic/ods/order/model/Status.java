package com.bicgraphic.ods.order.model;

public class Status {
	private String statusCode;
	private String message;
	
	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Status(String statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}

}

package com.bicgraphic.ods.order.Exceptions;

public class OdsOrderException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String orderErrorCode = null;
	private String orderErrorMessage = null;

	

	public OdsOrderException(String orderErrorCode, String orderErrorMessage) {
		super();
		this.orderErrorCode = orderErrorCode;
		this.orderErrorMessage = orderErrorMessage;
	}

	public String getOrderErrorCode() {
		return orderErrorCode;
	}

	public void setOrderErrorCode(String orderErrorCode) {
		this.orderErrorCode = orderErrorCode;
	}

	public String getOrderErrorMessage() {
		return orderErrorMessage;
	}

	public void setOrderErrorMessage(String orderErrorMessage) {
		this.orderErrorMessage = orderErrorMessage;
	}

}

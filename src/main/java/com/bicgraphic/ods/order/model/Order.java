package com.bicgraphic.ods.order.model;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bicgraphic.ods.order.constants.OdsConstants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = OdsConstants.ORDER_COLLECTION)
public class Order {

	@JsonIgnore
	@Id
	private String id;

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@NotNull(message = "{validation.currencyCode.notNull}")
	@JsonProperty("CurrencyCode")
	private String currencyCode;

	@NotNull(message = "{validation.orderNumber.notNull}")
	@JsonProperty("OrderNumber")
	private String orderNumber;

	@NotNull(message = "{validation.customerName.notNull}")
	@JsonProperty("CustomerName")
	private String customerName;

	@NotNull(message = "{validation.customerNumber.notNull}")
	@JsonProperty("CustomerNumber")
	private String customerNumber;

	@NotNull(message = "{validation.custPONumber.notNull}")
	@JsonProperty("CustPONumber")
	private String custPONumber;

	@NotNull(message = "{validation.expirationDate.notNull}")
	@JsonProperty("ExpirationDate")
	private String expirationDate;

	@NotNull(message = "{validation.orderDate.notNull}")
	@JsonProperty("OrderDate")
	private String orderDate;

	@JsonProperty("OrderHeaderCharges")
	private String orderHeaderCharges;

	@JsonProperty("Salesperson")
	private String salesperson;

	@JsonProperty("orderSubTotal")
	private String orderSubTotal;

	@NotNull(message = "{validation.orderHeaderStatus.notNull}")
	@JsonProperty("orderHeaderStatus")
	private String orderHeaderStatus;

	@JsonProperty("OrderHeaderTax")
	private String orderHeaderTax;

	@NotNull(message = "{validation.orderHeaderTotal.notNull}")
	@JsonProperty("orderHeaderTotal")
	private String orderHeaderTotal;

	@JsonProperty("OrderHeaderUSDCharges")
	private String orderHeaderUSDCharges;

	@JsonProperty("OrderHeaderUSDTax")
	private String orderHeaderUSDTax;

	@JsonProperty("orderHeaderUSDTotal")
	private String orderHeaderUSDTotal;

	@NotNull(message = "{validation.orderSourceName.notNull}")
	@JsonProperty("OrderSourceName")
	private String orderSourceName;

	@NotNull(message = "{validation.orderType.notNull}")
	@JsonProperty("OrderType")
	private String orderType;

	@JsonProperty("ReferenceOrder")
	private String referenceOrder;

	@Valid
	@JsonProperty("Holds")
	private Holds holds = null;

	@Valid
	@JsonProperty("OrderBillings")
	private OrderBillings orderBillings = null;

	@NotNull(message = "{validation.paymentTerms.notNull}")
	@JsonProperty("paymentTerms")
	private String paymentTerms;

	@NotNull(message = "{validation.paymentType.notNull}")
	@JsonProperty("paymentType")
	private String paymentType;

	@NotNull(message = "{validation.EBSOrderUrl.notNull}")
	@JsonProperty("EBSOrderUrl")
	private String ebsOrderUrl;

	@JsonProperty("RepeatOrderFlag")
	private String repeatOrderFlag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}

	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustPONumber() {
		return custPONumber;
	}

	public void setCustPONumber(String custPONumber) {
		this.custPONumber = custPONumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getOrderHeaderCharges() {
		return orderHeaderCharges;
	}

	public void setOrderHeaderCharges(String orderHeaderCharges) {
		this.orderHeaderCharges = orderHeaderCharges;
	}

	public String getSalesperson() {
		return salesperson;
	}

	public void setSalesperson(String salesperson) {
		this.salesperson = salesperson;
	}

	public String getOrderSubTotal() {
		return orderSubTotal;
	}

	public void setOrderSubTotal(String orderSubTotal) {
		this.orderSubTotal = orderSubTotal;
	}

	public String getOrderHeaderStatus() {
		return orderHeaderStatus;
	}

	public void setOrderHeaderStatus(String orderHeaderStatus) {
		this.orderHeaderStatus = orderHeaderStatus;
	}

	public String getOrderHeaderTax() {
		return orderHeaderTax;
	}

	public void setOrderHeaderTax(String orderHeaderTax) {
		this.orderHeaderTax = orderHeaderTax;
	}

	public String getOrderHeaderTotal() {
		return orderHeaderTotal;
	}

	public void setOrderHeaderTotal(String orderHeaderTotal) {
		this.orderHeaderTotal = orderHeaderTotal;
	}

	public String getOrderHeaderUSDCharges() {
		return orderHeaderUSDCharges;
	}

	public void setOrderHeaderUSDCharges(String orderHeaderUSDCharges) {
		this.orderHeaderUSDCharges = orderHeaderUSDCharges;
	}

	public String getOrderHeaderUSDTax() {
		return orderHeaderUSDTax;
	}

	public void setOrderHeaderUSDTax(String orderHeaderUSDTax) {
		this.orderHeaderUSDTax = orderHeaderUSDTax;
	}

	public String getOrderHeaderUSDTotal() {
		return orderHeaderUSDTotal;
	}

	public void setOrderHeaderUSDTotal(String orderHeaderUSDTotal) {
		this.orderHeaderUSDTotal = orderHeaderUSDTotal;
	}

	public String getOrderSourceName() {
		return orderSourceName;
	}

	public void setOrderSourceName(String orderSourceName) {
		this.orderSourceName = orderSourceName;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getReferenceOrder() {
		return referenceOrder;
	}

	public void setReferenceOrder(String referenceOrder) {
		this.referenceOrder = referenceOrder;
	}

	public Holds getHolds() {
		return holds;
	}

	public void setHolds(Holds holds) {
		this.holds = holds;
	}

	public OrderBillings getOrderBillings() {
		return orderBillings;
	}

	public void setOrderBillings(OrderBillings orderBillings) {
		this.orderBillings = orderBillings;
	}

	public String getPaymentTerms() {
		return paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getRepeatOrderFlag() {
		return repeatOrderFlag;
	}

	public void setRepeatOrderFlag(String repeatOrderFlag) {
		this.repeatOrderFlag = repeatOrderFlag;
	}

}
/**
 * 
 */
package com.bicgraphic.ods.order.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bicgraphic.ods.order.Exceptions.OdsOrderException;
import com.bicgraphic.ods.order.constants.OdsConstants;
import com.bicgraphic.ods.order.model.KeysValues;
import com.bicgraphic.ods.order.model.Order;
import com.bicgraphic.ods.order.model.OrderList;
import com.bicgraphic.ods.order.model.PullEventResponse;
import com.bicgraphic.ods.order.model.PullOrderData;
import com.bicgraphic.ods.order.model.PullOrders;
import com.bicgraphic.ods.order.model.PullRequest;
import com.bicgraphic.ods.order.model.PullResponse;
import com.bicgraphic.ods.order.repository.OrderRepository;

/**
 * @author dhanunjaya.potteti
 *
 */
@RestController
public class WebsiteOrderController {

	private static final Logger logger = LoggerFactory.getLogger(WebsiteOrderController.class);
	@Autowired
	private OrderRepository orderRepository;

	@RequestMapping(value = "/pullWebsiteOrders", consumes = { "application/json" }, produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<Object> pullWebsiteOrders(@Valid @RequestBody PullRequest pullRequest) {
		PullResponse pullResponse = new PullResponse();
		try {
			PullEventResponse pullEventResponse = new PullEventResponse();
			OrderList orderList = new OrderList();
			List<PullOrders> pullOrdersList = new ArrayList<PullOrders>();
			for (KeysValues KeysValues : pullRequest.getPullEventRequest().getRequestRecords().getRequestRecord()) {
				PullOrders pullOrders = new PullOrders();
				PullOrderData pullOrderData = new PullOrderData();
				List<Order> orders = new ArrayList<Order>();
				if (KeysValues.getKeyName().equalsIgnoreCase("OrderNumber")) {
					orders.addAll(orderRepository.findOrdersByOrderNumber(KeysValues.getKeyValue()));
				} else if (KeysValues.getKeyName().equalsIgnoreCase("customerNumber")) {
					orders.addAll(orderRepository.findOrdersByCustomerNumber(KeysValues.getKeyValue()));
				} else {
					throw new OdsOrderException(OdsConstants.ORDER_INV_INPUT_CODE, OdsConstants.ORDER_INV_INPUT_MSG);
				}
				pullOrderData.setOrder(orders);
				pullOrders.setOrderData(pullOrderData);
				pullOrdersList.add(pullOrders);
			}
			for (PullOrders pullOrders : pullOrdersList) {
				if(pullOrders.getOrderData().getOrder().size()==0) {
				//	throw new OdsOrderException(OdsConstants.ORDER_DATA_NOT_FOUND_MSG, OdsConstants.ORDER_DATA_NOT_FOUND_CODE);
				//	Need to Handle Error Message for Each KeyName (clarification Required)
				}
			}
			orderList.setOrders(pullOrdersList);
			pullEventResponse.setOrderList(orderList);
			pullResponse.setEventResponse(pullEventResponse);
		} catch (OdsOrderException ods) {
			throw new OdsOrderException(ods.getOrderErrorCode(), ods.getOrderErrorMessage());
		} catch (Exception e) {
			throw new OdsOrderException(OdsConstants.ORDER_INV_STRUCTURE_CODE, OdsConstants.ORDER_INV_STRUCTURE_MSG);
		}
		return new ResponseEntity<>(pullResponse, HttpStatus.OK);
	}
}

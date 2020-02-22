package com.bicgraphic.ods.order.controller;

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
import com.bicgraphic.ods.order.model.Event;
import com.bicgraphic.ods.order.model.EventResponse;
import com.bicgraphic.ods.order.model.Order;
import com.bicgraphic.ods.order.serviceImpl.SalesforceOrderServiceImpl;

@RestController
public class SalesforceOrderTransmissionApiController {
	private static final Logger logger = LoggerFactory.getLogger(SalesforceOrderTransmissionApiController.class);

	@Autowired
	SalesforceOrderServiceImpl salesforceOrderServiceImpl;

	@RequestMapping(value = "/salesforceOrderTransmissionProcess", consumes = {
			"application/json" }, produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<EventResponse> salesforceTransmissionProcess(@RequestBody Event eventRequest) throws Exception {
		String response = null;
		try {

			if (eventRequest != null) {
				Order order = eventRequest.getOrders().getOrder().get(0);
				boolean isMadarotrypams = mandatoryParams(eventRequest);
				if (isMadarotrypams) {
					String eventType = eventRequest.getEventType();
					response = salesforceOrderServiceImpl.postOrderMetaDataToSF(order);
					/* SECOND ITERATION */
					if (response == null || !response.equalsIgnoreCase(OdsConstants.ORDER_SUCCESS)) {
						throw new OdsOrderException(OdsConstants.ORDER_INV_INPUT_CODE, OdsConstants.ORDER_INV_INPUT_MSG);
					}

				} else {
					logger.error("Required paramaters missed in event request object");
					throw new OdsOrderException(OdsConstants.ORDER_MANDATORY_MISS_CODE, OdsConstants.ORDER_MANDATORY_MISS_MSG);
				}
			} else {
				logger.error("Order object is empty");
				throw new OdsOrderException(OdsConstants.ORDER_INV_STRUCTURE_CODE, OdsConstants.ORDER_INV_STRUCTURE_MSG);
			}
		} catch (OdsOrderException ods) {
			return new ResponseEntity<>(new EventResponse(ods.getOrderErrorCode(), ods.getOrderErrorMessage()), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new OdsOrderException(OdsConstants.ORDER_INV_STRUCTURE_CODE, OdsConstants.ORDER_INV_STRUCTURE_MSG);
		}
		throw new OdsOrderException(OdsConstants.ORDER_SUCCESS_CODE, OdsConstants.ORDER_SUCCESS_MSG);
	}

	public static boolean mandatoryParams(Event event) {
		boolean isMadatorparams = false;
		Order order = event.getOrders().getOrder().get(0);
		if (order.getOrderNumber() != null && event.getEventType() != null && event.getEventBusinessID() != null && event.getEventObject() != null
				&& event.getEventSourceSystem() != null) {
			isMadatorparams = true;
		}
		return isMadatorparams;
	}

}

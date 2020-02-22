package com.bicgraphic.ods.order.controller;

import java.io.File;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bicgraphic.ods.order.Exceptions.OdsOrderException;
import com.bicgraphic.ods.order.commons.APIInfo;
import com.bicgraphic.ods.order.constants.OdsConstants;
import com.bicgraphic.ods.order.model.Event;
import com.bicgraphic.ods.order.model.EventResponse;
import com.bicgraphic.ods.order.repository.OrderRepository;
import com.bicgraphic.ods.order.service.OrdersService;

@RestController
@RequestMapping("/order")
public class OrdersIngestionAPIController {

	private static final Logger logger = LoggerFactory.getLogger(OrdersIngestionAPIController.class);

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrdersService ordersService;

	@Autowired
	BuildProperties buildProperties;

	@RequestMapping(value = "/saveOrUpdateOrDelete", consumes = { "application/json" }, produces = "application/json", method = RequestMethod.POST)
	public ResponseEntity<EventResponse> store(@Valid @RequestBody Event eventRequest) throws Exception {

		String response = null;
		EventResponse eventResponse = new EventResponse();
		try {
			if (eventRequest != null) {
				response = ordersService.processOrder(eventRequest);
				if (response != null && response.equalsIgnoreCase(OdsConstants.ORDER_SUCCESS)) {
					eventResponse = ordersService.pushEventToODS(eventRequest);
				} else {
					return new ResponseEntity<>(new EventResponse(OdsConstants.ORDER_INV_INPUT_CODE, OdsConstants.ORDER_INV_INPUT_MSG),
							HttpStatus.OK);
				}
			} else {
				logger.error("Order object is empty");
				return new ResponseEntity<>(new EventResponse(OdsConstants.ORDER_INV_STRUCTURE_CODE, OdsConstants.ORDER_INV_STRUCTURE_MSG),
						HttpStatus.OK);
			}
		} catch (OdsOrderException ods) {
			return new ResponseEntity<>(new EventResponse(ods.getOrderErrorCode(), ods.getOrderErrorMessage()), HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(new EventResponse(OdsConstants.ORDER_INV_STRUCTURE_CODE, OdsConstants.ORDER_INV_STRUCTURE_MSG),
					HttpStatus.OK);
		}
		eventResponse.setEventErrorCode(OdsConstants.ORDER_SUCCESS_CODE);
		eventResponse.setEventStatusMessage(OdsConstants.ORDER_SUCCESS);
		eventResponse.setMustRetryFlag("false");
		eventResponse.setEventBusinessID(eventRequest.getEventBusinessID());
		return new ResponseEntity<>(eventResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/version", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<Object> getVersion() throws Exception {
		APIInfo apiInfo = new APIInfo();
		apiInfo.setBuildNumber(buildProperties.getVersion());
		return new ResponseEntity<Object>(apiInfo, HttpStatus.OK);
	}

	@RequestMapping(value = "/diagnostics", produces = "application/json", method = RequestMethod.GET)
	public ResponseEntity<Object> getDiagnostics() throws Exception {
		APIInfo apiInfo = new APIInfo();
		StringBuilder sb = new StringBuilder();
		sb.append("MongoDB Connection Good.");
		File file = ResourceUtils.getFile("classpath:ods_event.properties");
		if (file.exists()) {
			sb.append("ods_events.properties File available.");
		} else {
			sb.append("Required ods_events.properties File not available.");
		}
		apiInfo.setMessage(sb.toString());
		return new ResponseEntity<Object>(apiInfo, HttpStatus.OK);
	}

}

package com.bicgraphic.ods.order.serviceImpl;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bicgraphic.ods.order.Exceptions.OdsOrderException;
import com.bicgraphic.ods.order.commons.EventProperties;
import com.bicgraphic.ods.order.constants.OdsConstants;
import com.bicgraphic.ods.order.model.Event;
import com.bicgraphic.ods.order.model.EventRequest;
import com.bicgraphic.ods.order.model.EventResponse;
import com.bicgraphic.ods.order.model.Order;
import com.bicgraphic.ods.order.repository.OrderRepository;
import com.bicgraphic.ods.order.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {

	private static final Logger logger = LoggerFactory.getLogger(OrdersServiceImpl.class);

	@Autowired
	OrderRepository OrderRepository;

	@Autowired
	private EventProperties eventProperties;

	@Override
	public String processOrder(Event eventOrderRequest) throws Exception {
		Order order = eventOrderRequest.getOrders().getOrder().get(0);
		//Need Clarification on Exception Handling while processing Multiple Orders
		String response = OdsConstants.ORDER_SUCCESS;
		try {
			if (eventOrderRequest.getEventType().equals(OdsConstants.ORDER_POST_OPERATION)) {
				if (fetchByOrderNumber(order.getOrderNumber()) == null) {
					OrderRepository.save(order);
				} else {
					throw new OdsOrderException(OdsConstants.ORDER_ALREADYEXITS_CODE, OdsConstants.ORDER_ALREADYEXITS_MSG);
				}
			} else if (eventOrderRequest.getEventType().equals(OdsConstants.ORDER_UPDATE_OPERATION)) {
				Order orderData = fetchByOrderNumber(order.getOrderNumber());
				if (orderData != null) {
					order.setId(orderData.getId());
					OrderRepository.save(order);
				} else {
					throw new OdsOrderException(OdsConstants.ORDER_DOESNOTEXITS_CODE, OdsConstants.ORDER_DOESNOTEXITS_MSG);
				}
			} else if (eventOrderRequest.getEventType().equals(OdsConstants.ORDER_DELETE_OPERATION)) {
				Order orderData = fetchByOrderNumber(order.getOrderNumber());
				if (orderData != null) {
					orderData.setOrderHeaderStatus("CANCELLED");
					OrderRepository.save(orderData);
				} else {
					throw new OdsOrderException(OdsConstants.ORDER_DOESNOTEXITS_CODE, OdsConstants.ORDER_DOESNOTEXITS_MSG);
				}
			} else {
				throw new OdsOrderException(OdsConstants.ORDER_INV_INPUT_CODE, OdsConstants.ORDER_INV_INPUT_MSG);
			}
		} catch (OdsOrderException e) {
			throw new OdsOrderException(e.getOrderErrorCode(), e.getOrderErrorMessage());
		} catch (Exception e) {
			logger.error("Exception occured - OrderCUD():{}", e);
			response = "Order post operation failed";
			throw new OdsOrderException(OdsConstants.ORDER_INV_INPUT_CODE, OdsConstants.ORDER_INV_INPUT_MSG);
		}
		return response;
	}

	@Override
	public EventResponse pushEventToODS(Event event) throws OdsOrderException {
		EventResponse eventResponse;
		EventRequest eventApiInput;
		try {
			String eventRestEndpoint = eventProperties.getEventEndpoint();
			eventApiInput = new EventRequest(event.getEventObject(), event.getEventType(), event.getEventDateTime(), event.getEventSourceSystem(),
					event.getEventBusinessID(), event.getEventDatabaseName(), event.getEventCollectionName());
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			HttpEntity<EventRequest> entity = new HttpEntity<EventRequest>(eventApiInput, headers);
			eventResponse = restTemplate.exchange(eventRestEndpoint, HttpMethod.POST, entity, EventResponse.class).getBody();
		} catch (Exception e) {
			logger.error("Exception occured -  pushEventToODS(Event eventRequest) in OrderAccountIngestionAPI{}", e);
			throw new OdsOrderException(OdsConstants.ORDER_EVENT_NETWORK_ERR_CODE, OdsConstants.ORDER_EVENT_NETWORK_ERR_MSG);
		}
		return eventResponse;
	}

	@Override
	public Order fetchByOrderNumber(String orderNumber) throws Exception {
		Order order = null;
		try {
			if (orderNumber != null) {
				order = OrderRepository.findByOrderNumber(orderNumber);
			} else {
				logger.info("Order Number is null");
			}
		} catch (Exception e) {
			throw new OdsOrderException(OdsConstants.ORDER_DOESNOTEXITS_CODE, OdsConstants.ORDER_DOESNOTEXITS_MSG);
		}
		return order;
	}

}

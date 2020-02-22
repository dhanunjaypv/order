package com.bicgraphic.ods.order.service;

import com.bicgraphic.ods.order.Exceptions.OdsOrderException;
import com.bicgraphic.ods.order.model.Event;
import com.bicgraphic.ods.order.model.EventResponse;
import com.bicgraphic.ods.order.model.Order;

public interface OrdersService {

	public String processOrder(Event eventOrderRequest) throws Exception;

	Order fetchByOrderNumber(String orderNumber) throws Exception;

	public EventResponse pushEventToODS(Event event) throws OdsOrderException;

}

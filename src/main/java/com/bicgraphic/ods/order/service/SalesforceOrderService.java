package com.bicgraphic.ods.order.service;

import com.bicgraphic.ods.order.Exceptions.OdsOrderException;
import com.bicgraphic.ods.order.model.Order;

public interface SalesforceOrderService {
	public String postOrderMetaDataToSF(Order order) throws OdsOrderException;
}

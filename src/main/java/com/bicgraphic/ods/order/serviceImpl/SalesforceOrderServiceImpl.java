package com.bicgraphic.ods.order.serviceImpl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bicgraphic.ods.order.Exceptions.OdsOrderException;
import com.bicgraphic.ods.order.commons.SalesForceProperties;
import com.bicgraphic.ods.order.constants.OdsConstants;
import com.bicgraphic.ods.order.model.Order;
import com.bicgraphic.ods.order.service.SalesforceOrderService;
import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.SaveResult;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

@Service
public class SalesforceOrderServiceImpl implements SalesforceOrderService {
	private static final Logger logger = LoggerFactory.getLogger(SalesforceOrderServiceImpl.class);

	@Autowired
	SalesForceProperties salesForceProperties;

	public PartnerConnection getPartnerConnection() throws OdsOrderException {
		ConnectorConfig config = new ConnectorConfig();
		// config.setUsername("svc_integration@bicgraphic.com.partial");
		// config.setPassword("$icIntegr8");
		config.setUsername(salesForceProperties.getSalesforceUsername());
		config.setPassword(salesForceProperties.getSalesforcePassword() + salesForceProperties.getSalesforceSecurityToken());
		config.setAuthEndpoint(salesForceProperties.getSalesforceEndpoint());
		PartnerConnection connection = null;
		try {
			connection = Connector.newConnection(config);
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			logger.error("Exception in connecting to salesforce getPartnerConnection():" + e.getMessage());
			throw new OdsOrderException(OdsConstants.ORDER_SALESFORCE_CONNECTION_FAILURE_CODE, OdsConstants.ORDER_SALESFORCE_CONNECTION_FAILURE_MSG);
		}
		return connection;
	}

	@Override
	public String postOrderMetaDataToSF(Order argOrder) throws OdsOrderException {
		PartnerConnection connection = getPartnerConnection();
		SObject order = new SObject();
		order.setType("Order");
		order.setField("AccountId", "0011U000005AaYz");
		order.setField("Order_Source_Name__c", argOrder.getOrderSourceName());
		order.setField("Order_Type__c", argOrder.getOrderType());
		order.setField("Currency__c", argOrder.getCurrencyCode());
		order.setField("Customer_PO__c", argOrder.getCustPONumber());
		order.setField("EffectiveDate", new Date());
		order.setField("Status", "Draft");
		try {
			com.sforce.soap.partner.SaveResult[] results = connection.create(new SObject[] { order });
			for (SaveResult result : results) {
				if (result.isSuccess()) {
					return OdsConstants.ORDER_SUCCESS;
				} else {
					com.sforce.soap.partner.Error err = result.getErrors()[0];
					throw new OdsOrderException(err.getStatusCode().name(), err.getMessage());
				}
			}
		} catch (ConnectionException e) {
			// TODO Auto-generated catch block
			logger.error("Exception in connecting to salesforce method postOrderMetaDataToSF():" + e.getMessage());
			throw new OdsOrderException(OdsConstants.ORDER_SALESFORCE_CONNECTION_FAILURE_CODE, OdsConstants.ORDER_SALESFORCE_CONNECTION_FAILURE_MSG);
		}
		return OdsConstants.ORDER_SUCCESS;

	}

}

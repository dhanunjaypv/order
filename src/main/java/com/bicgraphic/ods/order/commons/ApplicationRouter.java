package com.bicgraphic.ods.order.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.bicgraphic.ods.order.Exceptions.OdsOrderException;
import com.bicgraphic.ods.order.constants.OdsConstants;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ApplicationRouter {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationRouter.class);

	@Autowired
	private EventProperties EventProperties;

	/*
	 * It will invoke external service with Request body & Return RouterResponse
	 * 
	 * @Request:serviceName, it have specific service value
	 * 
	 * @Request:requestObj , it post request Body
	 * 
	 * @Return: routerResponse, this response coming from external service
	 */
	public RouterResponse invokePostService(String serviceName, Object requestObj) {
		logger.info("Router Request {}#", requestObj);
		RouterResponse routerResponse = null;
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			ResponseEntity<String> eventResponse = restTemplate.exchange(EventProperties.getEventEndpoint() + serviceName, HttpMethod.POST,
					new HttpEntity<>(requestObj, prepareHttpHeaders()), String.class);
			if (eventResponse.getStatusCode() == HttpStatus.OK) {
				logger.debug("Event API post() service successfully done");

				routerResponse = objectMapper.readValue(eventResponse.getBody(), RouterResponse.class);
				logger.info("Router Response {}#", routerResponse);
			}
		} catch (HttpStatusCodeException e) {
			logger.error("Router Bad Response {}#", e.getResponseBodyAsString());
			// routerResponse=objectMapper.readValue(e.getResponseBodyAsString(),RouterResponse.class);

			throw new OdsOrderException(OdsConstants.ORDER_EVENT_NETWORK_ERR_CODE, "EVENT RESP:" + e.getResponseBodyAsString());

		} catch (Exception e) {

			throw new OdsOrderException(OdsConstants.ORDER_INV_STRUCTURE_CODE, "Data Convertion failed");

		}
		return routerResponse;
	}

	/*
	 * It will prepare Http headers
	 * 
	 * @Return : headers
	 */
	private HttpHeaders prepareHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		return headers;
	}
}

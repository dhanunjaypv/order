package com.bicgraphic.ods.order.commons;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:sf_configuration.properties") 
public class SalesForceProperties {
	
	
	@Value("${salesforce.org.username}")
	private String salesforceUsername;
	
	@Value("${salesforce.org.password}")
	private String salesforcePassword;
	
	@Value("${salesforce.org.securityToken}")
	private String salesforceSecurityToken;
	
	@Value("${salesforce.org.endpoint}")
	private String salesforceEndpoint;

	public String getSalesforceUsername() {
		return salesforceUsername;
	}

	public void setSalesforceUsername(String salesforceUsername) {
		this.salesforceUsername = salesforceUsername;
	}

	public String getSalesforcePassword() {
		return salesforcePassword;
	}

	public void setSalesforcePassword(String salesforcePassword) {
		this.salesforcePassword = salesforcePassword;
	}

	public String getSalesforceSecurityToken() {
		return salesforceSecurityToken;
	}

	public void setSalesforceSecurityToken(String salesforceSecurityToken) {
		this.salesforceSecurityToken = salesforceSecurityToken;
	}

	public String getSalesforceEndpoint() {
		return salesforceEndpoint;
	}

	public void setSalesforceEndpoint(String salesforceEndpoint) {
		this.salesforceEndpoint = salesforceEndpoint;
	}
	
}

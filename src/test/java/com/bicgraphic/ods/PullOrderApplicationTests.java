
package com.bicgraphic.ods;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;

import com.bicgraphic.ods.order.OrderApplication;
import com.bicgraphic.ods.order.model.PullRequest;
import com.bicgraphic.ods.order.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = OrderApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PullOrderApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	/*
	 * validate with valid input weather we will get proper values or not
	 */
	@Test
	public void testGetOrder() throws Exception {
		
		File file = ResourceUtils.getFile("classpath:order_pull_testdata.json");
		ObjectMapper mapper = new ObjectMapper();
		PullRequest pullRequest = mapper.readValue(file, PullRequest.class);
		String pullRequestJson = mapper.writeValueAsString(pullRequest);
		mockMvc.perform(post("/pullWebsiteOrders").contentType(MediaType.APPLICATION_JSON).content(pullRequestJson)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$..*OrderNumber").value("ABCD"))
				.andExpect(jsonPath("$..*CustomerNumber").value("ABCDEFGHIJKLMNOP"));

	}

	@Test
	public void testGetOrder2() throws Exception {

		File file = ResourceUtils.getFile("classpath:order_pull_testdata.json");
		ObjectMapper mapper = new ObjectMapper();
		PullRequest pullRequest = mapper.readValue(file, PullRequest.class);
		pullRequest.getPullEventRequest().getRequestRecords().getRequestRecord().get(0).setKeyName("ABC");
		String pullRequestJson = mapper.writeValueAsString(pullRequest);
		mockMvc.perform(post("/pullWebsiteOrders").contentType(MediaType.APPLICATION_JSON).content(pullRequestJson)).andDo(print())
				.andExpect(jsonPath("$.EventErrorCode").value("INTERR-113")).andExpect(jsonPath("$.EventStatusMessage").value("Incorrect request field value"));

	}

	@Test
	public void testGetOrder3() throws Exception {

		File file = ResourceUtils.getFile("classpath:order_pull_testdata.json");
		ObjectMapper mapper = new ObjectMapper();
		PullRequest pullRequest = mapper.readValue(file, PullRequest.class);
		pullRequest.getPullEventRequest().getRequestRecords().getRequestRecord().get(0).setKeyName(null);
		String pullRequestJson = mapper.writeValueAsString(pullRequest);
		mockMvc.perform(post("/pullWebsiteOrders").contentType(MediaType.APPLICATION_JSON).content(pullRequestJson)).andDo(print())
				.andExpect(jsonPath("$.EventErrorCode").value("INTERR-112")).andExpect(jsonPath("$.EventStatusMessage").value("[keyName cannot be Null]"));

	}

}

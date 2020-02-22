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
import com.bicgraphic.ods.order.model.Event;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = OrderApplication.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderIngestionAPITests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testSaveOrder1() throws Exception {

		File file = ResourceUtils.getFile("classpath:Order_testdata.json");
		ObjectMapper mapper = new ObjectMapper();
		Event event = mapper.readValue(file, Event.class);
		String eventJson = mapper.writeValueAsString(event);
		mockMvc.perform(post("/order/saveOrUpdateOrDelete").contentType(MediaType.APPLICATION_JSON).content(eventJson)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.EventErrorCode").value("INTERR-000"))
				.andExpect(jsonPath("$.EventStatusMessage").value("SUCCESS"));
	}

	@Test
	public void testSaveOrder2() throws Exception {

		File file = ResourceUtils.getFile("classpath:Order_testdata.json");
		ObjectMapper mapper = new ObjectMapper();
		Event event = mapper.readValue(file, Event.class);
		String eventJson = mapper.writeValueAsString(event);
		mockMvc.perform(post("/order/saveOrUpdateOrDelete").contentType(MediaType.APPLICATION_JSON).content(eventJson)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.EventErrorCode").value("INTERR-003"))
				.andExpect(jsonPath("$.EventStatusMessage").value("Order Already Exits"));
	}

	@Test
	public void testSaveOrder3() throws Exception {

		File file = ResourceUtils.getFile("classpath:Order_testdata.json");
		ObjectMapper mapper = new ObjectMapper();
		Event event = mapper.readValue(file, Event.class);
		event.getOrders().getOrder().get(0).setOrderNumber(null);
		String eventJson = mapper.writeValueAsString(event);
		mockMvc.perform(post("/order/saveOrUpdateOrDelete").contentType(MediaType.APPLICATION_JSON).content(eventJson)).andDo(print())
				.andExpect(status().isBadRequest()).andExpect(jsonPath("$.EventErrorCode").value("INTERR-112"))
				.andExpect(jsonPath("$.EventStatusMessage").value("[orderNumber cannot be Null]"));
	}

	@Test
	public void testzDeleteOrder1() throws Exception {

		File file = ResourceUtils.getFile("classpath:Order_testdata.json");
		ObjectMapper mapper = new ObjectMapper();
		Event event = mapper.readValue(file, Event.class);
		event.setEventType("DELETE");
		String eventJson = mapper.writeValueAsString(event);
		mockMvc.perform(post("/order/saveOrUpdateOrDelete").contentType(MediaType.APPLICATION_JSON).content(eventJson)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.EventErrorCode").value("INTERR-000"))
				.andExpect(jsonPath("$.EventStatusMessage").value("SUCCESS"));
	}

	@Test
	public void testzDeleteOrder2() throws Exception {

		File file = ResourceUtils.getFile("classpath:Order_testdata.json");
		ObjectMapper mapper = new ObjectMapper();
		Event event = mapper.readValue(file, Event.class);
		event.setEventType("DELETE");
		event.getOrders().getOrder().get(0).setOrderNumber("11111");
		String eventJson = mapper.writeValueAsString(event);
		mockMvc.perform(post("/order/saveOrUpdateOrDelete").contentType(MediaType.APPLICATION_JSON).content(eventJson)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.EventErrorCode").value("INTERR-004"))
				.andExpect(jsonPath("$.EventStatusMessage").value("Order Does not Exits"));
	}

}

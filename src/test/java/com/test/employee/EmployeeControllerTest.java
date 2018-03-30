/**
 * 
 */
package com.test.employee;

/**
 * @author Anita
 * Test class to test the employee controller
 * methods included to test Get,Post,Put , Delete
 * and bad request.
 * 
 *
 */

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.test.employee.EmployeeRestServiceApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EmployeeRestServiceApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmployeeControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	
	@Test
	public void testEmployees() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/employees/").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(4))).andDo(print());
	}

	@Test
	public void testEmployeeById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/employees/1001").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.name").exists())
				.andExpect(jsonPath("$.salary").exists()).andExpect(jsonPath("$.id").value(1001))
				.andExpect(jsonPath("$.name").value("Jason")).andExpect(jsonPath("$.salary").value(2000))
				.andDo(print());
	}

	@Test
	public void testInvalidEmployeeId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/employees/test").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest()).andDo(print());
	}

	@Test
	public void testAddEmployee() throws Exception {
		String testEmployeeJson = "{\"id\":\"2001\",\"name\":\"Sam\",\"salary\":\"5000\"}";
		mockMvc.perform(MockMvcRequestBuilders.post("/employees/").contentType(MediaType.APPLICATION_JSON)
				.content(testEmployeeJson).accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.name").exists()).andExpect(jsonPath("$.salary").exists())
				.andExpect(jsonPath("$.id").value(2001)).andExpect(jsonPath("$.name").value("Sam"))
				.andExpect(jsonPath("$.salary").value(5000)).andDo(print());
	}

	@Test
	public void testUpdateEmployee() throws Exception {
		String testEmployeeJson = "{\"id\":\"2001\",\"name\":\"Samuel\",\"salary\":\"4500\"}";
		mockMvc.perform(MockMvcRequestBuilders.put("/employees/2001").contentType(MediaType.APPLICATION_JSON)
				.content(testEmployeeJson).accept(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.name").exists()).andExpect(jsonPath("$.salary").exists())
				.andExpect(jsonPath("$.id").value(2001)).andExpect(jsonPath("$.name").value("Samuel"))
				.andExpect(jsonPath("$.salary").value(4500)).andDo(print());
	}

	@Test
	public void testDeleteEmployee() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/employees/2001").accept(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}

package com.fizzbuzz.apirest.controllers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class FizzBuzzControllerIntegrationTests {

	@Autowired
	private FizzBuzzController fizzBuzzController;

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testHelloEndpointIsOK() throws Exception {
		this.mockMvc.perform(get("/fizzbuzz/100")).andExpect(status().isOk());
	}

	@Test
	void fizzBuzzDoesNotThrow() {
		assertDoesNotThrow(() -> {
			for (int i = 1; i <= 200; i++) {
				fizzBuzzController.fizzBuzz(10, null);
			}
		});

	}

}

package com.fizzbuzz.apirest.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class FizzBuzzControllerTests {

	@Autowired
	private FizzBuzzController fizzBuzzController;
	
	@Test
	 void fizzBuzzControllerIsNotNull() {
	  assertThat(fizzBuzzController).isNotNull();
	 }
	
	@Test
	void fizzBuzzHTTPCodeOk() {		
		ResponseEntity<String> response = fizzBuzzController.fizzBuzz(90, null);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	void fizzBuzzThrow() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			fizzBuzzController.fizzBuzz(-10, null);
	    });
		String expectedMessage = "Please try again with an positive integer value.";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void fizzBuzzNotNull() {		
		ResponseEntity<String> response = fizzBuzzController.fizzBuzz(100, null);
		assertNotNull(response.getBody());
	}

}

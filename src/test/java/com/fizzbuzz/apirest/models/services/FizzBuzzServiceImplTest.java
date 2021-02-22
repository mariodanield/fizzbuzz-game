package com.fizzbuzz.apirest.models.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;

@SpringBootTest
class FizzBuzzServiceImplTest {
	
	@Autowired
	IFizzBuzzService fizzBuzzService;

	@Test
	void testRunGame() throws JsonProcessingException, InterruptedException, ExecutionException {
		assertNotNull(fizzBuzzService);
		assertDoesNotThrow(() -> {
			fizzBuzzService.runGame(10);
	    });
		assertNotNull(fizzBuzzService.runGame(10));
		assertNotNull(fizzBuzzService.runGame(10).get());
		
		Exception exception = assertThrows(ExecutionException.class, () -> {
			fizzBuzzService.runGame(-10).get();
	    });
		String expectedMessage = "Format number incorrect.";
		String actualMessage = exception.getMessage();
		assertTrue(actualMessage.contains(expectedMessage));
	}

}

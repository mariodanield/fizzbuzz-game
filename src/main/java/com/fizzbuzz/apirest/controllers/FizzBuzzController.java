package com.fizzbuzz.apirest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fizzbuzz.apirest.exception.CustomNullPointerException;
import com.fizzbuzz.apirest.models.services.IFizzBuzzService;

import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

//Controlador para lanzar el juego de fizzbuzz
@RestController
public class FizzBuzzController {
	private static final Logger logger = LogManager.getLogger(FizzBuzzController.class);

	@Autowired
	IFizzBuzzService fizzBuzzService;

	@RequestMapping(value = "/fizzbuzz/{startNumber}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> fizzBuzz(@PathVariable("startNumber") int startNumber, HttpServletResponse response) {
		logger.info("Start service rest /fizzbuzz/{startNumber}.");
		try {
			if (startNumber<0) {
				throw new IllegalArgumentException("Please try again with an positive integer value.");
			}
			logger.info("End service rest /fizzbuzz/{startNumber}.");
			return new ResponseEntity<String>(fizzBuzzService.runGame(startNumber).get(), HttpStatus.OK);
		} catch (CustomNullPointerException e) {
			logger.error(e.getMessage());
		} catch (JsonProcessingException e) {
			logger.warn(e.getMessage());
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		} catch (ExecutionException e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Devuelve este mensaje de error si se proporciona un valor no entero negativo para startNumber.
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public void handleParameterTypeMismatch(IllegalArgumentException e, HttpServletResponse response)
			throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value(), "Please try again with an positive integer value.");
	}

	// Devuelve este mensaje de error si se produce un error durante el parseo del json.
	@ExceptionHandler(JsonProcessingException.class)
	public void handleSerializationError(JsonProcessingException e, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"There was an error serializing an object to JSON.");
	}
	
	
	
	
}
package com.fizzbuzz.apirest.models.services;

import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IFizzBuzzService {

	public CompletableFuture<String> runGame(int startNumber) throws JsonProcessingException;
	
}

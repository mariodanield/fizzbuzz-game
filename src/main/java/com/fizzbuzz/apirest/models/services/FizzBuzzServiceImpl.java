package com.fizzbuzz.apirest.models.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fizzbuzz.apirest.models.entity.FizzBuzz;
import com.fizzbuzz.apirest.util.DateConvert;
import com.fizzbuzz.apirest.util.FileManager;

@Service
public class FizzBuzzServiceImpl implements IFizzBuzzService{
	private static final Logger logger = LogManager.getLogger(FizzBuzzServiceImpl.class);

	@Value("${end.number}")
	int endNumber;

	@Autowired
	FileManager fileManager;

	/**
	 * Recorrer un rango de numeros buscando las coincidencias con fizz buzz y
	 * fizzbuzz y guardar el resultado en un archivo.
	 * 
	 * @param startNumber número de inicio para rango de números del juego.
	 * @param c
	 * @return CompletableFuture<String> resultado del juego
	 * @throws JsonProcessingException error en parseo de json
	 */
	@Async
	public CompletableFuture<String> runGame(int startNumber) throws JsonProcessingException, NumberFormatException {
		logger.info("Start function runGame().");
		FizzBuzz fizzBuzzObj = new FizzBuzz();
		List<Integer> fizzList = new ArrayList<>(), buzzList = new ArrayList<>(), fizzBuzzList = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		if(startNumber<0) {
			throw new NumberFormatException("Format number incorrect.");
		}
		// Bucle que recorre un rango de numeros
		IntStream.range(startNumber, endNumber + 1).forEach(i -> {
			switch (CategorizerChain.Calculate(i)) {
			case "Buzz":
				buzzList.add(i);
				break;
			case "Fizz":
				fizzList.add(i);
				break;
			case "FizzBuzz":
				fizzBuzzList.add(i);
				break;
			}
		});

		fizzBuzzObj.setFizz(fizzList);
		fizzBuzzObj.setBuzz(buzzList);
		fizzBuzzObj.setFizzBuzz(fizzBuzzList);

		synchronized (this) {
			fileManager.addToWrite(DateConvert.formatDateToString(new Date()) + " -- "
					+ objectMapper.writeValueAsString(fizzBuzzObj));
			fileManager.save();
		}
		logger.info("End function runGame().");
		return CompletableFuture.completedFuture(objectMapper.writeValueAsString(fizzBuzzObj));

	}

}

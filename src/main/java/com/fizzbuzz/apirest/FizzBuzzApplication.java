package com.fizzbuzz.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

//Configuración spring boot y archivo properties
@SpringBootApplication
@PropertySource("classpath:config/fizzbuzz.properties")
public class FizzBuzzApplication {

	public static void main(String[] args) {
		SpringApplication.run(FizzBuzzApplication.class, args);
	}

}

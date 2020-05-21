package com.tak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author taak
 *
 *Shopping cart microservice
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
public class CartApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CartApplication.class, args);
	}

}

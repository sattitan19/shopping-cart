package com.tak.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tak.model.ProductRequest;
import com.tak.model.Response;

/**
 * 
 * @author taak
 *
 *Controller to be called to publish to messaging queue 
 */
@RestController
@RequestMapping("/addToCart")
public class CartController {

	Logger logger = LoggerFactory.getLogger(CartController.class);
	
	@Value("${app.exchange.name}")
	private String appExchange;
	@Value("${app.routing.key}")
	private String appRoutingKey;

	private final RabbitTemplate rabbitTemplate;
	
	public CartController(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	/**
	 * 
	 * Method to be called when a product is added to cart
	 * 
	 * @param product
	 * @return Response
	 * 
	 */
	@RequestMapping (method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Response addToCart(@RequestBody ProductRequest product) {
		logger.info("Add product to cart : "+product);
		rabbitTemplate.convertAndSend(appExchange, appRoutingKey, product);
		logger.info("Product published to messaging queue.");
		Response response=new Response();
		response.setErrorCode("0");
		return response;
	}

}

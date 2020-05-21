package com.tak.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tak.entity.Cart;
import com.tak.model.ProductRequest;
import com.tak.repo.CartRepo;

@Component
public class CartQueueSubscriber {

	Logger logger = LoggerFactory.getLogger(CartQueueSubscriber.class);
	
	private final CartRepo cartRepo;
	
	public CartQueueSubscriber(CartRepo cartRepo) {
		this.cartRepo=cartRepo;
	}

	public void receiveMessage(ProductRequest product) {
		logger.info("Recieved Message From RabbitMQ: " + product);
		Cart cart=new Cart();
		cart.setProductName(product.getProductName());
		cart.setProductQuantity(product.getProductQuantity());
		cart.setUserId(product.getUserId());
		cartRepo.save(cart);
		logger.info("Product saved to  cart.");
	}


}

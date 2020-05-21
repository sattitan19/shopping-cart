package com.tak.cart;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tak.entity.Cart;
import com.tak.model.ProductRequest;
import com.tak.repo.CartRepo;

@SpringBootTest
@AutoConfigureMockMvc
class CartApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private CartRepo cartRepo;

	@Test
	@Order(1)
	public void addToCartController() throws Exception {
		ProductRequest p = new ProductRequest();
		p.setProductName("mobile");
		p.setProductQuantity(2);
		mockMvc.perform(post("/addToCart").contentType(MediaType.APPLICATION_JSON).content(asJsonString(p)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@Test
	@Order(2)
    public void testCartRepo() throws Exception {
		Cart c = new Cart();
		c.setProductName("mobile");
		c.setProductQuantity(2);
		cartRepo.save(c);
    }

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}

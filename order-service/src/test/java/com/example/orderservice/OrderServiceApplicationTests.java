package com.example.orderservice;

import com.example.orderservice.controller.OrderController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class OrderServiceApplicationTests {

	@Autowired
	private OrderController orderController;

	@Test
	void contextLoads() {
		assertThat(orderController).isNotNull();
	}

}

package com.example.productservice;

import com.example.productservice.grpc.ProductRPCServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ProductServiceApplicationTests {

	@Autowired
	private ProductRPCServer productRPCServer;

	@Test
	void contextLoads() {
		assertThat(productRPCServer).isNotNull();
	}

}

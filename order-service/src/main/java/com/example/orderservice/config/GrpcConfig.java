package com.example.orderservice.config;

import com.example.orderservice.exception.OrderProductOutOfStockException;
import com.example.orderservice.utils.grpc.ExceptionMapperGrpcStubExecutor;
import com.example.orderservice.utils.grpc.GrpcStubExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class GrpcConfig {

    @Bean
    GrpcStubExecutor exceptionMapperGrpcStubExecutor() {
        return new ExceptionMapperGrpcStubExecutor(Map.of(
                "error.out_of_stock.order_product", errorInfo -> {
                    long shopId = Long.parseLong(errorInfo.getMetadataOrThrow("shop_id"));
                    return new OrderProductOutOfStockException(shopId);
                }
        ), () -> new RuntimeException("Unknown exception"));
    }

}

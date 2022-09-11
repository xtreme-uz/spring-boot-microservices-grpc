package com.example.orderservice.exception;

import lombok.Getter;

@Getter
public class OrderProductOutOfStockException extends RuntimeException {

    private final Long productId;

    public OrderProductOutOfStockException(Long productId) {
        super(String.format("Order product %d out of stock", productId));
        this.productId = productId;
    }
}

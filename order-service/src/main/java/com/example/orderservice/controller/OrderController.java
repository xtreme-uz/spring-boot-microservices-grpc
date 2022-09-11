package com.example.orderservice.controller;

import com.example.orderservice.grpc.GrpcProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final GrpcProductService productService;

    @GetMapping("order")
    public String createOrder() {
        productService.reserveOrderedProducts();
        return "order successfully created";
    }

}

package com.example.orderservice.grpc;

import com.example.orderservice.utils.grpc.GrpcStubExecutor;
import com.example.proto.product.ProductOuterClass.Product;
import com.example.proto.product.ProductServiceGrpc.ProductServiceBlockingStub;
import com.example.proto.product.ProductServiceOuterClass;
import com.example.proto.product.ProductServiceOuterClass.ReserveOrderedProductsRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class GrpcProductService {

    @GrpcClient("product-service")
    private ProductServiceBlockingStub productServiceStub;

    private final GrpcStubExecutor grpcStubExecutor;

    @Retry(name = "product-service")
    @CircuitBreaker(name = "product-service")
    public List<Product> reserveOrderedProducts() {
        ProductServiceOuterClass.ProductList productList = productServiceStub.withDeadlineAfter(20, TimeUnit.SECONDS)
                .reserveOrderedProducts(ReserveOrderedProductsRequest.newBuilder()
                        .addAllProducts(List.of(ProductServiceOuterClass.OrderProduct.newBuilder()
                                .setProductId(1)
                                .setQuantity(1)
                                .build()))
                        .build());
        return productList.getProductsList();
    }

}

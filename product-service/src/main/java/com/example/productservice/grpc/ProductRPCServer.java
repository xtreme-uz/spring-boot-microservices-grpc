package com.example.productservice.grpc;

import com.example.proto.product.ProductOuterClass;
import com.example.proto.product.ProductServiceGrpc;
import com.example.proto.product.ProductServiceOuterClass;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class ProductRPCServer extends ProductServiceGrpc.ProductServiceImplBase {

    @Override
    public void reserveOrderedProducts(ProductServiceOuterClass.ReserveOrderedProductsRequest request, StreamObserver<ProductServiceOuterClass.ProductList> responseObserver) {
        List<ProductOuterClass.Product> productsList = request.getProductsList()
                .stream().map(e -> ProductOuterClass.Product.newBuilder()
                        .setId(1)
                        .setName("test")
                        .setCategory("test_category")
                        .build()).toList();
        log.info("Reserving products: {}", productsList.toArray());
        responseObserver.onNext(ProductServiceOuterClass.ProductList.newBuilder()
                .addAllProducts(productsList).build());
        responseObserver.onCompleted();
    }
}

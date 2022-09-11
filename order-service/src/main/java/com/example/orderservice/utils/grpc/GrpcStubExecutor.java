package com.example.orderservice.utils.grpc;

import java.util.function.Supplier;

public interface GrpcStubExecutor {

    <T> T execute(Supplier<T> supplier);

    default <T> void invoke(Supplier<T> supplier) {
        execute(supplier);
    }

}

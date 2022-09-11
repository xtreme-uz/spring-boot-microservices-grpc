package com.example.orderservice.utils.grpc;

import com.google.rpc.ErrorInfo;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.ProtoUtils;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class ExceptionMapperGrpcStubExecutor implements GrpcStubExecutor {
    private final Map<String, Function<ErrorInfo, RuntimeException>> exceptionMappers;
    private final Supplier<RuntimeException> defaultExceptionSupplier;

    @Override
    public <T> T execute(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (StatusRuntimeException e) {
            var key = ProtoUtils.keyForProto(ErrorInfo.getDefaultInstance());
            var trailers = Optional.ofNullable(e.getTrailers()).orElseThrow(defaultExceptionSupplier);
            if (trailers.containsKey(key)) {
                ErrorInfo errorInfo = Optional.ofNullable(trailers.get(key))
                        .orElseThrow(defaultExceptionSupplier);

                throw exceptionMappers.get(errorInfo.getReason()).apply(errorInfo);
            }
            throw defaultExceptionSupplier.get();
        }
    }
}

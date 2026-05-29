package com.interview.demo.core.usecase;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public interface UseCaseExecutor {
    <Any, In extends UseCase.InputValue, Out extends UseCase.OutputValue, Ctx extends UseCase.Context> CompletableFuture<Any> execute(
            UseCase<In, Out, Ctx> useCase,
            In input,
            Ctx context,
            Function<Out, Any> outputMapper
    );
}

package com.interview.demo.core.usecase;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Service
public class UseCaseExecutorImpl implements UseCaseExecutor {
    @Override
    public <Any, In extends UseCase.InputValue, Out extends UseCase.OutputValue, Ctx extends UseCase.Context> CompletableFuture<Any> execute(
            UseCase<In, Out, Ctx> useCase,
            In input,
            Ctx context,
            Function<Out, Any> outputMapper
    ) {
        return CompletableFuture
                .supplyAsync(() -> input)
                .thenApply(in -> useCase.execute(in, context))
                .exceptionally(ex -> {
                    Throwable handled = useCase.handleException(context, ex);
                    throw handled instanceof RuntimeException
                            ? (RuntimeException) handled
                            : new RuntimeException(handled);
                })
                .thenApply(outputMapper);
    }
}

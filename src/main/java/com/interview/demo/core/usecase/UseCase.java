package com.interview.demo.core.usecase;

public abstract class UseCase<In extends UseCase.InputValue, Out extends UseCase.OutputValue, Ctx extends UseCase.Context> {
    public abstract Out execute(In input, Ctx context);

    public Throwable handleException(Ctx context, Throwable throwable) {
        return throwable;
    };

    public interface InputValue {
    }

    public interface OutputValue {
    }

    public interface Context {
    }
}

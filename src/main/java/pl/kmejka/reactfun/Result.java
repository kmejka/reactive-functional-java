package pl.kmejka.reactfun;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class Result<SUCCESS, ERROR> {

    private final SUCCESS successVal;
    private final ERROR errorVal;

    private Result(final SUCCESS successVal, final ERROR errorVal) {
        this.successVal = successVal;
        this.errorVal = errorVal;
    }

    public static <ERROR> Result failWith(final ERROR errorVal) {
        return new Result<>(null, errorVal);
    }

    public static <SUCCESS> Result succeedWith(final SUCCESS successVal) {
        return new Result<>(successVal, null);
    }

    public static Result success() {
        return new Result<>(null, null);
    }

    public boolean isSuccess() {
        return errorVal == null;
    }

    public boolean isFailure() {
        return !isSuccess();
    }

    public Optional<SUCCESS> getResult() {
        if (isSuccess()) {
            return Optional.of(successVal);
        } else {
            throw new IllegalStateException("Result did not succeed!");
        }
    }

    public Result<SUCCESS, ERROR> onSuccess(final Consumer<Optional<SUCCESS>> successConsumer) {
        if (isSuccess()) {
            successConsumer.accept(Optional.ofNullable(successVal));
        }
        return this;
    }

    public Result<SUCCESS, ERROR> onFailure(final Consumer<ERROR> failureConsumer) {
        if (isFailure()) {
            failureConsumer.accept(errorVal);
        }
        return this;
    }

    public SUCCESS getOrRecoverWith(final Function<ERROR, SUCCESS> errorHandlingFunction) {
        if (isSuccess()) {
            return successVal;
        } else {
            return errorHandlingFunction.apply(errorVal);
        }
    }
}

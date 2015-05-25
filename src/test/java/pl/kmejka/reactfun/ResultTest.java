package pl.kmejka.reactfun;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class ResultTest {

    @Test
    public void succesfullTry() {
        //GIVEN
        final String retValue = "a";

        //WHEN
        final Result<String, RuntimeException> obj = Result.succeedWith(retValue);

        //THEN
        assertThat(obj.isSuccess()).isTrue();
        assertThat(obj.getResult().isPresent()).isTrue();
        assertThat(obj.getResult().get()).isEqualTo(retValue);
    }

    @Test(expected = RuntimeException.class)
    public void failureReturnsProperException() {
        //GIVEN
        final RuntimeException exception = new RuntimeException("BANG!");

        //WHEN
        final Result<String, RuntimeException> obj = Result.failWith(exception);

        //THEN
        assertThat(obj.isSuccess()).isFalse();
        assertThat(obj.getResult().isPresent()).isTrue();
        obj.getResult().get();
    }

    @Test
    public void failureCaseHandlingWorks() {
        //GIVEN
        final RuntimeException exception = new RuntimeException("b");
        final String failSafe = "a";

        //WHEN
        final Result<String, RuntimeException> obj = Result.failWith(exception);

        //THEN
        assertThat(obj.getOrRecoverWith(error -> failSafe + error.getMessage())).isEqualTo("ab");
    }
}
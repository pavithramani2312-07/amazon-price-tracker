package util;

@FunctionalInterface
public interface Retryable<T> {

    T run() throws Exception;

}

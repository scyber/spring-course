package ru.otus.exeptions;

public class FindItemExecption extends RuntimeException {
    public FindItemExecption(String message, Throwable cause) {
        super(message, cause);
    }
}

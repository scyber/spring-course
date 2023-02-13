package ru.otus.exeptions;

public class FindItemException extends RuntimeException{
    public FindItemException(String message, Throwable cause) {
        super(message, cause);
    }
    public FindItemException(String mesage){
        super(mesage);
    }

}

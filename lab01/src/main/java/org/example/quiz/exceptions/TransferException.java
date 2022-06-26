package org.example.quiz.exceptions;

public class TransferException extends RuntimeException{
    public TransferException(String message, Throwable cause){
        super(message,cause);
    }
}

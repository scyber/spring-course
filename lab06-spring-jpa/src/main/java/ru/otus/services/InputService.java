package ru.otus.services;

public interface InputService {
    long readLong();
    String readStringWithPrompt(String prompt);
    long readLongWithPrompt(String prompt);
}

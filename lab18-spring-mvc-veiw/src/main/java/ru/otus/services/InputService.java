package ru.otus.services;

public interface InputService {
    Long readLong();
    String readStringWithPrompt(String prompt);
    Long readLongWithPrompt(String prompt);
}

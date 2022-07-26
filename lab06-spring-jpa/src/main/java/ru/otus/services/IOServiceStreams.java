package ru.otus.services;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class IOServiceStreams implements IOService{
    private final PrintStream output;
    private final Scanner input;

    public IOServiceStreams(InputStream inputStream, PrintStream output) {
        this.output = output;
        this.input = new Scanner(inputStream);
    }

    @Override
    public long readLong() {
        return Long.parseLong(input.nextLine());
    }

    @Override
    public String readStringWithPrompt(String prompt) {
        output.println(prompt);
        return input.nextLine();
    }

    @Override
    public long readLongWithPrompt(String prompt) {
        output.println(prompt);
        return  readLong();
    }

    @Override
    public void outputString(String s) {
        output.println(s);
    }
}

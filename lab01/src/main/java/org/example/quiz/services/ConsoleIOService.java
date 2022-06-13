package org.example.quiz.services;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleIOService {
    private final Scanner userInput;

    public ConsoleIOService() {
        this.userInput = new Scanner(System.in);
    }

    public void outputString(String s) {
        System.out.println(s);
    }

    public String resStringWithPrompt(String prompt){
        outputString(prompt);
        return userInput.nextLine();
    }
    public List<String> readResultsWithPrompt(String prompt){
        outputString(prompt);
        return Arrays.stream(userInput.nextLine().split("\\s")).collect(Collectors.toList());
    }


}

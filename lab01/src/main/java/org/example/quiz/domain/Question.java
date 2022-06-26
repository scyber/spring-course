package org.example.quiz.domain;


import com.opencsv.bean.CsvBindAndSplitByPosition;
import com.opencsv.bean.CsvBindByPosition;
import java.util.LinkedList;
import java.util.List;

public class Question {
    @CsvBindByPosition(position = 0)
    private Long id;
    @CsvBindByPosition(position = 1)
    private String context;

    @CsvBindAndSplitByPosition(position = 2, elementType = Long.class, collectionType = LinkedList.class)
    private LinkedList<Long> answersList;

    @CsvBindAndSplitByPosition(position = 3, elementType = Long.class, collectionType = List.class)
    private List<Long> correctAnswers;


    public void setId(Long id) {
        this.id = id;
    }

    public void setContext(String context) {
        this.context = context;
    }


    public void setAnswersList(LinkedList<Long> answersList) {
        this.answersList = answersList;
    }

    public void setCorrectAnswers(List<Long> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public long getId() {
        return id;
    }

    public String getContext() {
        return context;
    }

    public LinkedList<Long> getAnswersList() {
        return answersList;
    }

    public List<Long> getCorrectAnswers() {
        return correctAnswers;
    }

}

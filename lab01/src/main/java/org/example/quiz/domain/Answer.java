package org.example.quiz.domain;


public class Answer {
    private Long id;
    private String context;

    public Answer() {
        this.id = 0L;
        this.context = "";
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public long getId() {
        return id;
    }

    public String getContext() {
        return context;
    }


}

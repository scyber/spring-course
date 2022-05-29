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

//    private Question() {
//
//    }

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

    public static QuestionBuilder newQuestionBuilder() {
        return new Question().new QuestionBuilder();
    }
    public class QuestionBuilder{

        private QuestionBuilder(){

        }
        public Question build(){
            Question question = new Question();
            question.id = Question.this.id;
            question.answersList = Question.this.answersList;
            question.context = Question.this.context;
            question.correctAnswers = Question.this.correctAnswers;
            return question;
        }
        public QuestionBuilder withId(long id){
            Question.this.id = id;
            return this;
        }
        public QuestionBuilder withAnswerList(List<Answer> answerList ){
            Question.this.answersList = answersList;
            return this;
        }
        public QuestionBuilder withCorrectAnswers(List<Long> correctAnswers){
            Question.this.correctAnswers = correctAnswers;
            return this;
        }
        public QuestionBuilder withContext(String context){
            Question.this.context = context;
            return this;
        }


    }
}

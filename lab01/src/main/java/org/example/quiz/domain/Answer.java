package org.example.quiz.domain;


public class Answer {
    private Long id;
    private String context;


//    private Answer(){
//
//    }

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

    public static AnswerBuilder newAnswerBuilder(){
        return new Answer().new AnswerBuilder();
    }

    public class AnswerBuilder{

        private  AnswerBuilder() {
        }

        public Answer build(){
            Answer answer = new Answer();
            answer.id = Answer.this.id;
            answer.context = Answer.this.context;
            return answer;
        }
        public AnswerBuilder withId(long id){
            Answer.this.id = id;
            return this;
        }
        public AnswerBuilder withContext(String context){
            Answer.this.context = context;
            return this;
        }
    }

}

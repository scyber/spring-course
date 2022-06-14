package org.example.quiz.services;



import org.springframework.beans.factory.annotation.Value;



public class ScorePropertiesService {

    @Value("${score.pass}")
    private Double scoreValue;

    public Double getScoreValue() {
        return this.scoreValue;
    }
}

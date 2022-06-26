package org.example.quiz.services;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class ScorePropertiesService {

    @Value("${score.pass}")
    private Double scoreValue;

    public Double getScoreValue() {
        return this.scoreValue;
    }
}

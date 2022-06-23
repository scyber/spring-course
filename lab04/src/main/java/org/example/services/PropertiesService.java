package org.example.services;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class PropertiesService {

    @Value("${score.pass}")
    private Double scoreValue;

    public Double getScoreValue() {
        return this.scoreValue;
    }
}

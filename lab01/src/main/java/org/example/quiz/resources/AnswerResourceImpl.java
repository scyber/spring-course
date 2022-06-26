package org.example.quiz.resources;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;


@Component
public class AnswerResourceImpl implements QuizResource {
    @Override
    public Resource getResource() {
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource("answers.csv");
        return resource;
    }
}

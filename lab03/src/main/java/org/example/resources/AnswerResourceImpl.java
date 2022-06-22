package org.example.resources;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;


@Component
public class AnswerResourceImpl implements QuizResource {
    private FileNameAnswersProvider provider;

    public AnswerResourceImpl(FileNameAnswersProvider provider) {
        this.provider = provider;
    }

    @Override
    public Resource getResource() {
        String answers = provider.provide();
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource(answers);
        return resource;
    }
}

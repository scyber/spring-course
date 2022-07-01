package org.example.resources;


import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class QuestionResourceImpl implements QuizResource {
    private FileNameQuestionsProvider provider;

    public QuestionResourceImpl(FileNameQuestionsProvider provider) {
        this.provider = provider;
    }

    @Override
    public Resource getResource() {
        String questions = provider.provide();
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource(questions);
        return resource;
    }
}

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.example.quiz.domain.Answer;
import org.example.quiz.repository.AnswerRepository;
import org.example.quiz.repository.QuestionRepository;
import org.example.quiz.resources.AnswerResourceImpl;
import org.example.quiz.resources.QuestionResourceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestApplication.TestConfiguration.class})
public class TestApplication {

    @Autowired
    QuestionResourceImpl questionResource;

    @Autowired
    AnswerResourceImpl answerResource;

    @Autowired
    QuestionRepository questionRepository;



    @Test
    public void testAnswersRepository(){

        AnswerRepository answerRepository = Mockito.mock(AnswerRepository.class);

        Answer answer = new Answer();
        answer.setId(1L);
        answer.setContext("Test Answer");
        Answer answer1 = new Answer();
        answer1.setId(2L);
        answer1.setContext("false");
        Map<Long,Answer> answerMap = new HashMap<>();
        answerMap.put(1L,answer);
        answerMap.put(2L, answer1);
        Mockito.when(answerRepository.getAnswers()).thenReturn(answerMap);
    }

   @Configuration
    public static class TestConfiguration{

        @Bean
        public QuestionResourceImpl questionResource(){
            return Mockito.mock(QuestionResourceImpl.class);
        }

        @Bean
        public AnswerResourceImpl answerResource(){
            return Mockito.mock(AnswerResourceImpl.class);
        }



        @Bean
        public QuestionRepository questionRepository() {
            return Mockito.mock(QuestionRepository.class);
        }

    }
}

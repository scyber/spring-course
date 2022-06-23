package org.example;

import org.example.resources.FileNameAnswersProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.i18n.SimpleLocaleContext;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;


import java.util.Locale;
import java.util.Optional;

@SpringBootTest
class QuizApplicationTests {
	static {
		Locale.setDefault(Locale.ENGLISH);
	}

	@Autowired
	MessageSource messageSource;



	@Test
	void contextLoads() {
	}

	@Test
	void testResourceBoundle(){
		FileNameAnswersProvider fileNameAnswersProvider = new FileNameAnswersProvider(messageSource,Optional.of(Locale.FRANCE));
		Assertions.assertEquals("en_answers.csv", fileNameAnswersProvider.provide());
	}


}

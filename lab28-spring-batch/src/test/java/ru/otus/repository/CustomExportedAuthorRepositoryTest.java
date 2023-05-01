package ru.otus.repository;

import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.export.ExportedAuthor;

@SpringBootTest
public class CustomExportedAuthorRepositoryTest {

	private static final String AUTHOR_NAME = "Тестовый Автор";

	@Autowired
	private ExportAuthorRepository exportedAuthorRepository;

	@Test
	@DisplayName("Тест поиска по имени Автора custom MongoDB")
	void testSearchByName() {
		var author = new ExportedAuthor();
		author.setName(AUTHOR_NAME);
		exportedAuthorRepository.save(author);
		var authors = exportedAuthorRepository.findByName(AUTHOR_NAME).stream().map(a -> a.getName()).collect(Collectors.toList());
		Assertions.assertTrue(authors.contains(AUTHOR_NAME));
	}

}

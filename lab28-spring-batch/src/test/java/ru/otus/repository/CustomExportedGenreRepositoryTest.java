package ru.otus.repository;


import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mongodb.assertions.Assertions;

import ru.otus.export.ExportedGenre;

@SpringBootTest
public class CustomExportedGenreRepositoryTest {
	
	private static final String GENRE_NAME = "Тестовый Автор";
	
	@Autowired
	private ExportGenreRepository exportGenreRepository;
	
	
	@Test
	@DisplayName("Тест поиска жанра по названию MongoDB")
	public void customFindByNameTest() {
		var exportedGengre = new ExportedGenre();
		exportedGengre.setName(GENRE_NAME);
		exportGenreRepository.save(exportedGengre);
		var foundGenres = exportGenreRepository.findByName(GENRE_NAME).stream().map(g -> g.getName()).collect(Collectors.toList());
		Assertions.assertTrue(foundGenres.contains(GENRE_NAME));
	}

}

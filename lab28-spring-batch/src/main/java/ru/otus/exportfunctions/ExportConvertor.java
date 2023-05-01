package ru.otus.exportfunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.export.ExportedAuthor;
import ru.otus.export.ExportedBook;
import ru.otus.export.ExportedGenre;
import ru.otus.repository.ExportAuthorRepository;
import ru.otus.repository.ExportGenreRepository;

@Component
@RequiredArgsConstructor
public class ExportConvertor {
	
	@Autowired
	private ExportAuthorRepository exportAuthorRepository;

	@Autowired
	private ExportGenreRepository exportGenreRepository;

	public Function<Author, ExportedAuthor> convertToAuthorExported = a -> {
		var expAuthor = new ExportedAuthor();
		expAuthor.setName(a.getName());
		return expAuthor;
	};

	public Function<Genre, ExportedGenre> convertToGenreExported = g -> {
		var expGenre = new ExportedGenre();
		expGenre.setName(g.getName());
		return expGenre;
	};

	public Function<Book, ExportedBook> convertToBookExported = b -> {
		var exportedBook = new ExportedBook();

		exportedBook.setTitle(b.getTitle());

		List<ExportedAuthor> authorsToAdd = new ArrayList<>();
		List<List<ExportedAuthor>> authorsFound = b.getAuthors().stream().map(a -> {
			List<ExportedAuthor> authors = exportAuthorRepository.findByName(a.getName());
			return authors;
		}).collect(Collectors.toList());

		authorsFound.forEach(l -> l.forEach(a -> authorsToAdd.add(a)));
		List<ExportedGenre> genresToAdd = new ArrayList<>();
		List<List<ExportedGenre>> genresListsFound = b.getGenres().stream().map(g -> {
			List<ExportedGenre> genres = exportGenreRepository.findByName(g.getName());
			return genres;
		}).collect(Collectors.toList());

		genresListsFound.forEach(l -> l.forEach(g -> genresToAdd.add(g)));

		exportedBook.setAuthors(authorsToAdd);
		exportedBook.setGenres(genresToAdd);
		return exportedBook;
	};

}

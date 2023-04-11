package ru.otus.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.otus.clients.LibraryClient;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.exeptions.FindItemExecption;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryClientServiceImpl implements LibraryClientService {

    private final LibraryClient libraryClient;

    @Override
    public Page<Book> getPagebleBooks(Integer page, Integer size) {
       return libraryClient.getBooks(PageRequest.of(page,size));
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return Optional.of(libraryClient.getBookById(id).orElseThrow(() -> new FindItemExecption("Could not find Book with id " + id)));
    }

    @Override
    public Book saveAndUpdateBook(Book book) {
        return libraryClient.saveAndUpdateBook(book);
    }

    @Override
    public void deleteBookById(Long id) {
        libraryClient.deleteBookById(id);
    }

    @Override
    public void updateBookTitleById(Long id, String title) {
        libraryClient.updateBookTitleById(id,title);
    }

    @Override
    public List<Author> getAllAuthors() {
        return libraryClient.getAllAuthors();
    }

    @Override
    public List<Genre> getAllGenres() {
        return libraryClient.getAllGenres();
    }

    @Override
    public List<Comment> getAllComments() {
        return libraryClient.getAllComments();
    }

}

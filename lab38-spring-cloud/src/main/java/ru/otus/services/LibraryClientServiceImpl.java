package ru.otus.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.otus.clients.LibraryClient;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.exeptions.FindItemExecption;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LibraryClientServiceImpl implements LibraryClientService {

    private final LibraryClient libraryClient;

    @HystrixCommand(fallbackMethod = "failGetAllBooks")
    @Override
    public Page<Book> getPagebleBooks(Integer page, Integer size) {
       return libraryClient.getBooks(PageRequest.of(page,size));
    }

    @HystrixCommand
    @Override
    public Optional<Book> getBookById(Long id) {
        return Optional.of(libraryClient.getBookById(id).orElseThrow(() -> new FindItemExecption("Could not find Book with id " + id)));
    }

    @HystrixCommand
    @Override
    public Book saveAndUpdateBook(Book book) {
        return libraryClient.saveAndUpdateBook(book);
    }

    @HystrixCommand
    @Override
    public void deleteBookById(Long id) {
        libraryClient.deleteBookById(id);
    }

    @HystrixCommand
    @Override
    public void updateBookTitleById(Long id, String title) {
        libraryClient.updateBookTitleById(id,title);
    }

    @HystrixCommand
    @Override
    public List<Author> getAllAuthors() {
        return libraryClient.getAllAuthors();
    }

    @HystrixCommand
    @Override
    public List<Genre> getAllGenres() {
        return libraryClient.getAllGenres();
    }

    @Override
    public List<Comment> getAllComments() {
        return libraryClient.getAllComments();
    }

    private Page<Book> failGetAllBooks(Integer page, Integer size){
        var emptyList = new ArrayList<Book>();
        var emptyBookPage = new PageImpl<>(emptyList);
        return emptyBookPage;
    }
}

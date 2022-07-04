package ru.otus.repository;

import ru.otus.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class BookRepositoryJpa implements BookRepository{
    @PersistenceContext
    private final EntityManager em ;

    public BookRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Book save(Book book) {
        if(book.getId() == null){
            em.persist(book);
            return book;
        } else {
          return em.merge(book);
        }
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public List<Book> findByName(String name) {
        TypedQuery<Book> query = em.createQuery("select s from BOOKS where s.name = : name", Book.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public void updateNameById(long id, String name) {
        Query query = em.createQuery("update BOOKS b set b.name = :name where b.id = :id");
        query.setParameter("name", name);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from BOOKS where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void updateAuthorById(long bookId, long authorId) {
        Query query = em.createQuery("update BOOKS set author_id = :author_id where id = :id");
        query.setParameter("author_id", authorId);
        query.setParameter("id", bookId);
        query.executeUpdate();
    }
    @Override
    public void updateGenreById(long bookId, long genreId) {
        Query query = em.createQuery("update BOOKS set genre_id = :genre_id where id = :id");
        query.setParameter("genre_id", genreId);
        query.setParameter("id", bookId);
        query.executeUpdate();
    }
}

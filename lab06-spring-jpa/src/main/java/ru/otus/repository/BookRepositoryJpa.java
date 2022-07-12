package ru.otus.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
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
        TypedQuery<Book> query= em.createQuery("SELECT b from BOOK", Book.class);
        return query.getResultList();
    }

    @Override
    public List<Book> findByName(String name) {
        TypedQuery<Book> query = em.createQuery("select s from BOOK " +
                "where s.name = : name", Book.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Comment> getComments(long id) {
        return null;
    }

    @Override
    public void updateNameById(long id, String name) {

    }

    @Override
    public void delete(long id) {
        TypedQuery<Book> query = em.createQuery("delete from BOOK " +
                "where id = :id", Book.class);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}

package ru.otus.repository;

import org.springframework.stereotype.Repository;
import ru.otus.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private final EntityManager em;

    public AuthorRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Author> findAll() {
        var query = em.createQuery("select a from Author a ", Author.class);
        return query.getResultList();
    }

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> findByName(String name) {
        var query = em.createQuery("SELECT a from Author a " +
                "where a.name =:name ", Author.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public void delete(long id) {
        var query = em.createQuery("delete from Author a " +
                "where a.id =:id ");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void updateAuthorNameById(long id, String name) {
        var query = em.createQuery("UPDATE Author a set a.name = :name where a.id =:id");
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.executeUpdate();
    }

    @Override
    public Author save(Author domain) {
        if (domain.getId() <= 0) {
            em.persist(domain);
            return domain;
        }
        return em.merge(domain);
    }
}

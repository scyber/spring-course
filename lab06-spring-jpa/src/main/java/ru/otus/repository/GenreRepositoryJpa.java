package ru.otus.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


@Repository
public class GenreRepositoryJpa {
    @PersistenceContext
    private final EntityManager em;

    public GenreRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    public List<Genre> findByName(String name) {
        var query = em.createQuery("select g from GENRE " +
                "where name = :name",Genre.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public List<Genre> findAll() {
        var query = em.createQuery("select g from GENRE ", Genre.class);
        return query.getResultList();
    }

    public void deleteById(long id) {
        var query = em.createQuery("delete from GENRE " +
                "where g.id = :id", Genre.class);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public Genre save(Genre domain) {
        if(domain.getId() <= 0){
          em.persist(domain);
          return domain;
        } else {
        return em.merge(domain);
        }
    }
}

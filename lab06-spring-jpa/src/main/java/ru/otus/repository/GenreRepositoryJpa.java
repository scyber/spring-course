package ru.otus.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Genre;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@Repository
public class GenreRepositoryJpa implements GenreRepository{

    @PersistenceContext
    private final EntityManager em;

    public GenreRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    @Transactional
    public List<Genre> findByName(String name) {
        var query = em.createQuery("select g from Genre g " +
                "where g.name = :name ",Genre.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Genre> findAll() {
        var query = em.createQuery("select g from Genre g ", Genre.class);
        return query.getResultList();
    }

    @Override
    public void delete(long id) {
        var query = em.createQuery("delete from Genre g " +
                "where g.id = :id");
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

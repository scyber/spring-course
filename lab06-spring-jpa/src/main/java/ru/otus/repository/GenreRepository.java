package ru.otus.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.Optional;

@Transactional
@Repository
public class GenreRepository implements ItemRepository<Genre> {
    @PersistenceContext
    private final EntityManager em;

    public GenreRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public Iterable<Genre> findByName(String name) {
        Query query = em.createQuery("select g from GENRE where name = :name");
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public Iterable<Genre> findAll() {
        Query query = em.createQuery("select g from GENRE", Genre.class);
        return query.getResultList();
    }

    @Override
    public long updateNameById(long id, String name) {
        return 0;
    }

    @Override
    public void delete(Genre domain) {
        Query query = em.createQuery("delete from GENRE where g.id = :id");
        query.setParameter("id", domain.getId());
        query.executeUpdate();
    }

    @Override
    public Genre save(Genre domain) {
        if(domain.getId() <= 0){
          em.persist(domain);
          return domain;
        } else {
        return em.merge(domain);
        }
    }
}

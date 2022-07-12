package ru.otus.repository;

import org.springframework.stereotype.Repository;
import ru.otus.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    EntityManager em;

    public AuthorRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Author> findAll(){
        var query = em.createQuery("select a from Author ", Author.class);
        return query.getResultList();
    }

    @Override
    public Optional<Author> findById(long id){
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public List<Author> findByName(String name){
        var query = em.createQuery("SELECT a from Author " +
                "where a.name =:name ", Author.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public void delete(long id){
        var query = em.createQuery("delete from Author " +
                "where a.id =:id", Author.class);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Author save(Author domain){
        if(domain.getId() == 0){
          em.persist(domain);
          return  domain;
        }
         return em.merge(domain);
    }
}

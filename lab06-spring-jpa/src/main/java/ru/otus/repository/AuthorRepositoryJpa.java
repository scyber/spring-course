package ru.otus.repository;

import ru.otus.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class AuthorRepositoryJpa {

    @PersistenceContext
    EntityManager em;

    public AuthorRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    public List<Author> findAll(){
        var query = em.createQuery("select a from AUTHOR", Author.class);
        return query.getResultList();
    }
    public Optional<Author> findById(long id){
        return Optional.ofNullable(em.find(Author.class, id));
    }
    public List<Author> findByName(String name){
        var query = em.createQuery("SELECT a from AUTHOR " +
                "where a.name =:name ", Author.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
    public void deleteById(long id){
        var query = em.createQuery("delete from AUTHOR " +
                "where a.id =:id", Author.class);
        query.setParameter("id", id);
        query.executeUpdate();
    }
    public Author save(Author domain){
        if(domain.getId() == 0){
          em.persist(domain);
          return  domain;
        }
            return em.merge(domain);
    }
}

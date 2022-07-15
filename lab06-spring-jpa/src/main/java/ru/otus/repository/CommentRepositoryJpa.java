package ru.otus.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Comment;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpa implements CommentRepository{

    @PersistenceContext
    EntityManager em;

    @Override
    public void delete(long commentId) {
        var query = em.createQuery("delete from Comments c where c.id = :id");
        query.setParameter("id", commentId);
        query.executeUpdate();
    }
    @Override
    public Comment save(Comment domain) {
        if(domain.getId() <= 0){
            em.persist(domain);
            return domain;
        }
        return em.merge(domain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findAll() {
        var query = em.createQuery("select c from Comment c ", Comment.class);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> findById(long commentId) {
        return Optional.ofNullable(em.find(Comment.class,commentId));
    }

    @Override
    public Optional<Comment> findByBookId(long bookId) {
        var query = em.createQuery("select c from Comment c where c.book_id = :book_id");
        query.setParameter("book_id", bookId);
        return Optional.ofNullable((Comment) query.getSingleResult());
    }

    @Override
    public void addBookComment(long bookId, String text) {

    }
}

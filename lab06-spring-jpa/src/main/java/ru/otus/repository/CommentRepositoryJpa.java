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
        var query = em.createQuery("delete from Comment c where c.id = :id");
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
    public Optional<List<Comment>> findByBookId(long bookId) {
        var query = em.createQuery("select c from Comment c where c.bookId = :bookId");
        query.setParameter("bookId", bookId);
        return Optional.of(query.getResultList());
    }

    @Override
    public void addBookComment(long bookId, String text) {
        var comment = new Comment();
        comment.setBookId(bookId);
        comment.setTitle(text);
        save(comment);
    }
}

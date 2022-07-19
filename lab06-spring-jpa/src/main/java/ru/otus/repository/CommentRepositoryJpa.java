package ru.otus.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Comment;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void delete(long id) {
        var query = em.createQuery("delete from Comment c where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public Comment save(Comment domain) {
        if (domain.getId() <= 0) {
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
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    @Transactional
    public List<Comment> findByBookId(long bookId) {
        var query = em.createQuery("select c from Comment c where c.bookId = :bookId");
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }

    @Override
    @Transactional
    public Comment addCommentBookById(long bookId, String title) {
        var comment = new Comment();
        comment.setBookId(bookId);
        comment.setTitle(title);
        return save(comment);
    }

    @Override
    @Transactional
    public void updateCommentById(long id, String title) {
        var query = em.createQuery("update Comment c set c.title =:title where c.id =:id");
        query.setParameter("title", title);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}

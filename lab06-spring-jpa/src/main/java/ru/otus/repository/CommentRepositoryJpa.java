package ru.otus.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Comment;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    private final EntityManager em;
    private final BookRepository bookRepository;

    public CommentRepositoryJpa(EntityManager em, BookRepository bookRepository) {
        this.em = em;
        this.bookRepository = bookRepository;
    }

    @Override
    public void delete(long id) {
        var query = em.createQuery("delete from Comment c where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Comment save(Comment domain) {
        if (domain.getId() <= 0) {
            em.persist(domain);
            return domain;
        }
        return em.merge(domain);
    }

    @Override
    public List<Comment> findAll() {
        var query = em.createQuery("select c from Comment c " +
                "left join fetch c.book", Comment.class);
        return query.getResultList();
    }

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public List<Comment> findByBookId(long bookId) {
        var query = em.createQuery("select c from Comment c where c.book = :book");
        var book = bookRepository.findById(bookId).get();
        query.setParameter("book", book);
        return query.getResultList();
    }

    @Override
    public Comment addCommentBookById(long bookId, String title) {
        var comment = new Comment();
        var book = bookRepository.findById(bookId).get();
        comment.setTitle(title);
        comment.setBook(book);
        return save(comment);
    }

    @Override
    public void updateCommentById(long id, String title) {
        var query = em.createQuery("update Comment c set c.title =:title where c.id =:id");
        query.setParameter("title", title);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}

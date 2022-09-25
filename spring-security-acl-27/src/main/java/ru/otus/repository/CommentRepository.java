package ru.otus.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteById(@Param("id") Long id);

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    Comment save(Comment domain);

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    Optional<Comment> findById(@Param("id") Long id);

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @Query("select c from Comment c where c.book = :book ")
    List<Comment> findByBook(@Param("book") Book book);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Query("update Comment c set c.title = :title where c.id = :id")
    @Modifying
    void updateCommentById(@Param("id")Long id, @Param("title") String title);

}

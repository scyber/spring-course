package ru.otus.listeners;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.domain.Book;
import ru.otus.repository.CommentRepository;

@Component
@RequiredArgsConstructor
public class CascadeDeleteBookListener extends AbstractMongoEventListener<Book> {

    private static final Logger LOG = LoggerFactory.getLogger(CascadeDeleteBookListener.class);
    private final CommentRepository commentRepository;

    public void onAfterDelete(AfterDeleteEvent<Book> event) {
        var bookId = event.getSource().get("_id").toString();
        super.onAfterDelete(event);
        commentRepository.deleteCommentsByBookId(bookId).subscribe(e -> LOG.info("Following comment Id deleted " + e.getId()));

    }
}

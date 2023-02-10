package ru.otus.listners;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import ru.otus.domain.Book;
import ru.otus.repository.CommentRepository;

@Component
@RequiredArgsConstructor
public class MongoCascadeDeleteEventListener extends AbstractMongoEventListener<Book> {

	private final CommentRepository commentRepository;
	
	public void onDeleteBookEvent(AfterDeleteEvent<Book> event) {
		super.onAfterDelete(event);
		var id = event.getSource().get("_id").toString();
		this.commentRepository.deleteCommentsByBookId(id);
	}
}

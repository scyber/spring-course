package ru.otus.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import ru.otus.export.ExportedAuthor;


@RequiredArgsConstructor
@Component
public class ExportAuthorRepositoryCustomImpl implements CustomExportedAuthorRepository {
	
	private final MongoTemplate mongoTemplate;

	@Override
	public List<ExportedAuthor> findByName(String name) {
		var query = new Query();
		var criteria = Criteria.where("name").is(name);
		query.addCriteria(criteria);
		return this.mongoTemplate.find(query, ExportedAuthor.class);
	}

}

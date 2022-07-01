package ru.otus.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Author;
import ru.otus.exeptions.FindItemExecption;
import ru.otus.repository.CommonRepository;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Repository
public class AuthorDao implements CommonRepository<Author> {

    private static final String SQL_QUERY_FOR_ALL = "select id, author_first_name, author_second_name, author_last_name from authors ";
    private static final String SQL_QUERY_AUTHOR_BY_ID = SQL_QUERY_FOR_ALL + " where id = :id";
    private static final String SQL_INSERT_AUTHORS = "insert into authors (author_first_name, author_second_name, author_last_name) values "+
            "(:author_first_name, :author_second_name, :author_last_name)";
    private static final String SQL_UPDATE_AUTHORS = "update authors set " +
            "author_first_name =: author_first_name, author_second_name = :author_second_name, author_last_name= :author_last_name " +
            "where id = :id";
    private static final String SQL_DELETE = "delete from authors where id = :id";


    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AuthorDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Long save(Author domain) {
        if(domain.getId() != null){
            Author author = new Author();
            author.setLastName(domain.getLastName());
            author.setSecondName(author.getSecondName());
            author.setLastName(author.getLastName());
            return update(author,SQL_UPDATE_AUTHORS);
        } else {
            return update(domain,SQL_INSERT_AUTHORS);
        }
    }

    @Override
    public void delete(Long id) {
        Map<String,Object> mappedParameters = Collections.singletonMap("id", id);
        this.namedParameterJdbcTemplate.update(SQL_DELETE,mappedParameters);
    }

    @Override
    public Author findById(Long id) {
        Map<String,Object> mapperParametes = Collections.singletonMap("id", id);
        try {
            return this.namedParameterJdbcTemplate.queryForObject(SQL_QUERY_AUTHOR_BY_ID, mapperParametes, authorRowMapper);
        } catch (Exception e){
            throw new FindItemExecption("Could not find Author by ID " + id, e);
        }
    }

    @Override
    public Iterable<Author> findAll() {
        return this.namedParameterJdbcTemplate.query(SQL_QUERY_FOR_ALL, authorRowMapper);
    }

    RowMapper<Author> authorRowMapper = (ResultSet rs, int rowNum) -> {
        Author author = new Author();
        author.setId(rs.getLong("id"));
        author.setFirstName(rs.getString("author_first_name"));
        author.setSecondName(Optional.ofNullable(rs.getString("author_second_name")).orElse(""));
        author.setLastName(rs.getString("author_last_name"));
        return author;
    };
    private Long update(Author author, String sql){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("id", author.getId());
        sqlParameterSource.addValue("author_first_name", author.getFirstName());
        sqlParameterSource.addValue("author_second_name",author.getSecondName());
        sqlParameterSource.addValue("author_last_name",author.getLastName());
        this.namedParameterJdbcTemplate.update(sql,sqlParameterSource,keyHolder);
        return keyHolder.getKey().longValue();
    }
}

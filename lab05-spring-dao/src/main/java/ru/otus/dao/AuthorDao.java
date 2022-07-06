package ru.otus.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Author;
import ru.otus.exeptions.FindItemExecption;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class AuthorDao {

    private static final String SQL_QUERY_FOR_ALL = "select id, name from authors a ";
    private static final String SQL_QUERY_AUTHOR_BY_ID = SQL_QUERY_FOR_ALL + " where id = :id";
    private static final String SQL_INSERT_AUTHORS = "insert into authors ( name ) values ( :name)";
    private static final String SQL_UPDATE_AUTHORS = "update authors set name = :name where id = :id";
    private static final String SQL_DELETE = "delete from authors where id = :id";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AuthorDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    public Long save(Author domain) {
        if(domain.getId() != null){
            Author author = new Author();
            author.setName(domain.getName());
            return update(author,SQL_UPDATE_AUTHORS);
        } else {
            return update(domain,SQL_INSERT_AUTHORS);
        }
    }

    public void delete(Long id) {
        Map<String,Object> mappedParameters = Collections.singletonMap("id", id);
        this.namedParameterJdbcTemplate.update(SQL_DELETE,mappedParameters);
    }
    public Optional<Author> findById(Long id) {
        Map<String,Object> mapperParameters = Collections.singletonMap("id", id);
        try {
            return Optional.ofNullable(this.namedParameterJdbcTemplate.queryForObject(SQL_QUERY_AUTHOR_BY_ID, mapperParameters, authorRowMapper));
        } catch (Exception e){
            throw new FindItemExecption("Could not find Author by ID " + id, e);
        }
    }
    public List<Author> findAll() {
        return this.namedParameterJdbcTemplate.query(SQL_QUERY_FOR_ALL, authorRowMapper);
    }

    RowMapper<Author> authorRowMapper = (ResultSet rs, int rowNum) -> {
        return new Author(rs.getLong(1), rs.getString(2));
    };

    private Long update(Author author, String sql){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("id", author.getId());
        sqlParameterSource.addValue("name", author.getName());
        this.namedParameterJdbcTemplate.update(sql,sqlParameterSource,keyHolder);
        return keyHolder.getKey().longValue();
    }
}

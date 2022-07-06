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

    private final String sql_query_for_all = "select id, name from authors a ";
    private final String sql_query_author_by_id = sql_query_for_all + " where id = :id";
    private final String sql_insert_authors = "insert into authors ( name ) values ( :name)";
    private final String sql_update_authors = "update authors set name = :name where id = :id";
    private final String sql_delete = "delete from authors where id = :id";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AuthorDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    public Long save(Author domain) {
        if(domain.getId() != null){
            Author author = new Author();
            author.setName(domain.getName());
            return update(author,sql_update_authors);
        } else {
            return update(domain,sql_insert_authors);
        }
    }

    public void delete(Long id) {
        Map<String,Object> mappedParameters = Collections.singletonMap("id", id);
        this.namedParameterJdbcTemplate.update(sql_delete,mappedParameters);
    }
    public Optional<Author> findById(Long id) {
        Map<String,Object> mapperParameters = Collections.singletonMap("id", id);
        try {
            return Optional.ofNullable(this.namedParameterJdbcTemplate.queryForObject(sql_query_author_by_id, mapperParameters, authorRowMapper));
        } catch (Exception e){
            throw new FindItemExecption("Could not find Author by ID " + id, e);
        }
    }
    public List<Author> findAll() {
        return this.namedParameterJdbcTemplate.query(sql_query_for_all, authorRowMapper);
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

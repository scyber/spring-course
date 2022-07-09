package ru.otus.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Author;
import ru.otus.exeptions.FindItemExecption;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class AuthorDao {

    private final String sqlQueryForAll = "select id, name from authors a ";
    private final String sqlQueryAuthorById = sqlQueryForAll + " where id = :id";
    private final String sqlInsertAuthor = "insert into authors ( name ) values ( :name)";
    private final String sqlUpdateAuthor = "update authors set name = :name where id = :id";
    private final String sqlDelete = "delete from authors where id = :id";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AuthorDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    public Long save(Author domain) {
        if(domain.getId() != null){
            return update(domain, sqlUpdateAuthor);
        } else {
            return update(domain, sqlInsertAuthor);
        }
    }

    public void delete(Long id) {
        Map<String,Object> mappedParameters = Collections.singletonMap("id", id);
        this.namedParameterJdbcTemplate.update(sqlDelete,mappedParameters);
    }
    public Optional<Author> findById(Long id) {
        Map<String,Object> mapperParameters = Collections.singletonMap("id", id);
        try {
            return Optional.ofNullable(this.namedParameterJdbcTemplate.queryForObject(sqlQueryAuthorById, mapperParameters, authorRowMapper));
        } catch (Exception e){
            throw new FindItemExecption("Could not find Author by ID " + id, e);
        }
    }
    @Transactional
    public List<Author> findAll() {
        return this.namedParameterJdbcTemplate.query(sqlQueryForAll, authorRowMapper);
    }

    RowMapper<Author> authorRowMapper = (ResultSet rs, int rowNum) -> new Author(rs.getLong("id"), rs.getString("name"));

    private Long update(Author author, String sql){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("id", author.getId());
        sqlParameterSource.addValue("name", author.getName());
        this.namedParameterJdbcTemplate.update(sql,sqlParameterSource,keyHolder);
        return keyHolder.getKey().longValue();
    }
}

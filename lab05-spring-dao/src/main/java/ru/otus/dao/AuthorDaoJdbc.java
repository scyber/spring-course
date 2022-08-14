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
public class AuthorDaoJdbc implements AuthorDao{

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    RowMapper<Author> authorRowMapper = (ResultSet rs, int rowNum) -> new Author(rs.getLong("id"), rs.getString("name"));

    public AuthorDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public long save(Author domain) {
        String sqlInsertAuthor = "insert into authors ( name ) values ( :name)";
        String sqlUpdateAuthor = "update authors set name = :name where id = :id";
        if(domain.getId() != null){
            return update(domain, sqlUpdateAuthor);
        } else {
            return update(domain, sqlInsertAuthor);
        }
    }

    @Override
    public void delete(long id) {
        String sqlDelete = "delete from authors where id = :id";
        Map<String,Object> mappedParameters = Collections.singletonMap("id", id);
        this.namedParameterJdbcTemplate.update(sqlDelete,mappedParameters);
    }

    @Override
    public Optional<Author> findById(long id) {
        String sqlQueryAuthorById = "select id, name from authors a where id = :id";
        Map<String,Object> mapperParameters = Collections.singletonMap("id", id);
        try {
            return Optional.ofNullable(this.namedParameterJdbcTemplate.queryForObject(sqlQueryAuthorById, mapperParameters, authorRowMapper));
        } catch (Exception e){
            throw new FindItemExecption("Could not find Author by ID " + id, e);
        }
    }

    @Override
    public List<Author> findAll() {
        String sqlQueryForAll = "select id, name from authors a ";
        return this.namedParameterJdbcTemplate.query(sqlQueryForAll, authorRowMapper);
    }

    private long update(Author author, String sql){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("id", author.getId());
        sqlParameterSource.addValue("name", author.getName());
        this.namedParameterJdbcTemplate.update(sql,sqlParameterSource,keyHolder);
        return keyHolder.getKey().longValue();
    }
}

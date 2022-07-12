package ru.otus.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Genre;
import ru.otus.exeptions.FindItemExecption;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public GenreDaoJdbc(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public long save(Genre domain) {
        String sqlInsertGenre = " insert into genres ( name ) values( :name) ";
        String sqlUpdateGenre = " update genres set name = :name where id = :id ";
        if (domain.getId() != null) {
            return update(domain, sqlUpdateGenre);
        } else {
            return update(domain, sqlInsertGenre);
        }
    }

    @Override
    public void delete(long id) {
        String sqlDeleteGenre = " delete from genres where id = :id ";
        Map<String, Object> mapperParameters = Collections.singletonMap("id", id);
        this.namedParameterJdbcTemplate.update(sqlDeleteGenre, mapperParameters);
    }

    @Override
    public Optional<Genre> findById(long id) {
        String sqlSelectById = "select id, name from genres where id = :id ";
        try {
            Map<String, Object> mappedParameters = Collections.singletonMap("id", id);
            return Optional.ofNullable(this.namedParameterJdbcTemplate.queryForObject(sqlSelectById, mappedParameters, genreRowMapper));
        } catch (Exception e) {
            throw new FindItemExecption("Could find Genre by ID " + id, e);
        }
    }

    @Override
    public List<Genre> findAll() {
        String sqlSelectAll = "select id, name from genres";
        return this.namedParameterJdbcTemplate.query(sqlSelectAll, genreRowMapper);
    }

    private RowMapper<Genre> genreRowMapper = (ResultSet rs, int numRow) -> new Genre(rs.getLong("id"), rs.getString("name"));

    private long update(Genre genre, String sql) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", genre.getId());
        mapSqlParameterSource.addValue("name", genre.getName());
        this.namedParameterJdbcTemplate.update(sql, mapSqlParameterSource, keyHolder);
        return keyHolder.getKey().longValue();
    }
}

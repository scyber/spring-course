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
public class GenreDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private String sqlSelectAll = "select id, name from genres";
    private String sqlSelectById = sqlSelectAll + " where id = :id ";
    private String sqlDeleteGenre = " delete from genres where id = :id ";
    private String sqlInsertGenre = " insert into genres ( name ) values( :name) ";
    private String sqlUpdateGenre = " update genres set name = :name where id = :id ";

    public GenreDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Long save(Genre domain) {
        if(domain.getId() != null){
           return update(domain, sqlUpdateGenre);
        } else {
           return update(domain, sqlInsertGenre);
        }
    }

    public void delete(Long id) {
        Map<String,Object> mapperParameters = Collections.singletonMap("id",id);
        this.namedParameterJdbcTemplate.update(sqlDeleteGenre,mapperParameters);
    }
    public Optional<Genre> findById(Long id) {
        try {
            Map<String,Object> mappedParameters = Collections.singletonMap("id",id);
            return Optional.ofNullable(this.namedParameterJdbcTemplate.queryForObject(sqlSelectById, mappedParameters,genreRowMapper));
        } catch (Exception e) {
            throw new FindItemExecption("Could find Genre by ID " + id, e);
        }

    }

    public List<Genre> findAll() {
        return this.namedParameterJdbcTemplate.query(sqlSelectAll, genreRowMapper);
    }

    private RowMapper<Genre> genreRowMapper = (ResultSet rs, int numRow) -> new Genre(rs.getLong("id"), rs.getString("name"));

    private Long update(Genre genre, String sql){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", genre.getId());
        mapSqlParameterSource.addValue("name", genre.getGenreName());
        this.namedParameterJdbcTemplate.update(sql,mapSqlParameterSource,keyHolder);
        return keyHolder.getKey().longValue();
    }
}

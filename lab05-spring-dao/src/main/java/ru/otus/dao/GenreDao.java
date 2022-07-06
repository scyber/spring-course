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

@Repository
public class GenreDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static String SQL_SELECT_ALL = "select id, name from genres";
    private static String SQL_SELECT_BY_ID = SQL_SELECT_ALL + " where id = :id ";
    private static String SQL_DELETE_GENRE = " delete from genres where id = :id ";
    private static String SQL_INSERT_GENRE = " insert into genres ( name ) values( :name) ";
    private static String SQL_UPDATE_GENRE = " update genres set name = :name where id = :id ";

    public GenreDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Long save(Genre domain) {
        if(domain.getId() != null){
            Genre genre = new Genre();
            genre.setId(domain.getId());
            genre.setGenreName(domain.getGenreName());
           return update(domain, SQL_UPDATE_GENRE);
        } else {
           return update(domain, SQL_INSERT_GENRE);
        }
    }

    public void delete(Long id) {
        Map<String,Object> mapperParameters = Collections.singletonMap("id",id);
        this.namedParameterJdbcTemplate.update(SQL_DELETE_GENRE,mapperParameters);
    }
    public Genre findById(Long id) {
        try {
            Map<String,Object> mappedParameters = Collections.singletonMap("id",id);
            return this.namedParameterJdbcTemplate.queryForObject(SQL_SELECT_BY_ID, mappedParameters,genreRowMapper);
        } catch (Exception e) {
            throw new FindItemExecption("Could find Genre by ID " + id, e);
        }

    }

    public List<Genre> findAll() {
        return this.namedParameterJdbcTemplate.query(SQL_SELECT_ALL, genreRowMapper);
    }

    private RowMapper<Genre> genreRowMapper = (ResultSet rs, int numRow) -> {
        return new Genre(rs.getLong(1), rs.getString(2));
    };

    private Long update(Genre genre, String sql){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", genre.getId());
        mapSqlParameterSource.addValue("name", genre.getGenreName());
        this.namedParameterJdbcTemplate.update(sql,mapSqlParameterSource,keyHolder);
        return keyHolder.getKey().longValue();
    }
}

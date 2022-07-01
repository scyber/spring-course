package ru.otus.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Genre;
import ru.otus.exeptions.FindItemExecption;
import ru.otus.repository.CommonRepository;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.Map;

@Repository
public class GenreDao implements CommonRepository<Genre> {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static String SQL_SELECT_ALL = "select id, genre_name from genres ";
    private static String SQL_SELECT_BY_ID = SQL_SELECT_ALL + " where id = :id ";
    private static String SQL_DELETE_GENRE = " delete from genres where id = :id ";
    private static String SQL_INSERT_GENRE = " insert into genres ( genre_name ) values( :genre_name) ";
    private static String SQL_UPDATE_GENRE = " update genres set genres_name = :genre_name where id = :id ";

    public GenreDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
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

    @Override
    public void delete(Long id) {
        Map<String,Object> mapperParameters = Collections.singletonMap("id",id);
        this.namedParameterJdbcTemplate.update(SQL_DELETE_GENRE,mapperParameters);
    }
    @Override
    public Genre findById(Long id) {
        try {
            Map<String,Object> mappedParameters = Collections.singletonMap("id",id);
            return this.namedParameterJdbcTemplate.queryForObject(SQL_SELECT_BY_ID,mappedParameters,genreRowMapper);
        } catch (Exception e) {
            throw new FindItemExecption("Could find Genre by ID " + id, e);
        }

    }
    @Override
    public Iterable<Genre> findAll() {
        return this.namedParameterJdbcTemplate.query(SQL_SELECT_ALL, genreRowMapper);
    }
    private RowMapper<Genre> genreRowMapper = (ResultSet rs, int numRow) -> {
        Genre genre = new Genre();
        genre.setId(rs.getLong("id"));
        genre.setGenreName(rs.getString("genre_name"));
        return genre;
    };
    private Long update(Genre genre, String sql){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", genre.getId());
        mapSqlParameterSource.addValue("genre_name", genre.getGenreName());
        this.namedParameterJdbcTemplate.update(sql,mapSqlParameterSource,keyHolder);
        return keyHolder.getKey().longValue();
    }
}

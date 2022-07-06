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

    private String sql_select_all = "select id, name from genres";
    private String sql_select_by_id = sql_select_all + " where id = :id ";
    private String sql_delete_genre = " delete from genres where id = :id ";
    private String sql_insert_genre = " insert into genres ( name ) values( :name) ";
    private String sql_update_genre = " update genres set name = :name where id = :id ";

    public GenreDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public Long save(Genre domain) {
        if(domain.getId() != null){
            Genre genre = new Genre();
            genre.setId(domain.getId());
            genre.setGenreName(domain.getGenreName());
           return update(domain, sql_update_genre);
        } else {
           return update(domain, sql_insert_genre);
        }
    }

    public void delete(Long id) {
        Map<String,Object> mapperParameters = Collections.singletonMap("id",id);
        this.namedParameterJdbcTemplate.update(sql_delete_genre,mapperParameters);
    }
    public Genre findById(Long id) {
        try {
            Map<String,Object> mappedParameters = Collections.singletonMap("id",id);
            return this.namedParameterJdbcTemplate.queryForObject(sql_select_by_id, mappedParameters,genreRowMapper);
        } catch (Exception e) {
            throw new FindItemExecption("Could find Genre by ID " + id, e);
        }

    }

    public List<Genre> findAll() {
        return this.namedParameterJdbcTemplate.query(sql_select_all, genreRowMapper);
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

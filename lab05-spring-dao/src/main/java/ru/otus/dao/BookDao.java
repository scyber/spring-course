package ru.otus.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Book;
import ru.otus.exeptions.FindItemExecption;
import ru.otus.repository.CommonRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

@Repository
public class BookDao implements CommonRepository<Book> {

    private static final String SQL_INSERT = "INSERT INTO BOOKS (name, author_id, genre_id )" +
            "values (:name, :author_id, :genre_id)";
    private static final String SQL_QUERY_FIND_ALL = "SELECT id, name, author_id, genre_id FROM BOOKS ";
    private static final String SQL_QUERY_FIND_BY_ID = SQL_QUERY_FIND_ALL + " WHERE ID = :id";
    private static final String SQL_UPDATE = "UPDATE BOOKS SET name = :name, author_id = :author_id, genre_id = :genre_id where ID =:id";
    private static final String SQL_DELETE = "DELETE FROM BOOKS WHERE ID = :id";

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public BookDao(JdbcOperations jdbc, NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbc = jdbc;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(Book domain) {
        if(domain.getId() != null){
            Book book = new Book();
            book.setId(domain.getId());
            book.setName(domain.getName());
            book.setAuthorId(domain.getAuthorId());
            book.setGenreId(domain.getGenreId());
            return update(book,SQL_UPDATE);
        }
        return update(domain,SQL_INSERT);
    }

    @Override
    public void delete(Long id) {
        Map<String,Object> mappedParameters = Collections.singletonMap("id", id);
        this.jdbcTemplate.update(SQL_DELETE, mappedParameters);
    }

    @Override
    public Book findById(Long id) {
        try {
         Map<String,Object> mappedParameters = Collections.singletonMap("id",id);
         return  this.jdbcTemplate.queryForObject(SQL_QUERY_FIND_BY_ID, mappedParameters, bookRowMapper);
        } catch (EmptyResultDataAccessException e){
            throw new FindItemExecption("Could not find book by id " + id, e);
        }
    }
    @Override
    public Iterable<Book> findAll() {
        return this.jdbcTemplate.query(SQL_QUERY_FIND_ALL, bookRowMapper);
    }

    private RowMapper<Book> bookRowMapper = (ResultSet rs, int rowNum) -> {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setName(rs.getString("name"));
        book.setAuthorId(rs.getLong("author_id"));
        book.setGenreId(rs.getLong("genre_id"));
        return book;
    };
    private Long update(Book book, String sql){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", book.getId());
        parameterSource.addValue("name", book.getName());
        parameterSource.addValue("author_id", book.getAuthorId());
        parameterSource.addValue("genre_id", book.getGenreId());
        this.jdbcTemplate.update(sql,parameterSource,keyHolder);
        return keyHolder.getKey().longValue();
    }
    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setId(rs.getLong("id"));
            book.setName(rs.getString("name"));
            book.setAuthorId(rs.getLong("author_id"));
            book.setId(rs.getLong("genre_id"));
            return book;
        }
    }
}

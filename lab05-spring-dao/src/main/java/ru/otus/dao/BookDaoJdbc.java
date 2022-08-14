package ru.otus.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.exeptions.FindItemExecption;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public class BookDaoJdbc implements BookDao {

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public BookDaoJdbc(JdbcOperations jdbc, NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbc = jdbc;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Book domain) {
        String sqlInsert = "INSERT INTO BOOKS (name, author_id, genre_id )" +
                "values (:name, :author_id, :genre_id)";
        String sqlUpdate = "UPDATE BOOKS SET name = :name, author_id = :author_id, genre_id = :genre_id where ID =:id";
        if (domain.getId() != null) {
            Book book = new Book();
            book.setId(domain.getId());
            book.setName(domain.getName());
            book.setAuthor(domain.getAuthor());
            book.setGenre(domain.getGenre());
            return update(book, sqlUpdate);
        }
        return update(domain, sqlInsert);
    }

    @Override
    public void delete(long id) {
        String sqlDelete = "DELETE FROM BOOKS WHERE ID = :id";
        Map<String, Object> mappedParameters = Collections.singletonMap("id", id);
        this.jdbcTemplate.update(sqlDelete, mappedParameters);
    }

    @Override
    public Optional<Book> findById(long id) {
        String sqlQueryFindById = "SELECT b.id, b.name, " +
                " b.author_id, a.name author_name," +
                " b.genre_id, g.name genre_name FROM BOOKS b " +
                " left join authors a on b.author_id = a.id " +
                " left join genres g on b.genre_id = g.id " +
                " WHERE b.id = :id";
        try {
            Map<String, Object> mappedParameters = Collections.singletonMap("id", id);
            return Optional.ofNullable(this.jdbcTemplate.queryForObject(sqlQueryFindById, mappedParameters, new BookRowMapper()));
        } catch (EmptyResultDataAccessException e) {
            throw new FindItemExecption("Could not find book by id " + id, e);
        }
    }

    @Override
    public List<Book> findAll() {
        String sqlQueryFindAll = "SELECT b.id, b.name, " +
                " b.author_id, a.name author_name," +
                " b.genre_id, g.name genre_name FROM BOOKS b " +
                " left join authors a on b.author_id = a.id " +
                " left join genres g on b.genre_id = g.id ";
        return jdbc.query(sqlQueryFindAll, new BookRowMapper());
    }

    @Override
    public void updateNameById(long id, String name) {
        String sqlQueryUpdateById = "UPDATE BOOKS SET name = :name where ID =:id ";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", id);
        mapSqlParameterSource.addValue("name", name);
        this.jdbcTemplate.update(sqlQueryUpdateById, mapSqlParameterSource);
    }

    private Long update(Book book, String sql) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", book.getId());
        parameterSource.addValue("name", book.getName());
        parameterSource.addValue("author_id", book.getAuthor().getId());
        parameterSource.addValue("genre_id", book.getGenre().getId());
        this.jdbcTemplate.update(sql, parameterSource, keyHolder);
        return keyHolder.getKey().longValue();
    }

    private static final class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setId(rs.getLong("id"));
            book.setName(rs.getString("name"));
            Author author = new Author();
            author.setId(rs.getLong("author_id"));
            author.setName(rs.getString("author_name"));
            book.setAuthor(author);
            Genre genre = new Genre();
            genre.setId(rs.getLong("genre_id"));
            genre.setName(rs.getString("genre_name"));
            book.setGenre(genre);
            return book;
        }
    }

}

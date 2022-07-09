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
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Repository
public class BookDao {

    private final String sqlInsert = "INSERT INTO BOOKS (name, author_id, genre_id )" +
            "values (:name, :author_id, :genre_id)";
    private final String sqlQueryFindAll = "SELECT b.id, b.name, " +
            " b.author_id, a.name author_name," +
            " b.genre_id, g.name genre_name FROM BOOKS b " +
            " left join authors a on b.author_id = a.id " +
            " left join genres g on b.genre_id = g.id ";

    private final String sqlQueryFindById = sqlQueryFindAll + " WHERE b.id = :id";
    private final String sqlUpdate = "UPDATE BOOKS SET name = :name, author_id = :author_id, genre_id = :genre_id where ID =:id";
    private final String sqlDelete = "DELETE FROM BOOKS WHERE ID = :id";

    private final JdbcOperations jdbc;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public BookDao(JdbcOperations jdbc, NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbc = jdbc;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(Book domain) {
        if(domain.getId() != null){
            Book book = new Book();
            book.setId(domain.getId());
            book.setName(domain.getName());
            book.setAuthor(domain.getAuthor());
            book.setGenre(domain.getGenre());
            return update(book, sqlUpdate);
        }
        return update(domain, sqlInsert);
    }

    public void delete(Long id) {
        Map<String,Object> mappedParameters = Collections.singletonMap("id", id);
        this.jdbcTemplate.update(sqlDelete, mappedParameters);
    }

    public Book findById(Long id) {
        try {
         Map<String,Object> mappedParameters = Collections.singletonMap("id",id);
         return  this.jdbcTemplate.queryForObject(sqlQueryFindById, mappedParameters, bookRowMapper);
        } catch (EmptyResultDataAccessException e){
            throw new FindItemExecption("Could not find book by id " + id, e);
        }
    }

    public List<Book> findAll() {
       return jdbc.query(sqlQueryFindAll, bookRowMapper);
    }

    private RowMapper<Book> bookRowMapper = (ResultSet rs, int rowNum) -> {
        Book book = new Book();
        book.setId(rs.getLong("id"));
        book.setName(rs.getString("name"));
        Author author = new Author();
        author.setId(rs.getLong("author_id"));
        author.setName(rs.getString("author_name"));
        book.setAuthor(author);
        Genre genre = new Genre();
        genre.setId(rs.getLong("genre_id"));
        genre.setGenreName(rs.getString("genre_name"));
        book.setGenre(genre);
        return book;
    };

    private Long update(Book book, String sql){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", book.getId());
        parameterSource.addValue("name", book.getName());
        parameterSource.addValue("author_id", book.getAuthor().getId());
        parameterSource.addValue("genre_id", book.getGenre().getId());
        this.jdbcTemplate.update(sql,parameterSource,keyHolder);
        return keyHolder.getKey().longValue();
    }

}

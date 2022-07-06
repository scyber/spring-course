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

    private static final String SQL_INSERT = "INSERT INTO BOOKS (name, author_id, genre_id )" +
            "values (:name, :author_id, :genre_id)";
    private static final String SQL_QUERY_FIND_ALL = "SELECT b.id, b.name, " +
            " b.author_id, a.name author_name," +
            " b.genre_id, g.name genre_name FROM BOOKS b " +
            " left join authors a on b.author_id = a.id " +
            " left join genres g on b.genre_id = g.id ";

    private static final String SQL_QUERY_FIND_BY_ID = SQL_QUERY_FIND_ALL + " WHERE b.id = :id";
    private static final String SQL_UPDATE = "UPDATE BOOKS SET name = :name, author_id = :author_id, genre_id = :genre_id where ID =:id";
    private static final String SQL_DELETE = "DELETE FROM BOOKS WHERE ID = :id";

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
            return update(book,SQL_UPDATE);
        }
        return update(domain,SQL_INSERT);
    }

    public void delete(Long id) {
        Map<String,Object> mappedParameters = Collections.singletonMap("id", id);
        this.jdbcTemplate.update(SQL_DELETE, mappedParameters);
    }

    public Book findById(Long id) {
        try {
         Map<String,Object> mappedParameters = Collections.singletonMap("id",id);
         return  this.jdbcTemplate.queryForObject(SQL_QUERY_FIND_BY_ID, mappedParameters, bookRowMapper);
        } catch (EmptyResultDataAccessException e){
            throw new FindItemExecption("Could not find book by id " + id, e);
        }
    }

    public List<Book> findAll() {
//       Map<Long,Book> bookMap = jdbc.query(SQL_QUERY_FIND_ALL, new BookResultSetExtractor());
//       return bookMap.values().stream().collect(Collectors.toList());
       return jdbc.query(SQL_QUERY_FIND_ALL, bookRowMapper);

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

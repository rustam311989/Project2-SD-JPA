package nachren.dao;

import nachren.models.Book;
import nachren.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

//    public Optional<Person> show(String fio){
//        return jdbcTemplate.query("SELECT * FROM Person where fio=?", new Object[]{fio},
//                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
//    }


    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM Book where book_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public void save(Book book){
        jdbcTemplate.update("insert into Book(title, author, year) values(?,?,?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updatedBook){
        jdbcTemplate.update("update Book set title=?, author=?, year=? where book_id=?",
                updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYear(), id);
    }

    public void update2(int id, Integer person_id){
        jdbcTemplate.update("update Book set person_id=? where book_id=?", person_id, id);
    }

    public void delete(int id){
        jdbcTemplate.update("delete from Book where book_id=?",id);
    }

    public List<Book> getPersonsBooks(int id){
        return jdbcTemplate.query("SELECT * FROM Book where person_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }

}

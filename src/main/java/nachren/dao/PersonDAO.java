package nachren.dao;

import nachren.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> show(String fio){
        return jdbcTemplate.query("SELECT * FROM Person where fio=?", new Object[]{fio},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }


    public Person show(Integer id){
        if(id != null) {
            return jdbcTemplate.query("SELECT * FROM Person where person_id=?", new Object[]{id},
                    new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
        }
        else{
            return null;
        }
    }

    public void save(Person person){
        jdbcTemplate.update("insert into Person(fio, year) values(?,?)",
                person.getFio(), person.getYear());
    }

    public void update(int id, Person updatedPerson){
        jdbcTemplate.update("update Person set fio=?,year=? where person_id=?",
                updatedPerson.getFio(), updatedPerson.getYear(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("delete from Person where person_id=?",id);
    }

}

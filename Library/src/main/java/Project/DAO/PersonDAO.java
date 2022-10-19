package Project.DAO;

import Project.Models.Book;
import Project.Models.Person;
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
    //Проверка на такое же ФИО
    public Optional<Person> show(String fullName){
        return jdbcTemplate.query("SELECT * FROM person WHERE fullName=?",new Object[]{fullName},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person",new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person where person_id=?",new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person (fullName,yearOfBirth) values (?,?)",
                person.getFullName(),person.getYearOfBirth());
    }
    public void update(int id, Person updatedPerson){
        jdbcTemplate.update("UPDATE person set values fullName=?,yearOfBirth=? where person_id=?",updatedPerson.getFullName(),
                updatedPerson.getYearOfBirth(),id);

    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM person where person_id=?",id);
    }
    public List<Book> getPersonBooks(int id){
        return jdbcTemplate.query("SELECT * FROM book where person_id=?",new Object[]{id},new BeanPropertyRowMapper<>(Book.class));
    }
}

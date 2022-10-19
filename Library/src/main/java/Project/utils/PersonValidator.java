package Project.utils;

import Project.DAO.PersonDAO;
import Project.Models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;
    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override//Чтобы не смогли использовать на других классах
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person)target;
        if(personDAO.show(person.getFullName()).isPresent()){//Проверка на нулл, но лучше
            errors.rejectValue("fullName","","Человек с таким ФИО уже существует");
        }
        //Посмотреть есть ли человек с таким же ФИО в бд
    }
}

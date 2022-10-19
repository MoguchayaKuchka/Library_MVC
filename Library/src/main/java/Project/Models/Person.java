package Project.Models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {
    private int person_id;
    @NotEmpty(message = "ФИО не может быть пустым")
    @Size(min=3,max=50,message="ФИО должно занимать от 3 до 50 символов")
    private String fullName;
    @Min(value=1900,message="Год рождения не может быть меньше 1900")
    private int yearOfBirth;

    public Person(String fullName, int yearOfBirth) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }
    public Person(){

    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}

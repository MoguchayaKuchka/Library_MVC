package Project.Models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int book_id;
    @NotEmpty(message="Название книги не может быть пустым")
    @Size(min=3,max=50,message="Название книги должно занимать от 3 до 50 символов")
    private String title;
    @NotEmpty(message="Имя автора не может быть пустым")
    @Size(min=3,max=50,message="Имя автора должно занимать от 3 до 50 символов")
    private String author;
    @Min(value=1500,message="Год выпуска книги не может быть меньше 1500")
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }
    public Book(){

    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

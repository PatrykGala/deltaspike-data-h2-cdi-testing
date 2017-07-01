package pl.patrykgala.ee.testing.jpa.entity;

import java.util.List;

import javax.persistence.*;

@Entity
public class Shelve {

    @Id
    @GeneratedValue
    private Long id;

    private String place;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Book.class)
    private List<Book> books;

    public Shelve() {
    }

    public Shelve(String place, List<Book> books) {
        this.place = place;
        this.books = books;
        books.forEach(b -> b.setShelve(this));
    }

    public Shelve(String place) {
        this.place = place;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

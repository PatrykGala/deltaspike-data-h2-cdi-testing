package pl.patrykgala.ee.testing.jpa.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String title;

    @ManyToOne(targetEntity = Shelve.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "shelve_id")
    private Shelve shelve;

    public Book(String title) {
        this.title = title;
    }

    public Book() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Shelve getShelve() {
        return shelve;
    }

    public void setShelve(Shelve shelve) {
        this.shelve = shelve;
    }
}

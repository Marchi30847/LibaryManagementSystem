package data.entities;

import data.annotations.Display;
import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @Display
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Display
    @Column(nullable = false)
    private String title;

    @Display
    @Column(nullable = false)
    private String author;


    @ManyToOne
    @JoinColumn(name = "publisherId", nullable = false)
    private Publisher publisher;

    @Display
    @Column(nullable = false)
    private int publicationYear;

    @Display
    @Column(nullable = false, unique = true)
    private String isbn;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }


    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return title;
    }
}

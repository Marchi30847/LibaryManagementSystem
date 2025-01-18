package data.entities;

import data.annotations.Display;
import jakarta.persistence.*;

@Entity
@Table(name = "copy")
public class Copy {
    @Display
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Display
    @ManyToOne
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;

    @Display
    @Column(nullable = false)
    private int copyNumber;

    @Display
    @Column(nullable = false)
    private String status;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(int copyNumber) {
        this.copyNumber = copyNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}

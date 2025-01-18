package data.entities;

import data.annotations.Display;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "borrowing")
public class Borrowing {
    @Display
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Display
    @ManyToOne
    @JoinColumn(name = "MEMBERID", nullable = false)
    private Member member;

    @Display
    @ManyToOne
    @JoinColumn(name = "COPYID", nullable = false)
    private Copy copy;

    @Display
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date borrowDate;

    @Display
    @Temporal(TemporalType.DATE)
    private Date returnDate;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Copy getCopy() {
        return copy;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return copy.getBook().getTitle();
    }
}

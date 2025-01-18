package data.entities;

import data.annotations.Display;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "librarian")
public class Librarian {
    @Display
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    @Display
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date employmentDate;

    @Display
    @Column(nullable = false)
    private String position;

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

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(Date employmentDate) {
        this.employmentDate = employmentDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return getMember().getName();
    }
}

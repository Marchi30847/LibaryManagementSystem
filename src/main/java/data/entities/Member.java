package data.entities;

import data.annotations.Display;
import jakarta.persistence.*;

@Entity
@Table(name = "Member")
public class Member {
    @Display
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Display
    @Column(nullable = false)
    private String name;

    @Display
    @Column(nullable = false, unique = true)
    private String email;

    @Display
    @Column(nullable = false)
    private String phoneNumber;

    @Display
    @Column(nullable = false)
    private String address;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private Librarian librarian;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Librarian getLibrarian() {
        return librarian;
    }

    public void setLibrarian(Librarian librarian) {
        this.librarian = librarian;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}

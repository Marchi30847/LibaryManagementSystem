package data.entities;

import data.annotations.Display;
import jakarta.persistence.*;

@Entity
@Table(name = "publisher")
public class Publisher {
    @Display
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Display
    @Column(nullable = false)
    private String name;

    @Display
    @Column(nullable = false)
    private String address;

    @Display
    @Column(nullable = false)
    private String phoneNumber;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return name;
    }
}

package data.entities;

import data.annotations.Display;
import jakarta.persistence.*;

/**
 * Represents a Publisher entity in the system.
 * This class is mapped to the "publisher" table in the database.
 */
@Entity
@Table(name = "publisher")
public class Publisher {

    /**
     * The unique ID of the publisher.
     * This field is auto-generated by the database.
     */
    @Display
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The name of the publisher.
     * This field cannot be null.
     */
    @Display
    @Column(nullable = false)
    private String name;

    /**
     * The address of the publisher.
     * This field cannot be null.
     */
    @Display
    @Column(nullable = false)
    private String address;

    /**
     * The phone number of the publisher.
     * This field cannot be null.
     */
    @Display
    @Column(nullable = false)
    private String phoneNumber;

    /**
     * Retrieves the unique ID of the publisher.
     *
     * @return The ID of the publisher.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique ID of the publisher.
     *
     * @param id The ID to set for the publisher.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the publisher.
     *
     * @return The name of the publisher.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the publisher.
     *
     * @param name The name to set for the publisher.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the address of the publisher.
     *
     * @return The address of the publisher.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the publisher.
     *
     * @param address The address to set for the publisher.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Retrieves the phone number of the publisher.
     *
     * @return The phone number of the publisher.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the publisher.
     *
     * @param phoneNumber The phone number to set for the publisher.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns a string representation of the publisher.
     * This implementation returns the publisher's ID as a string.
     *
     * @return A string representation of the publisher.
     */
    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
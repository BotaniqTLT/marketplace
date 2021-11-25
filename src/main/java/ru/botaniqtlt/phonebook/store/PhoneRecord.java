package ru.botaniqtlt.phonebook.store;

import java.util.Objects;

/**
 * Класс записи
 */
public class PhoneRecord {

    private Long id;

    private String firstName;

    private String lastName;

    private String phone;

    public PhoneRecord(Long id, String firstName, String lastName, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    public PhoneRecord(String firstName, String lastName, String phone) {
        this(null, firstName, lastName, phone);
    }

    public PhoneRecord() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneRecord phoneRecord = (PhoneRecord) o;
        return Objects.equals(id, phoneRecord.id) &&
                Objects.equals(firstName, phoneRecord.firstName) &&
                Objects.equals(lastName, phoneRecord.lastName) &&
                Objects.equals(phone, phoneRecord.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phone);
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public PhoneRecord copy() {
        return new PhoneRecord(id,firstName,lastName,phone);
    }
}

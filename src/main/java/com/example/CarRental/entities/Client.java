package com.example.CarRental.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "clients")
public class Client extends EntityId {
    private String lastName;
    private String name;
    private String surname;
    private LocalDate birthday;
    private String phone;
    private String email;
    private int drivingExperience;

    @Column(name = "last_name", length = 50, nullable = false)
    public String getLastName() {
        return lastName;
    }

    @Column(length = 50, nullable = false)
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Column(length = 50, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 50)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    @Column(nullable = false)
    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    @Column(length = 15, nullable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Column(length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Column(nullable = false)
    public int getDrivingExperience() {
        return drivingExperience;
    }

    public void setDrivingExperience(int drivingExperience) {
        this.drivingExperience = drivingExperience;
    }

    public Client() {
    }
}

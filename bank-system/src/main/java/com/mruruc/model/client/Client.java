package com.mruruc.model.client;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class Client {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private Gender gender;
    private List<String> phone;
    private String email;
    private Long addressID;


    public Client(String firstName,
                  String lastName,
                  LocalDate dob,
                  Gender gender,
                  List<String> phone,
                  String email,
                  Long addressID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.addressID = addressID;
    }
    public Client(Long id,
                  String firstName,
                  String lastName,
                  LocalDate dob,
                  Gender gender,
                  List<String> phone,
                  String email,
                  Long addressID) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.addressID = addressID;
    }
    public Client(Long id,
                  String firstName,
                  String lastName,
                  LocalDate dob,
                  Gender gender,
                  List<String> phone,
                  String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
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

    public List<String> getPhone() {
        return phone;
    }

    public void setPhone(List<String> phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return Period.between(LocalDate.now(),this.dob).getYears();
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDate getDob() {
        return dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Long getAddressID() {
        return addressID;
    }

    public void setAddress(Long addressID) {
        this.addressID = addressID;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + this.getAge() +
                ", gender=" + gender +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                '}';
    }


}

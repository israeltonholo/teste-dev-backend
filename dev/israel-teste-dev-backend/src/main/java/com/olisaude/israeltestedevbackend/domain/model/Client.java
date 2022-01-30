package com.olisaude.israeltestedevbackend.domain.model;

import org.json.JSONObject;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "user_client_user")
public class Client{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public OffsetDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(OffsetDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public OffsetDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(OffsetDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public OffsetDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(OffsetDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Column(name = "surname")
    private String surname;

    @Column(name = "birth_date")
    private OffsetDateTime birthDate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "registration_date")
    private OffsetDateTime registrationDate;

    @Column(name = "last_update")
    private OffsetDateTime lastUpdate;

}

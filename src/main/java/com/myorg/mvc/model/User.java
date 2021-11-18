package com.myorg.mvc.model;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.time.LocalDate;

@Entity
public class User {

    @javax.persistence.Id
    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private String email;
    private LocalDate birthday;

    public User() {
        this.id = id;
    }

    public User(
            Long id,
            String name,
            String email,
            LocalDate dob

    ) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthday = dob;
    }

    public User(
            String name,
            String email,
            LocalDate dob
    ) {
        this.name = name;
        this.email = email;
        this.birthday = dob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }


    @Override
    public String toString() {
        return "User name= " + name + ", " + birthday + ", email= " + email;
    }
}

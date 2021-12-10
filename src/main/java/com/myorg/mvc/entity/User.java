package com.myorg.mvc.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String lastName;
    private String firstName;
    private String email;
    private String birthday;
    private String username;
    private String password;

    public User() {
    }

    public User(
            Long userId,
            String lastName,
            String firstName,
            String email,
            String dob
    ) {
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.birthday = dob;
    }

    public User(String lastName, String firstName, String email,
                String birthday, String username, String password,
                String matchingPassword) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.birthday = birthday;
        this.username = username;
        this.password = password;

    }

    public User(
            String lastName,
            String firstName,
            String email,
            String dob
    ) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.birthday = dob;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId)
                && lastName.equals(user.lastName)
                && firstName.equals(user.firstName)
                && email.equals(user.email)
                && birthday.equals(user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, lastName, firstName, email, birthday);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}

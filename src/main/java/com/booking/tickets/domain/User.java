package com.booking.tickets.domain;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.getAll", query = "select u from User u"),
        @NamedQuery(name = "User.getByLogin", query = "select u from User u where u.login = :login")})
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String fullName;
    @Column(name = "email")
    private String email;
    @Enumerated(STRING)
    @Column(name = "access")
    private UserRole accessRole;
    @OneToMany(mappedBy = "user")
    private Set<Ticket> bookedTickets = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getAccessRole() {
        return accessRole;
    }

    public void setAccessRole(UserRole accessRole) {
        this.accessRole = accessRole;
    }

    public Set<Ticket> getBookedTickets() {
        return bookedTickets;
    }

    public void setBookedTickets(Set<Ticket> bookedTickets) {
        this.bookedTickets = bookedTickets;
    }
}

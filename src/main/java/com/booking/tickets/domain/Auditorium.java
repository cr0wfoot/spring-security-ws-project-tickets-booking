package com.booking.tickets.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "auditoriums")
@NamedQuery(name = "Auditorium.getAll", query = "select a from Auditorium a")
public class Auditorium {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "seats")
    private int seatsQuantity;
    @Column(name = "vip_seats")
    private String vipSeats;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeatsQuantity() {
        return seatsQuantity;
    }

    public void setSeatsQuantity(int seatsQuantity) {
        this.seatsQuantity = seatsQuantity;
    }

    public String getVipSeats() {
        return vipSeats;
    }

    public void setVipSeats(String vipSeats) {
        this.vipSeats = vipSeats;
    }
}

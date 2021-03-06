package com.booking.tickets.domain;

import javax.persistence.*;

import java.util.List;

import static com.booking.tickets.domain.Auditorium.SEATS_SEPARATOR;
import static java.util.Arrays.asList;
import static javax.persistence.GenerationType.IDENTITY;
import static org.apache.commons.lang3.StringUtils.join;

@Entity
@Table(name = "tickets")
@NamedQuery(name = "Ticket.getAll", query = "select t from Ticket t")
public class Ticket {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
    @Column(name = "seats")
    private String bookedSeats;
    @Column(name = "price")
    private double totalPrice;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<String> getListOfBookedSeats() {
        return asList(bookedSeats.split(SEATS_SEPARATOR));
    }

    public String getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(String bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public void setBookedSeats(List<String> vipSeats) {
        this.bookedSeats = join(vipSeats, SEATS_SEPARATOR);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

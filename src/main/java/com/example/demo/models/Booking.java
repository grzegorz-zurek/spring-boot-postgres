package com.example.demo.models;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "from_date", nullable = false)
    @NonNull
    private Timestamp fromDate;
    @Column(name = "to_date", nullable = false)
    @NonNull
    private Timestamp toDate;
    @Column(nullable = false)
    @NonNull
    private Integer price;
    @Column(nullable = false)
    @NonNull
    private Long customer_id;
    @Column(name = "room_id", nullable = false)
    @NonNull
    private Long roomId;

    public Booking(Timestamp fromDate, Timestamp toDate, Integer price, Long customer_id, Long roomId) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.price = price;
        this.customer_id = customer_id;
        this.roomId = roomId;
    }

    public boolean isDateWithinBooking(Timestamp date) {
        return date.after(fromDate) && date.before(toDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getFromDate() {
        return fromDate;
    }

    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    public Timestamp getToDate() {
        return toDate;
    }

    public void setToDate(Timestamp toDate) {
        this.toDate = toDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Long customer_id) {
        this.customer_id = customer_id;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(fromDate, booking.fromDate) && Objects.equals(toDate, booking.toDate) && Objects.equals(price, booking.price) && Objects.equals(customer_id, booking.customer_id) && Objects.equals(roomId, booking.roomId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromDate, toDate, price, customer_id, roomId);
    }

    @Override
    public String toString() {
        return "Bookings{" +
                "id=" + id +
                ", from_date=" + fromDate +
                ", to_date=" + toDate +
                ", price=" + price +
                ", customer_id=" + customer_id +
                ", room_id=" + roomId +
                '}';
    }
}

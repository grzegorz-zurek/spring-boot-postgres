package com.example.demo.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private Integer size;
    @Column(nullable = false)
    private Integer price;

    public Room(String number, Integer size, Integer price) {
        this.number = number;
        this.size = size;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(number, room.number) && Objects.equals(size, room.size) && Objects.equals(price, room.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, size, price);
    }

    @Override
    public String toString() {
        return "Rooms{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", size=" + size +
                ", price=" + price +
                '}';
    }
}

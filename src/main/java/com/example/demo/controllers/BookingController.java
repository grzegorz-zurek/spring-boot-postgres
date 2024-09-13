package com.example.demo.controllers;

import com.example.demo.models.Booking;
import com.example.demo.services.interfaces.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService service;

    @Autowired
    BookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getBookings() {
        return ResponseEntity.ok(service.getBookings());
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("bookingId") Long id) {
        Booking booking = service.getBookingById(id);
        return booking != null ? ResponseEntity.ok(booking) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping
    public ResponseEntity<Booking> addBooking(@Validated @RequestBody Booking booking) {
        Booking result = service.addBooking(booking);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.status(400).body(null);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Booking> deleteBooking(@PathVariable("bookingId") Long id) {
        Booking booking = service.deleteBooking(id);
        return booking != null ? ResponseEntity.ok(booking) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}

package com.example.demo.services.interfaces;

import com.example.demo.models.Booking;

import java.sql.Timestamp;
import java.util.List;

public interface BookingService {
    List<Booking> getBookings();

    Booking getBookingById(Long id);

    Booking addBooking(Booking booking);

    Booking updateBooking(Long id, Timestamp from_date, Timestamp to_date, Integer price, Long customer_id, Long room_id);

    Booking deleteBooking(Long id);
}

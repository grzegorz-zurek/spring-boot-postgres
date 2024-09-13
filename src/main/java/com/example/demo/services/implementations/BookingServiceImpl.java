package com.example.demo.services.implementations;

import com.example.demo.models.Booking;
import com.example.demo.repositories.BookingsRepository;
import com.example.demo.services.interfaces.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingsRepository bookingsRepository;

    @Autowired
    public BookingServiceImpl(BookingsRepository bookingsRepository) {
        this.bookingsRepository = bookingsRepository;
    }

    @Override
    public List<Booking> getBookings() {
        return bookingsRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingsRepository.findById(id).orElse(null);
    }

    @Override
    public Booking addBooking(Booking booking) {
        List<Booking> bookings = bookingsRepository.findAllByFromDateLessThanEqualAndToDateGreaterThanEqual(booking.getFromDate(),booking.getToDate());
        return bookings.isEmpty() ? bookingsRepository.save(booking) : null;
    }

    @Override
    public Booking updateBooking(Long id, Timestamp from_date, Timestamp to_date, Integer price, Long customer_id, Long room_id) {
        if(id == null) return null;
        Booking booking = bookingsRepository.findById(id).orElse(null);
        if(booking == null) return null;
        if(from_date != null) {
            booking.setFromDate(from_date);
        }
        if(to_date != null) {
            booking.setToDate(to_date);
        }
        if(price != null) {
            booking.setPrice(price);
        }
        if(customer_id != null) {
            booking.setCustomer_id(customer_id);
        }
        if(room_id != null) {
            booking.setRoomId(room_id);
        }
        return bookingsRepository.save(booking);
    }

    @Override
    public Booking deleteBooking(Long id) {
        Booking booking = bookingsRepository.findById(id).orElse(null);
        if(booking == null) return null;
        bookingsRepository.deleteById(id);
        return booking;
    }
}

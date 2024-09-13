package com.example.demo.repositories;

import com.example.demo.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface BookingsRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByRoomId(Long id);
    List<Booking> findAllByFromDateLessThanEqualAndToDateGreaterThanEqual(Timestamp from_date, Timestamp to_date);
}

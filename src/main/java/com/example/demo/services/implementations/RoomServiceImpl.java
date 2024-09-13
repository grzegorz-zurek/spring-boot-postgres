package com.example.demo.services.implementations;

import com.example.demo.models.Booking;
import com.example.demo.models.Room;
import com.example.demo.repositories.BookingsRepository;
import com.example.demo.repositories.RoomsRepository;
import com.example.demo.services.interfaces.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomsRepository roomsRepository;
    private final BookingsRepository bookingsRepository;

    @Autowired
    public RoomServiceImpl(RoomsRepository roomsRepository, BookingsRepository bookingsRepository) {
        this.roomsRepository = roomsRepository;
        this.bookingsRepository = bookingsRepository;
    }

    @Override
    public List<Room> getRooms() {
        return roomsRepository.findAll();
    }

    @Override
    public Room getRoomById(Long id) {
        return roomsRepository.findById(id).orElse(null);
    }

    @Override
    public boolean getAvailabilityOnDate(Long id, Timestamp onDate) {
        List<Booking> bookings = bookingsRepository.findAllByRoomId(id);
        for(Booking booking : bookings) {
            if(booking.isDateWithinBooking(onDate)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Room> getAvailableRoomsOnInterval(Timestamp startDate, Timestamp endDate) {
        List<Room> rooms = roomsRepository.findAll();
        List<Booking> bookings = bookingsRepository.findAllByFromDateLessThanEqualAndToDateGreaterThanEqual(startDate,endDate);
        List<Long> bookedRoomsIds = bookings.stream()
                .map(Booking::getRoomId)
                .toList();
        List<Room> availableRooms = new ArrayList<>();
        for(Room room: rooms) {
            if(!bookedRoomsIds.contains(room.getId())) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
}

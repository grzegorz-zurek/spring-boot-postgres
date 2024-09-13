package com.example.demo.services.interfaces;

import com.example.demo.models.Room;

import java.sql.Timestamp;
import java.util.List;

public interface RoomService {
    List<Room> getRooms();

    Room getRoomById(Long id);

    boolean getAvailabilityOnDate(Long id, Timestamp onDate);

    List<Room> getAvailableRoomsOnInterval(Timestamp startDate, Timestamp endDate);
}

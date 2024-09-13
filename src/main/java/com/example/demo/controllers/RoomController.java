package com.example.demo.controllers;

import com.example.demo.models.Room;
import com.example.demo.services.interfaces.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class RoomController {
    private final RoomService service;

    @Autowired
    public RoomController(RoomService service) {
        this.service = service;
    }

    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> getRooms() {
        return ResponseEntity.ok(service.getRooms());
    }

    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<Room> getRoomById(@PathVariable("roomId") Long id) {
        Room room = service.getRoomById(id);
        return room != null ? ResponseEntity.ok(room) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/available/room/{roomId}")
    public ResponseEntity<Boolean> getRoomAvailabilityById(@PathVariable("roomId") Long id, @RequestParam("onDate") Timestamp onDate) {
        return ResponseEntity.ok(service.getAvailabilityOnDate(id,onDate));
    }

    @GetMapping("/available/rooms")
    public ResponseEntity<List<Room>> getAvailableRoomsBetweenDates(@RequestParam("startDate") Timestamp startDate, @RequestParam("endDate") Timestamp endDate) {
        if(startDate.after(endDate)) return ResponseEntity.status(422).body(null);
        List<Room> rooms = service.getAvailableRoomsOnInterval(startDate,endDate);
        return !rooms.isEmpty() ? ResponseEntity.ok(rooms) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(rooms);
    }
}

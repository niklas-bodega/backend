package com.lasias.hostelbookingbackend.controllers;

import com.lasias.hostelbookingbackend.dtos.AvailableRoomsDTO;
import com.lasias.hostelbookingbackend.dtos.RoomTypeDTO;
import com.lasias.hostelbookingbackend.services.RoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms/roomTypes")
public class RoomTypeController {
    private final RoomTypeService roomTypeService;

    @GetMapping
    public List<RoomTypeDTO> getAllRoomTypes() {
        return roomTypeService.getAllRoomTypes();
    }

    @GetMapping("/available")
    public ResponseEntity<List<AvailableRoomsDTO>> getAllRoomTypesByAvailability(
            @RequestParam LocalDate checkInDate,
            @RequestParam LocalDate checkOutDate,
            @RequestParam Integer nrOfGuests) {
        return ResponseEntity.ok(roomTypeService.getAllRoomTypesByAvailability(checkInDate, checkOutDate, nrOfGuests));
    }

    @GetMapping("/available/{id:\\d+}")
    public ResponseEntity<Boolean> checkRoomTypeAvailability(
            @PathVariable Long id,
            @RequestParam LocalDate checkInDate,
            @RequestParam LocalDate checkOutDate,
            @RequestParam String bookingNumber
    ) {
        return ResponseEntity.ok(roomTypeService.checkIfRoomTypeIsAvailable(id,checkInDate, checkOutDate,bookingNumber));
    }

    @GetMapping("/{id:\\d+}")
    public RoomTypeDTO getRoomTypeById(@PathVariable Long id) {
        return roomTypeService.getRoomTypeById(id);
    }
}

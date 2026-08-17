package com.lasias.hostelbookingbackend.services;

import com.lasias.hostelbookingbackend.config.BookingConstants;
import com.lasias.hostelbookingbackend.dtos.AvailableRoomsDTO;
import com.lasias.hostelbookingbackend.dtos.RoomTypeDTO;
import com.lasias.hostelbookingbackend.exceptions.RoomNotFoundException;
import com.lasias.hostelbookingbackend.models.RoomType;
import com.lasias.hostelbookingbackend.repositories.RoomTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public class RoomTypeService {
    private final RoomTypeRepository roomTypeRepository;


    public List<RoomTypeDTO> getAllRoomTypes() {
        return roomTypeRepository.findAll().stream().map(this::roomTypeToDTO).toList();
    }

    private RoomTypeDTO roomTypeToDTO(RoomType roomType) {
        return RoomTypeDTO.builder()
                .id(roomType.getId())
                .name(roomType.getName())
                .type(roomType.getType())
                .description(roomType.getDescription())
                .price(roomType.getPrice())
                .size(roomType.getSize())
                .capacity(roomType.getCapacity())
                .extraBedAvailable(roomType.isExtraBedAvailable())
                .badge(roomType.getBadge())
                .featured(roomType.isFeatured())
                .imageUrl(roomType.getImageUrl())
                .build();
    }


    public RoomTypeDTO getRoomTypeById(Long id) {
        return roomTypeRepository.findById(id).map(this::roomTypeToDTO).orElse(null);
    }

    public boolean checkIfRoomTypeIsAvailable(Long roomTypeId, LocalDate checkInDate, LocalDate checkOutDate, String bookingNumber) {
        LocalDateTime checkIn = checkInDate.atTime(BookingConstants.CHECK_IN_TIME);
        LocalDateTime checkOut = checkOutDate.atTime(BookingConstants.CHECK_OUT_TIME);

        RoomType rt = roomTypeRepository.findById(roomTypeId).orElseThrow(() -> new RoomNotFoundException("Room Type not found"));
        int count = roomTypeRepository.countAllByAvailability(rt, checkIn, checkOut, bookingNumber);
        return count > 0;
    }

    public List<AvailableRoomsDTO> getAllRoomTypesByAvailability(LocalDate checkInDate, LocalDate checkOutDate, Integer nrOfGuests) {
        LocalDateTime checkIn = checkInDate.atTime(BookingConstants.CHECK_IN_TIME);
        LocalDateTime checkOut = checkOutDate.atTime(BookingConstants.CHECK_OUT_TIME);

        return roomTypeRepository.findAllByAvailability(checkIn, checkOut, nrOfGuests)
                .stream()
                .map(roomType -> AvailableRoomsDTO.builder()
                        .roomType(roomTypeToDTO(roomType))
                          .numberOfAvailableRooms(
                                  roomTypeRepository.countAllByAvailability(roomType, checkIn, checkOut, null)
                          ).build()).toList();
    }
}

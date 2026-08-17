package com.lasias.hostelbookingbackend.dtos;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class BookingResponseDTO {

    private final String bookingNumber;
    private final RoomResponseDTO room;
    private final LocalDateTime checkInDate;
    private final LocalDateTime checkOutDate;
    private final boolean extraBed;
    private final String status;

    public BookingResponseDTO(
            String bookingNumber,
            RoomResponseDTO room,
            LocalDateTime checkInDate,
            LocalDateTime checkOutDate,
            boolean extraBed,
            String status
    ) {
        this.bookingNumber = bookingNumber;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.extraBed = extraBed;
        this.status = status;
    }
}

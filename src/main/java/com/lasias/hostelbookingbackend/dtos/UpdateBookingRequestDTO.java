package com.lasias.hostelbookingbackend.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class UpdateBookingRequestDTO {

    private Long roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private boolean extraBed;

}
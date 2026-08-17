package com.lasias.hostelbookingbackend.dtos;

import com.lasias.hostelbookingbackend.models.RoomType;
import lombok.Getter;

@Getter
public class RoomResponseDTO {

    private final Long id;
    private final Long roomNumber;
    private final boolean extraBed;
    private final RoomType roomType;

    public RoomResponseDTO(Long id, Long roomNumber, boolean extraBed, RoomType roomType) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.extraBed = extraBed;
        this.roomType = roomType;
    }
}
package com.lasias.hostelbookingbackend.exceptions;

public class NoAvailableRoomException extends RuntimeException {
    public NoAvailableRoomException(String message) {
        super(message);
    }
}

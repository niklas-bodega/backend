package com.lasias.hostelbookingbackend.services;

import com.lasias.hostelbookingbackend.dtos.CreateBookingRequestDTO;
import com.lasias.hostelbookingbackend.dtos.RegisterNewUserDTO;
import com.lasias.hostelbookingbackend.models.AppUser;
import com.lasias.hostelbookingbackend.repositories.AppUserRepository;
import com.lasias.hostelbookingbackend.repositories.BookingRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookingServiceTest {
    @Autowired
    private AppUserRepository appUserRepository;
    @Inject
    private AppUserService appUserService;
    @Autowired
    private BookingRepository bookingRepository;
    @Inject
    private BookingService bookingService;


}
package com.lasias.hostelbookingbackend.repositories;

import com.lasias.hostelbookingbackend.models.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;


public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

    @Query("""
            SELECT r FROM RoomEntity r
            WHERE r.roomType.id = :roomTypeId
            AND (:extraBed = false OR r.extraBed = true)
            AND NOT EXISTS (
                SELECT b FROM BookingEntity b
                WHERE b.room = r
                AND b.status <> com.lasias.hostelbookingbackend.enums.BookingStatus.CANCELLED
                AND b.checkInDate < :checkOut
                AND b.checkOutDate > :checkIn
            )
            ORDER BY r.roomNumber ASC
            """)
    List<RoomEntity> findAvailableByRoomTypeId(
            @Param("roomTypeId") Long roomTypeId,
            @Param("checkIn") LocalDateTime checkIn,
            @Param("checkOut") LocalDateTime checkOut,
            @Param("extraBed") boolean extraBed
    );

    @Query("""
            SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END
            FROM RoomEntity r
            WHERE r.id = :roomId
            AND (:extraBed = false OR r.extraBed = true)
            AND NOT EXISTS (
                SELECT b FROM BookingEntity b
                WHERE b.room = r
                AND b.bookingNumber <> :bookingNumber
                AND b.status <> com.lasias.hostelbookingbackend.enums.BookingStatus.CANCELLED
                AND b.checkInDate < :checkOut
                AND b.checkOutDate > :checkIn
            )
            """)
    boolean isRoomAvailableForBookingUpdate(
            @Param("roomId") Long roomId,
            @Param("bookingNumber") String bookingNumber,
            @Param("checkIn") LocalDateTime checkIn,
            @Param("checkOut") LocalDateTime checkOut,
            @Param("extraBed") boolean extraBed
    );
}

package com.example.hotel.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking,Long>{
    
    List<Booking> findByRoomId(Long RoomId);

    List<Booking> findByBookingConformationCode(String conformationCode);

    List<Booking> findByUserId(Long userId);
}

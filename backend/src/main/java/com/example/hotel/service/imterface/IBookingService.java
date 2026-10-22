package com.example.hotel.service.imterface;

import com.example.hotel.dto.Response;
import com.example.hotel.entity.Booking;

public interface IBookingService {
    Response saveBooking(Long roomId,Long userId,Booking bookingRequest);

    Response findBookingByConfirmationCode(String conformation);

    Response getAllBookings();

    Response cancalBooking(Long bookingId);
}

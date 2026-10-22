package com.example.hotel.utils;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

import com.example.hotel.dto.BookingDTO;
import com.example.hotel.dto.RoomDTO;
import com.example.hotel.dto.UserDTO;
import com.example.hotel.entity.Booking;
import com.example.hotel.entity.Room;
import com.example.hotel.entity.User;

public class Utils {

    private static final String ALPHANUMERIC_STRING =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

    private static final SecureRandom SecureRandom = new SecureRandom();

    public static String generateRandomConfirmationCode(int length) {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {

            int randomIndex =
                    SecureRandom.nextInt(ALPHANUMERIC_STRING.length());

            char randomChar =
                    ALPHANUMERIC_STRING.charAt(randomIndex);

            stringBuilder.append(randomChar);
        }

        return stringBuilder.toString();
    }

    public static UserDTO mapUserEntityUserDTO(User user) {

        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRole());

        return userDTO;
    }

    public static RoomDTO mapRoomEntityToRoomDTO(Room room) {

        RoomDTO roomDTO = new RoomDTO();

        roomDTO.setId(room.getId());
        roomDTO.setRoomType(room.getRoomType());
        roomDTO.setRoomPrice(room.getRoomPrice());
        roomDTO.setRoomPhotoUrl(room.getRoomPhotoUrl());
        roomDTO.setRoomDiscription(room.getRoomDiscription());

        return roomDTO;
    }

    public static BookingDTO mapBookingEntityToBookingDTO(Booking booking) {

        BookingDTO bookingDTO = new BookingDTO();

        bookingDTO.setId(booking.getId());
        bookingDTO.setCheckInDate(booking.getCheckInDate());
        bookingDTO.setCheckOutDate(booking.getCheckOutDate());
        bookingDTO.setNumOfAdults(booking.getNumOfAdults());
        bookingDTO.setNumOfChildren(booking.getNumOfChildren());

        // FIXED: Of is capital O
        bookingDTO.setTotalNumOfGuest(
                booking.getTotalNumofGuest()
        );

        bookingDTO.setBookingConformationCode(
                booking.getBookingConformationCode()
        );

        return bookingDTO;
    }

    public static RoomDTO mapRoomEntityToRoomDTOPlusBookings(Room room) {

        RoomDTO roomDTO = new RoomDTO();

        roomDTO.setId(room.getId());
        roomDTO.setRoomType(room.getRoomType());
        roomDTO.setRoomPrice(room.getRoomPrice());
        roomDTO.setRoomPhotoUrl(room.getRoomPhotoUrl());
        roomDTO.setRoomDiscription(room.getRoomDiscription());

        if (room.getBookings() != null) {

            roomDTO.setBookings(
                    room.getBookings()
                            .stream()
                            .map(Utils::mapBookingEntityToBookingDTO)
                            .collect(Collectors.toList())
            );
        }

        return roomDTO;
    }

    public static BookingDTO mapBookingEntityToBookingDTOPlusBookedRooms(
            Booking booking,
            boolean mapUser) {

        BookingDTO bookingDTO = new BookingDTO();

        bookingDTO.setId(booking.getId());
        bookingDTO.setCheckInDate(booking.getCheckInDate());
        bookingDTO.setCheckOutDate(booking.getCheckOutDate());
        bookingDTO.setNumOfAdults(booking.getNumOfAdults());
        bookingDTO.setNumOfChildren(booking.getNumOfChildren());

        // FIXED: Of is capital O
        bookingDTO.setTotalNumOfGuest(
                booking.getTotalNumofGuest()
        );

        bookingDTO.setBookingConformationCode(
                booking.getBookingConformationCode()
        );

        if (mapUser) {

            bookingDTO.setUser(
                    Utils.mapUserEntityUserDTO(
                            booking.getUser()
                    )
            );
        }

        if (booking.getRoom() != null) {

            RoomDTO roomDTO = new RoomDTO();

            roomDTO.setId(booking.getRoom().getId());
            roomDTO.setRoomType(booking.getRoom().getRoomType());
            roomDTO.setRoomPrice(booking.getRoom().getRoomPrice());
            roomDTO.setRoomPhotoUrl(booking.getRoom().getRoomPhotoUrl());
            roomDTO.setRoomDiscription(
                    booking.getRoom().getRoomDiscription()
            );

            bookingDTO.setRoom(roomDTO);
        }

        return bookingDTO;
    }

    public static UserDTO mapUserEntityToUserDTOPlusUserBookingsAndRoom(
            User user) {

        UserDTO userDTO = new UserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRole());

        if (!user.getBookings().isEmpty()) {

            userDTO.setBookings(
                    user.getBookings()
                            .stream()
                            .map(booking ->
                                    mapBookingEntityToBookingDTOPlusBookedRooms(
                                            booking,
                                            false
                                    )
                            )
                            .collect(Collectors.toList())
            );
        }

        return userDTO;
    }

    public static List<UserDTO> mapUserListEntityToUserListDTO(
            List<User> userList) {

        return userList
                .stream()
                .map(Utils::mapUserEntityUserDTO)
                .collect(Collectors.toList());
    }

    public static List<RoomDTO> mapRoomListEntityToRoomListDTO(
            List<Room> roomList) {

return roomList
        .stream()
        .map(room -> Utils.mapRoomEntityToRoomDTO(room))
        .collect(Collectors.toList());







    }

    public static List<BookingDTO> mapBookingListEntityToBookingListDTO(
            List<Booking> bookingList) {

        return bookingList
                .stream()
                .map(Utils::mapBookingEntityToBookingDTO)
                .collect(Collectors.toList());
    }
}
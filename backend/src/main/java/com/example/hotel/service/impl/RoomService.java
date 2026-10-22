package com.example.hotel.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.hotel.dto.Response;
import com.example.hotel.dto.RoomDTO;
import com.example.hotel.entity.Room;
import com.example.hotel.exception.OurException;
import com.example.hotel.repo.BookingRepository;
import com.example.hotel.repo.RoomRepository;
import com.example.hotel.service.CloudinaryService;
import com.example.hotel.service.imterface.IRoomService;
import com.example.hotel.utils.Utils;

@Service
public class RoomService implements IRoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository BookingRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    public Response addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice, String description) {
        Response response = new Response();

        try {
            String imageUrl = cloudinaryService.saveImage(photo);

            Room room = new Room();
            room.setRoomPhotoUrl(imageUrl);
            room.setRoomType(roomType);
            room.setRoomPrice(roomPrice);
            room.setRoomDiscription(description);

            Room saveRoom = roomRepository.save(room);
            RoomDTO roomDTO = Utils.mapRoomEntityToRoomDTO(saveRoom);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setRoom(roomDTO);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error to adding Room " + e.getMessage());
        }

        return response;
    }

    @Override
    public List<String> getAllRoomType() {
        return roomRepository.findDistinctRoomType();
    }

    @Override
    public Response getAllRooms() {
        Response response = new Response();

        try {
            List<Room> roomList = roomRepository.findAll(
                    Sort.by(Sort.Direction.DESC, "id")
            );

            List<RoomDTO> roomDTOList =
                    Utils.mapRoomListEntityToRoomListDTO(roomList);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setRoomList(roomDTOList);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room " + e.getMessage());
        }

        return response;
    }

    @Override
    public Response deleteRoom(Long roomId) {
        Response response = new Response();

        try {
            roomRepository.findById(roomId)
                    .orElseThrow(() -> new OurException("Room Not Found"));

            roomRepository.deleteById(roomId);

            response.setStatusCode(200);
            response.setMessage("successful");

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error deleting a room " + e.getMessage());
        }

        return response;
    }

    @Override
    public Response updateRoom(
            Long roomId,
            String description,
            String roomType,
            BigDecimal roomPrice,
            MultipartFile photo) {

        Response response = new Response();

        try {
            String imageUrl = null;

            if (photo != null && !photo.isEmpty()) {
                imageUrl = cloudinaryService.saveImage(photo);
            }

            Room room = roomRepository.findById(roomId)
                    .orElseThrow(() -> new OurException("Room Not Found"));

            if (roomType != null) {
                room.setRoomType(roomType);
            }

            if (roomPrice != null) {
                room.setRoomPrice(roomPrice);
            }

            if (description != null) {
                room.setRoomDiscription(description);
            }

            if (imageUrl != null) {
                room.setRoomPhotoUrl(imageUrl);
            }

            Room updatedRoom = roomRepository.save(room);

            RoomDTO roomDTO =
                    Utils.mapRoomEntityToRoomDTO(updatedRoom);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setRoom(roomDTO);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room " + e.getMessage());
        }

        return response;
    }

    @Override
    public Response getRoomById(Long RoomId) {
        Response response = new Response();

        try {
            Room room = roomRepository.findById(RoomId)
                    .orElseThrow(() -> new OurException("Room Not Found"));

            RoomDTO roomDTO =
                    Utils.mapRoomEntityToRoomDTOPlusBookings(room);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setRoom(roomDTO);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room " + e.getMessage());
        }

        return response;
    }

    @Override
    public Response getAvialableRoomsByDateAndType(
            LocalDate checkInDate,
            LocalDate checkOutDate,
            String roomType) {

        Response response = new Response();

        try {
            List<Room> availableRooms =
                    roomRepository.findAvailableRoomsByDatesAndType(
                            checkInDate,
                            checkOutDate,
                            roomType
                    );

            List<RoomDTO> roomDTOList =
                    Utils.mapRoomListEntityToRoomListDTO(availableRooms);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setRoomList(roomDTOList);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room " + e.getMessage());
        }

        return response;
    }

    @Override
    public Response getAllAvailableRooms() {
        Response response = new Response();

        try {
            List<Room> roomList = roomRepository.getAvailableRooms();

            List<RoomDTO> roomDTOList =
                    Utils.mapRoomListEntityToRoomListDTO(roomList);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setRoomList(roomDTOList);

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error to find room a room " + e.getMessage());
        }

        return response;
    }
}
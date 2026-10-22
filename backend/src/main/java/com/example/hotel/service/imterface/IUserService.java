package com.example.hotel.service.imterface;

import com.example.hotel.dto.LoginRequest;
import com.example.hotel.dto.Response;
import com.example.hotel.entity.User;

public interface IUserService {
    
    Response registre(User loginRequest);
    Response Login(LoginRequest loginRequest);
    Response getAllUser();
    Response getUserBookingHistory(String userId);
    Response deleteUser(String userId);
    Response getUserById(String userId);
    Response getMyInfo(String email);
}

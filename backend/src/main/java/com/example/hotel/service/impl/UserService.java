package com.example.hotel.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hotel.dto.LoginRequest;
import com.example.hotel.dto.Response;
import com.example.hotel.dto.UserDTO;
import com.example.hotel.entity.User;
import com.example.hotel.exception.OurException;
import com.example.hotel.repo.UserRepository;
import com.example.hotel.service.imterface.IUserService;
import com.example.hotel.utils.JWTUtils;
import com.example.hotel.utils.Utils;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public Response registre(User user) {
        Response response=new Response();
        try{
            if(user.getRole()==null || user.getRole().isBlank()){
                user.setRole("USER");
            }

            if(userRepository.existsByEmail(user.getEmail())){
                throw new OurException(user.getEmail()+"Already Exist");
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User saveUser=userRepository.save(user);
            UserDTO userDTO=Utils.mapUserEntityUserDTO(saveUser);
            response.setStatusCode(200);
            response.setUser(userDTO);

        }catch(OurException e){
            response.setStatusCode(500);
            response.setMessage("Error Ocured During User Registration "+e.getMessage());
        }
        return response;
    }

    @Override
    public Response Login(LoginRequest loginRequest) {

        Response response = new Response();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new OurException("user Not found"));

            var token = jwtUtils.genrateToken(user);
            response.setStatusCode(200);
            response.setToken(token);
            response.setRole(user.getRole());
            response.setExpirationTime("7 Days");
            response.setMessage("successful");

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage("Error Occurred During USer Login " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getAllUser() {

        Response response = new Response();
        try {
            List<User> userList = userRepository.findAll();
            List<UserDTO> userDTOList = Utils.mapUserListEntityToUserListDTO(userList);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUserList(userDTOList);

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getUserBookingHistory(String userId) {
        Response response = new Response();
        try{
            User user=userRepository.findById(Long.valueOf(userId)).orElseThrow(()->new OurException("user not found "));
            UserDTO userDTO=Utils.mapUserEntityToUserDTOPlusUserBookingsAndRoom(user);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userDTO);

        }
        catch(OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch(Exception e){
            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response deleteUser(String userId) {
        Response response = new Response();
        try{
            userRepository.findById(Long.valueOf(userId)).orElseThrow(()->new OurException("user not found "));
            userRepository.deleteById(Long.valueOf(userId));
            response.setStatusCode(200);
            response.setMessage("successful");

        }
        catch(OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch(Exception e){
            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getUserById(String userId) {
        Response response = new Response();
        try{
            User user= userRepository.findById(Long.valueOf(userId)).orElseThrow(()->new OurException("user not found "));
            UserDTO userDTO=Utils.mapUserEntityUserDTO(user);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userDTO);

        }
        catch(OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch(Exception e){
            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response getMyInfo(String email) {
        Response response = new Response();
        try{
            User user= userRepository.findByEmail(email).orElseThrow(()->new OurException("user not found "));
            UserDTO userDTO=Utils.mapUserEntityUserDTO(user);
            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userDTO);

        }
        catch(OurException e){
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch(Exception e){
            response.setStatusCode(500);
            response.setMessage("Error getting all users " + e.getMessage());
        }
        return response;
    }
    
}

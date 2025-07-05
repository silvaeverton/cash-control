package com.everton.cash_Control.services;

import com.everton.cash_Control.dtos.UserRequestDto;
import com.everton.cash_Control.dtos.UserResponseDto;
import com.everton.cash_Control.entities.User;

import java.util.List;


public interface UserService {

    public User createUser(UserRequestDto request);
    public UserResponseDto findUserById(Long idUser);
    public List<UserResponseDto> findAllUsers();
    public User updateUser(Long idUser,UserRequestDto request);
    public User getUserById(Long idUser);
    public void deactivateUser(Long idUser);
}

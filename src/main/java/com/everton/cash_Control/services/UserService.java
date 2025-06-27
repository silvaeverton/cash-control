package com.everton.cash_Control.services;

import com.everton.cash_Control.dtos.UserRequestDto;
import com.everton.cash_Control.dtos.UserResponseDto;
import com.everton.cash_Control.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public User createUser(UserRequestDto request);
    public UserResponseDto findUserById(Long idUser);
    public List<UserResponseDto> findAllUsers();
    public User updateUser(UserRequestDto request,Long idUser);
    public User getUserById(Long idUser);
    public void deleteUser(Long idUser);
}

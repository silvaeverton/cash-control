package com.everton.cash_Control.services.ServiceImpl;

import com.everton.cash_Control.dtos.UserRequestDto;
import com.everton.cash_Control.dtos.UserResponseDto;
import com.everton.cash_Control.entities.User;
import com.everton.cash_Control.exceptions.custom.NotFoundException;
import com.everton.cash_Control.repositories.UserRepository;
import com.everton.cash_Control.services.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public User createUser(UserRequestDto request) {
        return null;
    }

    @Override
    public UserResponseDto findUserById(Long idUser) {
        return null;
    }

    @Override
    public List<UserResponseDto> findAllUsers() {
        return List.of();
    }

    @Override
    public User updateUser(UserRequestDto request, Long idUser) {
        return null;
    }

    @Override
    public void deleteUser(Long idUser) {

    }

    @Override
    public User getUserById(Long idUser) {
        return userRepository.findById(idUser).orElseThrow(()->
                new NotFoundException("User not found",404));
    }
}

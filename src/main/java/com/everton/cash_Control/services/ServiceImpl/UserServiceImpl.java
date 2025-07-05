package com.everton.cash_Control.services.ServiceImpl;

import com.everton.cash_Control.dtos.UserRequestDto;
import com.everton.cash_Control.dtos.UserResponseDto;
import com.everton.cash_Control.entities.User;
import com.everton.cash_Control.exceptions.custom.NotFoundException;
import com.everton.cash_Control.repositories.UserRepository;
import com.everton.cash_Control.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public User createUser(UserRequestDto request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        return userRepository.saveAndFlush(user);
    }

    @Override
    public UserResponseDto findUserById(Long idUser) {
        User user = userRepository.findById(idUser).orElseThrow(()->
                new NotFoundException("User not Found", 404));

        UserResponseDto dto = new UserResponseDto();

        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setId(user.getId());

        return dto;
    }

    @Override
    public List<UserResponseDto> findAllUsers() {

        List<User> list = userRepository.findAll();

        if (list.isEmpty()) {
            throw new NotFoundException("User List is Empty", 404);
        }

       return list.stream().map( user -> {
            UserResponseDto dto = new UserResponseDto();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            return dto;
        }).toList();

    }

    @Override
    public User updateUser( Long idUser, UserRequestDto request) {

        User user = getUserById(idUser);

        if(request.getName() != null) {user.setName(request.getName());}
        if(request.getEmail() != null) {user.setEmail(request.getEmail());}

        return userRepository.saveAndFlush(user);
    }

    @Override
    public void deactivateUser(Long idUser) {

        User user = getUserById(idUser);
        user.setActive(false);
        userRepository.saveAndFlush(user);



    }

    @Override
    public User getUserById(Long idUser) {
        return userRepository.findById(idUser).orElseThrow(()->
                new NotFoundException("User not found",404));
    }
}

package com.everton.cash_Control.controllers;

import com.everton.cash_Control.dtos.UserRequestDto;
import com.everton.cash_Control.dtos.UserResponseDto;
import com.everton.cash_Control.entities.User;
import com.everton.cash_Control.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

   private final   UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody UserRequestDto request) {
        return userService.createUser(request);
    }

    @GetMapping("/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto findUserById(@PathVariable("idUser") Long idUser) {
        return userService.findUserById(idUser);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponseDto> findAllUsers() {
        return userService.findAllUsers();
    }

    @PutMapping("/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public User updateUser( @PathVariable Long idUser,@RequestBody UserRequestDto request) {
        return userService.updateUser(idUser,request);
    }

    public User getUserById(Long idUser) {
        return userService.getUserById(idUser);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void deactivateUser(Long idUser) {
        userService.deactivateUser(idUser);
    }
}

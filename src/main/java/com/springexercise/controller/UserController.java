package com.springexercise.controller;

import com.springexercise.common.response.Response;
import com.springexercise.dto.user.ChangePasswordUserDto;
import com.springexercise.dto.user.UpdateUserDto;
import com.springexercise.dto.user.UserDto;
import com.springexercise.entity.User;
import com.springexercise.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<Response> getAllUsers() {
        return userService.getAllUsers();
    }


    @PostMapping
    public ResponseEntity<Response> createUser(@Valid @RequestBody UserDto dto) {
        return userService.addUser(dto);
    }


    @PutMapping("{id}")
    public ResponseEntity<Response> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UpdateUserDto dto) {
        return userService.updateUser(id, dto);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }


    @PatchMapping("/change-password/{id}")
    public ResponseEntity<Response> changeUserPassword(@PathVariable("id") Long id, @Valid @RequestBody ChangePasswordUserDto dto) {
        return userService.changePassword(id, dto);
    }
}

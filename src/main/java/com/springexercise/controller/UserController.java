package com.springexercise.controller;

import com.springexercise.common.response.Response;
import com.springexercise.dto.user.ChangePasswordUserDto;
import com.springexercise.dto.user.UpdateUserDto;
import com.springexercise.dto.user.UserDto;
import com.springexercise.dto.user.UserResponseDto;
import com.springexercise.entity.User;
import com.springexercise.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<Response> getAllUsers() {
        List<UserResponseDto> dtos =  userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(Response.success("200" , "success" , "successfully get data" , dtos));
    }


    @PostMapping
    public ResponseEntity<Response> createUser(@Valid @RequestBody UserDto dto) {
        userService.addUser(dto);
        return ResponseEntity.status(HttpStatus.OK).body(Response.success("201" , "success" , "successfully added user" ));
    }


    @PutMapping("{id}")
    public ResponseEntity<Response> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserDto dto) {
        userService.updateUser(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(Response.success( "success" , "successfully update data"));
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(Response.success( "success" , "successfully update data"));
    }


    @PatchMapping("/change-password/{id}")
    public ResponseEntity<Response> changeUserPassword(@PathVariable Long id, @Valid @RequestBody ChangePasswordUserDto dto) {
        userService.changePassword(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(Response.success( "success" , "successfully changed password"));
    }


    @GetMapping("{id}")
    public ResponseEntity<Response> getUserById(@PathVariable Long id) {
        UserResponseDto dto = userService.getUserById(id);

        return ResponseEntity.status(HttpStatus.OK).body(Response.success("200", "success" , "successfully update data" , dto));
    }


    @GetMapping("/search")
    public ResponseEntity<Response> getUserByName(@RequestParam String name) {
        List<UserResponseDto> dtos = userService.searchUserByName(name);

        return ResponseEntity.status(HttpStatus.OK).body(Response.success("200", "success" , "successfully update data" , dtos));
    }
}

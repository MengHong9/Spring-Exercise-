package com.springexercise.service;


import com.springexercise.common.response.Response;
import com.springexercise.dto.user.UserDto;
import com.springexercise.dto.user.UserResponseDto;
import com.springexercise.entity.User;
import com.springexercise.exception.DuplicateException;
import com.springexercise.exception.ResourceNotFoundException;
import com.springexercise.mapper.UserMapper;
import com.springexercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;


    public ResponseEntity<Response> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> dtos = userMapper.toDtoList(users);

        return ResponseEntity.status(HttpStatus.OK).body(Response.success("200" , "success" , "successfully retrieved user" , dtos ));

    }

    public ResponseEntity<Response> addUser(UserDto dto) {
        if (userRepository.existsByName(dto.getName())) {
            throw new DuplicateException("User with this name already exists");
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateException("User with this email already exists");
        }

        User user = userMapper.toEntity(dto);
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(Response.success("201" , "success" , "successfully added user"));
    }


    public ResponseEntity<Response> updateUser(Long id,UserDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("user not found"));

        userMapper.updateUser(dto, user);
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body(Response.success("success" , "successfully updated user"));
    }

    public ResponseEntity<Response> deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("user not found"));

        userRepository.delete(user);
        return ResponseEntity.status(HttpStatus.OK).body(Response.success("success" , "successfully deleted user"));
    }
}

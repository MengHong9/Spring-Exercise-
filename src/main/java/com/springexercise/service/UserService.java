package com.springexercise.service;


import com.springexercise.common.response.Response;
import com.springexercise.dto.user.ChangePasswordUserDto;
import com.springexercise.dto.user.UpdateUserDto;
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
import java.util.Objects;

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


    public ResponseEntity<Response> updateUser(Long id, UpdateUserDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("user not found with id " + id ));

        userMapper.updateUser(user , dto);
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body(Response.success("success" , "successfully updated user"));
    }

    public ResponseEntity<Response> deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("user not found with id : "+id));

        userRepository.delete(user);
        return ResponseEntity.status(HttpStatus.OK).body(Response.success("success" , "successfully deleted user"));
    }

    public ResponseEntity<Response> changePassword(Long id, ChangePasswordUserDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("user not found with id : "+id));


        // old password is not correct
        if (!Objects.equals(dto.getOldPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(Response.error("fail","old password is incorrect"));
        }

        // confirm password incorrect and new password does not match
        if (!Objects.equals(dto.getNewPassword(), dto.getConfirmPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Response.error("fail","new password and confirm password does not match"));
        }

        userMapper.changePassword(user , dto.getNewPassword());
        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body(Response.success("success" , "successfully changed password"));
    }



    public ResponseEntity<Response> getUserById(Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("user not found with id : "+id));


        return ResponseEntity.status(HttpStatus.OK).body(Response.success("200","success" , "successfully retrieved user with id : " + id , existingUser));
    }


    public ResponseEntity<Response> searchUserByName(String name) {
        List<User> user = userRepository.findByNameContainingIgnoreCase(name);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("user not found with name : " + name);
        }

        List<UserResponseDto> dtos = userMapper.toDtoList(user);
        return ResponseEntity.status(HttpStatus.OK).body(Response.success("200","success" , "successfully retrieved! "  , dtos));
    }
}

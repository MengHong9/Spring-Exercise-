package com.springexercise.service;


import com.springexercise.common.response.Response;
import com.springexercise.dto.user.ChangePasswordUserDto;
import com.springexercise.dto.user.UpdateUserDto;
import com.springexercise.dto.user.UserDto;
import com.springexercise.dto.user.UserResponseDto;
import com.springexercise.entity.User;
import com.springexercise.exception.DuplicateException;
import com.springexercise.exception.ResourceNotFoundException;
import com.springexercise.exception.UnprocessableEntityException;
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


    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return userMapper.toDtoList(users);

    }

    public void addUser(UserDto dto) {
        if (userRepository.existsByName(dto.getName())) {
            throw new DuplicateException("User with this name already exists");
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateException("User with this email already exists");
        }

        User user = userMapper.toEntity(dto);
        userRepository.save(user);

    }


    public void updateUser(Long id, UpdateUserDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("user not found with id " + id ));

        userMapper.updateUser(user , dto);
        userRepository.save(user);

    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("user not found with id : "+id));

        userRepository.delete(user);

    }

    public void changePassword(Long id, ChangePasswordUserDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("user not found with id : "+id));


        // old password is not correct
        if (!Objects.equals(dto.getOldPassword(), user.getPassword())) {
            throw new UnprocessableEntityException("old password is incorrect, please enter the correct password");
        }

        // confirm password incorrect and new password does not match
        if (!Objects.equals(dto.getNewPassword(), dto.getConfirmPassword())) {
            throw new UnprocessableEntityException("new password and confirm password does not match");
        }

        userMapper.changePassword(user , dto.getNewPassword());
        userRepository.save(user);


    }



    public UserResponseDto getUserById(Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("user not found with id : "+id));


        return userMapper.toResponseDto(existingUser);
    }


    public List<UserResponseDto> searchUserByName(String name) {
        List<User> user = userRepository.findByNameContainingIgnoreCase(name);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("user not found with name : " + name);
        }

        return userMapper.toDtoList(user);

    }
}

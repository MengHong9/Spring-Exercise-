package com.springexercise.mapper;

import com.springexercise.dto.user.UserDto;
import com.springexercise.dto.user.UserResponseDto;
import com.springexercise.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        return user;
    }

    public UserResponseDto toResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setAge(user.getAge());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setRole(user.getRole());
        return userResponseDto;
    }

    public List<UserResponseDto> toDtoList(List<User> users) {
        if (users == null || users.isEmpty()) {
            return new ArrayList<>();
        }

        return users.stream().map(this::toResponseDto).collect(Collectors.toList());
    }


    public void updateUser(UserDto dto , User user) {
        if (user == null || dto == null) {
            return;
        }
        user.setName(dto.getName());
        user.setAge(dto.getAge());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
    }
}

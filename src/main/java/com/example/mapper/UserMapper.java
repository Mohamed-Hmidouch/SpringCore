package com.example.mapper;

import org.springframework.stereotype.Component;
import com.example.model.User;
import com.example.dto.CreateUserDto;
import com.example.dto.ResponseUserDto;

@Component
public class UserMapper {
    public User toEntity(CreateUserDto createUserDto){
        User user = new User();
        user.setUsername(createUserDto.getUsername());
        user.setEmail(createUserDto.getEmail());
        user.setPassword(createUserDto.getPassword());
        return user;
    }

    public ResponseUserDto toResponseUserDto(User user){
        ResponseUserDto responseUserDto = new ResponseUserDto();
        responseUserDto.setId(user.getId());
        responseUserDto.setUsername(user.getUsername());
        responseUserDto.setEmail(user.getEmail());
        return responseUserDto;
    }

    public void updateEntityFromDto(CreateUserDto createUserDto, User userToUpdate) {
        userToUpdate.setUsername(createUserDto.getUsername());
        userToUpdate.setEmail(createUserDto.getEmail());
        userToUpdate.setPassword(createUserDto.getPassword());
    }
}
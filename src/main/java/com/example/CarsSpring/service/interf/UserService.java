package com.example.CarsSpring.service.interf;


import com.example.CarsSpring.dto.UserDto;

import java.util.Set;

public interface UserService {

    UserDto save(UserDto userDto);

    UserDto update(Long id, UserDto userDto);

    Set<UserDto> findAll();

    UserDto findById(Long id);

    UserDto findByEmail(String email);

    UserDto findByPhoneNumber(String phoneNumber);

    void deleteById(Long id);
}

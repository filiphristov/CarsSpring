package com.example.CarsSpring.service;

import com.example.CarsSpring.dto.RoleDto;
import com.example.CarsSpring.dto.UserDto;
import com.example.CarsSpring.exception.BadRequestException;
import com.example.CarsSpring.exception.DuplicateException;
import com.example.CarsSpring.exception.NotFoundException;
import com.example.CarsSpring.model.Role;
import com.example.CarsSpring.model.User;
import com.example.CarsSpring.repository.UserRepository;
import com.example.CarsSpring.service.interf.RoleService;
import com.example.CarsSpring.service.interf.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void deleteById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("User not found with id of: " + id);
        }
    }

    @Override
    public UserDto save(UserDto userDto) {
        try {
            userDto.setId(null);
            RoleDto roleDto = roleService.findByName("ROLE_CLIENT");
            User user = modelMapper.map(userDto, User.class);
            user.setRoles(modelMapper.map(roleDto, Role.class));
            User savedUser = userRepository.save(user);
            return modelMapper.map(savedUser, UserDto.class);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateException("User with name " + userDto.getFirstName() + " " + userDto.getLastName() + " already exist.");
        }
    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        Optional<User> maybeUser = userRepository.findById(id);
        if (maybeUser.isPresent()) {
            User savedUser = userRepository.save(maybeUser.get());
            return modelMapper.map(savedUser, UserDto.class);
        }
        throw new BadRequestException("User with id: " + id + " does not exist.");
    }

    @Override
    public Set<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper
                        .map(user, UserDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public UserDto findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDto.class);
        }
        throw new NotFoundException("User not found with id: " + id);
    }

    @Override
    public UserDto findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDto.class);
        }
        throw new NotFoundException("User not found with Email: " + email);
    }

    @Override
    public UserDto findByPhoneNumber(String phoneNumber) {
        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDto.class);
        }
        throw new NotFoundException("User not found with Phone Number: " + phoneNumber);
    }
}

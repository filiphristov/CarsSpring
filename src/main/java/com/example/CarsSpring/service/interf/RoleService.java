package com.example.CarsSpring.service.interf;

import com.example.CarsSpring.dto.RoleDto;

import java.util.Set;

public interface RoleService {
    RoleDto save(RoleDto roleDto);

    RoleDto update(Long id , RoleDto roleDto);

    Set<RoleDto> findAll();

    RoleDto findById(Long id);

    RoleDto findByName(String name);

    void deleteById(Long id);
}

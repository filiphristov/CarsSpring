package com.example.CarsSpring.controller;

import com.example.CarsSpring.dto.RoleDto;
import com.example.CarsSpring.service.interf.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(value = "roles")
public class RoleController {
    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PutMapping(value = "/{id}")
    public RoleDto update(@PathVariable Long id, @RequestBody RoleDto roleDto) {
        return roleService.update(id, roleDto);
    }

    @PostMapping
    public RoleDto save(@RequestBody @Valid RoleDto roleDto) {
        return roleService.save(roleDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        roleService.deleteById(id);
    }

    @GetMapping
    public Set<RoleDto> findAll() {
        return roleService.findAll();
    }

    @GetMapping(value = "/{id}")
    public RoleDto findById(@PathVariable Long id) {

        return roleService.findById(id);
    }

    @GetMapping(value = "/name/{name}")
    public RoleDto findByName(@PathVariable String name) {
        return roleService.findByName(name);

    }
}

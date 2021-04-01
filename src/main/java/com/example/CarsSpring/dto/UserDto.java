package com.example.CarsSpring.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class UserDto {

    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 7, max = 15, message = "Password have to be between 7 and 15 symbols.")
    private String password;

    @NotEmpty
    @Size(min = 11, max = 11, message = "Phone number have to be 11 symbols.")
    private String phoneNumber;

    private Set<CarDto> cars;

    private RoleDto roles;
}

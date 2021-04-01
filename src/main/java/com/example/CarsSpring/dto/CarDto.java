package com.example.CarsSpring.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CarDto {


    private Long id;

    @NotEmpty
    private String brand;

    @NotEmpty
    private String model;

    @NotEmpty
    private String year;

    @NotEmpty
    @Size(min = 7, max = 8, message = "Wrong number.")
    private String carNumber;

    @NotEmpty
    private String kilometers;

    private Long users_id;

}

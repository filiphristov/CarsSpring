package com.example.CarsSpring.service.interf;

import com.example.CarsSpring.dto.CarDto;

import java.util.Set;

public interface CarService {
    CarDto save(CarDto carDto);

    CarDto update(Long id, CarDto carDto);

    CarDto findById(Long id);

    Set<CarDto> findByBrand(String brand);

    Set<CarDto> findByModel(String model);

    Set<CarDto> findByYear(String year);

    CarDto findByCarNumber(String carNumber);

    Set<CarDto> findAll();

    void deleteById(Long id);

}
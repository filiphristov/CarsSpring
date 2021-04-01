package com.example.CarsSpring.controller;

import com.example.CarsSpring.dto.CarDto;
import com.example.CarsSpring.service.interf.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(value = "cars")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PutMapping(value = "/{id}")
    public CarDto update(@PathVariable Long id, @RequestBody CarDto carDto) {
        return carService.update(id, carDto);
    }

    @PostMapping
    public CarDto save(@RequestBody @Valid CarDto carDto) {
        return carService.save(carDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(Long id) {
        carService.deleteById(id);
    }

    @GetMapping
    public Set<CarDto> findAll() {
        return carService.findAll();
    }

    @GetMapping(value = "/{id}")
    public CarDto findById(@PathVariable Long id) {
        return carService.findById(id);
    }

    @GetMapping(value = "/model/{model}")
    public Set<CarDto> findByModel(@PathVariable String model) {
        return carService.findByModel(model);
    }

    @GetMapping(value = "/brand/{brand}")
    public Set<CarDto> findByBrand(@PathVariable String brand) {
        return carService.findByBrand(brand);
    }

    @GetMapping(value = "/number/{carNumber}")
    public CarDto findByCarNumber(@PathVariable String carNumber) {
        return carService.findByCarNumber(carNumber);
    }

    @GetMapping(value = "/year/{year}")
    public Set<CarDto> findByYear(@PathVariable String year) {
        return carService.findByYear(year);
    }
}


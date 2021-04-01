package com.example.CarsSpring.service;

import com.example.CarsSpring.dto.CarDto;
import com.example.CarsSpring.exception.BadRequestException;
import com.example.CarsSpring.exception.DuplicateException;
import com.example.CarsSpring.exception.NotFoundException;
import com.example.CarsSpring.model.Car;
import com.example.CarsSpring.repository.CarRepository;
import com.example.CarsSpring.service.interf.CarService;
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
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void deleteById(Long id) {
        try {
            carRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("Car not found with id of : " + id);
        }
    }

    @Override
    public CarDto save(CarDto carDto) {
        try {
            carDto.setId(null);
            Car car = modelMapper.map(carDto, Car.class);
            Car savedCar = carRepository.save(car);
            return modelMapper.map(savedCar, CarDto.class);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateException("Car with number " + carDto.getCarNumber() + " already exist.");
        }
    }

    @Override
    public CarDto update(Long id, CarDto carDto) {
        Optional<Car> maybeCar = carRepository.findById(id);
        if (maybeCar.isPresent()) {
            modelMapper.map(carDto, maybeCar.get());
            Car savedCar = carRepository.save(maybeCar.get());
            return modelMapper.map(savedCar, CarDto.class);
        }
        throw new BadRequestException("Car with id: " + id + " does not exist.");
    }

    @Override
    public CarDto findById(Long id) {
        Optional<Car> maybeCar = carRepository.findById(id);
        if (maybeCar.isPresent()) {
            return modelMapper.map(maybeCar.get(), CarDto.class);
        }
        throw new NotFoundException("Car not found with id: " + id);
    }

    @Override
    public Set<CarDto> findByBrand(String brand) {
        if (carRepository.findByBrand(brand).isEmpty()) {
            throw new NotFoundException("Cars not found with brand: " + brand);
        }
        return carRepository.findByBrand(brand)
                .stream().map(car -> modelMapper
                        .map(car, CarDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<CarDto> findByModel(String model) {
        if (carRepository.findByModel(model).isEmpty()) {
            throw new NotFoundException("Cars not found with model: " + model);
        }
        return carRepository.findByModel(model)
                .stream().map(cars -> modelMapper
                        .map(cars, CarDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<CarDto> findByYear(String year) {
        if (carRepository.findByYear(year).isEmpty()) {
            throw new NotFoundException("Cars not found with year: " + year);
        }
        return carRepository
                .findByYear(year)
                .stream().map(car -> modelMapper
                        .map(car, CarDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public CarDto findByCarNumber(String carNumber) {
        Optional<Car> maybeCar = carRepository.findByCarNumber(carNumber);
        if (maybeCar.isPresent()) {
            return modelMapper.map(maybeCar.get(), CarDto.class);
        }
        throw new NotFoundException("Car not found by number: " + carNumber);
    }

    @Override
    public Set<CarDto> findAll() {
        if (carRepository.findAll().isEmpty()) {
            throw new NotFoundException("Cars not found.");
        }
        return carRepository.findAll()
                .stream().map(car -> modelMapper
                        .map(car, CarDto.class))
                .collect(Collectors.toSet());
    }
}

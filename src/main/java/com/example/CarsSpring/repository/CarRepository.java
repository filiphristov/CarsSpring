package com.example.CarsSpring.repository;

import com.example.CarsSpring.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findById(Long id);

    List<Car> findByBrand(String brand);

    List<Car> findByModel(String model);

    List<Car> findByYear(String year);

    Optional<Car> findByCarNumber(String carNumber);


}

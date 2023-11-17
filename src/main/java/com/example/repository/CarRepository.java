package com.example.repository;

import com.example.payload.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarRepository extends JpaRepository<Car, Long> {


    @Query("SELECT c FROM Car c WHERE c.id = :id")
    Car findCarById(@Param("id") int id);

}

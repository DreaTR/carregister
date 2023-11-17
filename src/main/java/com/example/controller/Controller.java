package com.example.controller;

import com.example.payload.Car;
import com.example.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class Controller {

    private final CarRepository carRepository;

    private final List<Car> cars = new ArrayList<>();

    @Autowired
    public Controller(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    //Endpoint för att lägga till en ny bil
    @PostMapping
    public ResponseEntity<Car>addCar(@RequestBody Car car){
        Car savedCar = carRepository.save(car);
        return new ResponseEntity<>(car, HttpStatus.CREATED);

    }

    // Endpoint för att hämta alla bilar
    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        List<Car>cars = carRepository.findAll();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    // Endpoint för att hämta en specifik bil med ID
    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        return optionalCar.map(car -> new ResponseEntity<>(car, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint för att uppdatera en befintlig bil
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCar(@PathVariable Long id, @RequestBody Car updatedCar) {
        if (carRepository.existsById(id)) {
            updatedCar.setId(id);
            Car savedCar = carRepository.save(updatedCar);
            return new ResponseEntity<>("Bil med Id " + id + " är uppdaterad", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Bil med Id " + id + " hittades inte", HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint för att ta bort en bil med ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            return new ResponseEntity<>("Bil med Id " + id + " är borttagen", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Bil med Id " + id + " hittades inte", HttpStatus.NOT_FOUND);
        }
    }



}

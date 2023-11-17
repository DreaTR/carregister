package com.example.payload;

import jakarta.persistence.*;

@Entity
@Table (name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String carBrand;

    private int carYear;

    private int mile;

    public Car(long id, String carBrand, int carYear, int mile) {
        this.id = id;
        this.carBrand = carBrand;
        this.carYear = carYear;
        this.mile = mile;
    }
    public Car() {
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public int getCarYear() {
        return carYear;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMile() {
        return mile;
    }

    public void setMile(int mile) {
        this.mile = mile;
    }
}

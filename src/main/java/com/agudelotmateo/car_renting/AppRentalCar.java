package com.agudelotmateo.car_renting;

public class AppRentalCar {
    private static AppRentalCar instance = null;

    private AppRentalCar() { }

    public static AppRentalCar getInstance() {
        if (instance == null)
            instance = new AppRentalCar();
        return instance;
    }

    public static void main(String[] args) {

    }
}

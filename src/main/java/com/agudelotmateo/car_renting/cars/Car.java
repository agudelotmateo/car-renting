package com.agudelotmateo.car_renting.cars;

public class Car {
    public static enum Transmission {
        Automatic, Manual
    }

    public static enum Insurance {
        Standard, Everything
    }

    public static enum Type {
        Standard, Family, Sport
    }

    private Type type;
    private Transmission transmission;
    private Insurance insurance;
    private String plate;
    private String brand;
    private String color;
    private double price;
    private int engine;
    private int doors;
    private int stock;

    public Car(Type type, Transmission transmission, Insurance insurance, String plate, String brand, String color,
            int engine, int doors, double price, int stock) {
        this.type = type;
        this.transmission = transmission;
        this.insurance = insurance;
        this.plate = plate;
        this.brand = brand;
        this.color = color;
        this.price = price;
        this.engine = engine;
        this.doors = doors;
        this.stock = stock;
    }

    public Type getType() {
        return this.type;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getColor() {
        return this.color;
    }

    public String getPlate() {
        return this.plate;
    }

    public double getPrice() {
        return this.price;
    }

    public Insurance getInsurance() {
        return this.insurance;
    }

    public int getStock() {
        return this.stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%d): $%.02f", brand, color, stock, price);
    }
}

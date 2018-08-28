package com.agudelotmateo.car_renting.transaction;

import java.util.Date;

public class RentalTransaction {
    private int id;
    private int carId;
    private int customerId;
    private Date date;
    private Date pickupDate;
    private Date returnDate;
    private double price;
    private double total;

    public RentalTransaction(int id, int carId, int customerId, Date date, Date pickupDate, Date returnDate,
            double price, double total) {
        this.id = id;
        this.carId = carId;
        this.customerId = customerId;
        this.date = date;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
        this.price = price;
        this.total = total;
    }

    public int getId() {
        return this.id;
    }

    public int getCarId() {
        return this.carId;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public Date getDate() {
        return this.date;
    }

    public Date getPickupDate() {
        return this.pickupDate;
    }

    public Date getReturnDate() {
        return this.returnDate;
    }

    public double getPrice() {
        return this.price;
    }

    public double getTotal() {
        return this.total;
    }
}

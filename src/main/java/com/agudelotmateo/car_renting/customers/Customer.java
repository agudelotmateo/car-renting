package com.agudelotmateo.car_renting.customers;

public class Customer {
    public static enum Type {
        Standard, VIP
    }

    private int id;
    private String name;
    private String genre;
    private Type type;

    public Customer(int id, String name, String genre, Type type) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.type = type;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getGenre() {
        return this.genre;
    }

    public Type getType() {
        return this.type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return String.format("%s (%s): %s", name, type == Type.VIP ? "VIP" : "Stnd.", genre);
    }
}

package com.agudelotmateo.car_renting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.agudelotmateo.car_renting.cars.Car;
import com.agudelotmateo.car_renting.customers.Customer;
import com.agudelotmateo.car_renting.transaction.RentalTransaction;

public class AppRentalCar {
    private static AppRentalCar instance = null;

    private TreeMap<Integer, RentalTransaction> transactions;
    private TreeMap<Integer, Customer> customers;
    private TreeMap<Integer, Car> cars;
    private Scanner scanner;

    private AppRentalCar() {
        transactions = new TreeMap<>();
        customers = this.importCustomers();
        cars = this.importCars();
        this.scanner = new Scanner(System.in);
    }

    public static AppRentalCar getInstance() {
        if (instance == null)
            instance = new AppRentalCar();
        return instance;
    }

    public static void main(String[] args) {
        AppRentalCar app = AppRentalCar.getInstance();
        app.welcomeUser();
        app.runRentals();
        app.farewellUser();
    }

    public void welcomeUser() {
        System.out.println("Welcome! Here you can rent a car ;)");
    }

    public TreeMap<Integer, Car> importCars() {
        // This is an example so I'll hard-code create 5
        TreeMap<Integer, Car> cars = new TreeMap<>();

        cars.put(1, new Car(Car.Type.Standard, Car.Transmission.Manual, Car.Insurance.Standard, "AAA000",
                "Renault Symbol", "Blue", 1600, 5, 5.0, 10));
        cars.put(2, new Car(Car.Type.Standard, Car.Transmission.Manual, Car.Insurance.Standard, "BBB111",
                "Renault Logan", "Gray", 1400, 5, 6.0, 8));
        cars.put(3, new Car(Car.Type.Standard, Car.Transmission.Manual, Car.Insurance.Standard, "CCC222",
                "Chevrolet Spark", "Gray", 1000, 5, 3.0, 5));
        cars.put(4, new Car(Car.Type.Standard, Car.Transmission.Manual, Car.Insurance.Standard, "DDD333", "BMW i3",
                "Gray", 650, 5, 6.0, 5));
        cars.put(5, new Car(Car.Type.Standard, Car.Transmission.Manual, Car.Insurance.Standard, "EEE333", "Mazda CX-8",
                "Black", 2900, 5, 8.0, 5));

        return cars;
    }

    public TreeMap<Integer, Customer> importCustomers() {
        // This is an example so I'll just create 1 user
        TreeMap<Integer, Customer> customers = new TreeMap<>();

        customers.put(1, new Customer(1, "Mateo Agudelo Toro", "Male", Customer.Type.VIP));

        return customers;
    }

    public void runRentals() {
        while (printMenu())
            ;
    }

    public void farewellUser() {
        System.out.println("Thank you! Please come back soon");
    }

    private boolean printMenu() {
        System.out.println("Enter the number of the option followed by enter to select:");

        ArrayList<String> options = new ArrayList<>();
        options.add("See the list of cars");
        options.add("See the list of customers");
        options.add("See the list of transactions");
        options.add("Perform a new transaction");
        options.add("Exit");

        for (int i = 0; i < options.size(); ++i)
            System.out.printf("%d. %s\n", i + 1, options.get(i));
        System.out.println();

        int option = scanner.nextInt();
        System.out.println();
        if (option == 1) {
            printCars();
            return true;
        }
        if (option == 2) {
            printCustomers();
            return true;
        }
        if (option == 3) {
            printTransactions();
            return true;
        }
        if (option == 4) {
            performTransaction();
            return true;
        }
        if (option == 5)
            return false;
        System.out.println("Invalid option");
        return true;
    }

    private void printCars() {
        printTreeMap(this.cars, "Cars");
    }

    private void printCustomers() {
        printTreeMap(this.customers, "Customers");
    }

    private <K, V> void printTreeMap(TreeMap<K, V> map, String name) {
        System.out.printf("%s:\n", name);
        if (map == null || map.size() == 0)
            System.out.println("    <empty>");
        else
            for (Entry<K, V> entry : map.entrySet())
                System.out.printf("    %d. %s\n", entry.getKey(), entry.getValue());
        System.out.println();
    }

    private void performTransaction() {
        // Ask the user for input
        System.out.println("Enter the id of the customer:");
        int customerId = this.scanner.nextInt();
        System.out.println("Enter the id of the car:");
        int carId = this.scanner.nextInt();

        // Auto compute the transaction's ID
        int transactionId = this.transactions.size() + 1;

        // Get and compute the dates accordingly
        // In this example the car can be picked up one hour after it's registered and
        // is rented for a week
        Date date = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int pickup = 1;
        calendar.add(Calendar.HOUR_OF_DAY, pickup);
        Date pickupDate = calendar.getTime();

        int length = 7;
        calendar.add(Calendar.DAY_OF_WEEK, length);
        Date returnDate = calendar.getTime();

        // Compuse the prices
        Car car = this.cars.get(carId);
        Double price = car.getPrice();
        Double total = price * length;

        // Update the stock
        car.setStock(car.getStock() - 1);

        // Record the transaction
        transactions.put(transactionId,
                new RentalTransaction(transactionId, carId, customerId, date, pickupDate, returnDate, price, total));
        System.out.println();
    }

    private void printTransactions() {
        System.out.println("Transactions:");
        if (this.transactions == null || this.transactions.size() == 0)
            System.out.println("    <empty>");
        else
            for (Entry<Integer, RentalTransaction> entry : this.transactions.entrySet()) {
                RentalTransaction transaction = entry.getValue();
                Customer customer = this.customers.get(transaction.getCustomerId());
                Car car = this.cars.get(transaction.getCarId());
                String transactionString = String.format("%d: %s (%s) -> %s (%s): %s - $%.02f", transaction.getId(),
                        customer.getName(), customer.getId(), car.getBrand(), car.getColor(), transaction.getDate(),
                        transaction.getTotal());
                System.out.printf("    %d. %s\n", entry.getKey(), transactionString);
            }
        System.out.println();
    }
}

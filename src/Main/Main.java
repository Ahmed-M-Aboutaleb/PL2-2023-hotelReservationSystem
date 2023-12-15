package Main;

import Views.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome To Hotel Reservation System!");
        System.out.println("(1) Employees menu");
        System.out.println("(2) Customers menu");
        System.out.println("(3) Rooms menu");
        System.out.println("(4) Services menu");
        System.out.println("(5) Bills menu");
        System.out.println("(6) Exit");
        Scanner userInput = new Scanner(System.in);
        boolean continueInput = true;
        do {
            try {
                int option = userInput.nextInt();
                switch (option) {
                    case 1 -> {
                        EmployeeManagement.employeesMenu();
                        continueInput = false;
                    }
                    case 2 -> {
                        CustomerManagement.customersMenu();
                        continueInput = false;
                    }
                    case 3 -> {
                        RoomManagement.roomMenu();
                        continueInput = false;
                    }
                    case 4 -> {
                        ServiceManagement.serviceMenu();
                        continueInput = false;
                    }
                    case 5 -> {
                        BillManagement.billMenu();
                        continueInput = false;
                    }
                    case 6 -> System.exit(0);
                    default -> throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
                userInput.nextLine(); // Clear the buffer
            }
        } while (continueInput);
    }
}
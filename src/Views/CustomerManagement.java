package Views;

import User.Customer;
import Main.Main;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class CustomerManagement {
    public static void customersMenu() {
        System.out.println("Customers Menu: ");
        System.out.println("(1) Add Customer");
        System.out.println("(2) View Customer");
        System.out.println("(3) Update Customer");
        System.out.println("(4) Delete Customer");
        System.out.println("(5) View all Customer");
        System.out.println("(6) View Customer bill");
        System.out.println("(7) Back");
        Scanner userInput = new Scanner(System.in);
        boolean continueInput = true;
        do {
            try {
                int option = userInput.nextInt();
                switch (option) {
                    case 1 -> {
                        continueInput = false;
                        CustomerManagement.addCustomer();

                    }
                    case 2 -> {
                        continueInput = false;
                        CustomerManagement.viewCustomer();
                    }
                    case 3 -> {
                        continueInput = false;
                        CustomerManagement.updateCustomer();
                    }
                    case 4 -> {
                        continueInput = false;
                        CustomerManagement.deleteCustomer();
                    }
                    case 5 -> {
                        continueInput = false;
                        CustomerManagement.allCustomers();
                    }
                    case 6 -> {
                        continueInput = false;
                        CustomerManagement.getBill();
                    }
                    case 7 -> {
                        Main.main(null);
                        continueInput = false;
                    }
                    default -> throw new Exception("Invalid option");
                }
            } catch (Exception e) {
                System.out.println((e.getMessage() != null) ? e.getMessage() : "Invalid input");
                userInput.nextLine(); // Clear the buffer
            }
        } while (continueInput);
    }

    private static void addCustomer() {
        try (Scanner userInput = new Scanner(System.in)) {
            boolean continueInput = true;

            do {
                try {
                    Customer myCustomer = new Customer();
                    System.out.print("Enter name: ");
                    myCustomer.setName(userInput.nextLine());
                    System.out.print("Enter age: ");
                    myCustomer.setAge(userInput.nextInt());
                    myCustomer.create();
                    System.out.println("Added customer with id: " + myCustomer.getID());
                    CustomerManagement.customersMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println((e.getMessage() != null) ? e.getMessage() : "Invalid input");
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }

    private static void viewCustomer() {
        try (Scanner userInput = new Scanner(System.in)) {
            boolean continueInput = true;

            do {
                try {
                    Customer myCustomer = new Customer();
                    System.out.print("Enter id: ");
                    String id = userInput.nextLine();
                    myCustomer.setID(id);
                    myCustomer = (Customer) myCustomer.read(id);
                    System.out.println(myCustomer);
                    CustomerManagement.customersMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println((e.getMessage() != null) ? e.getMessage() : "Invalid input");
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }

    private static void updateCustomer() {
        try (Scanner userInput = new Scanner(System.in)) {
            boolean continueInput = true;
            do {
                try {
                    Customer myCustomer = new Customer();
                    Customer myOldCustomer = new Customer();
                    System.out.print("Enter id: ");
                    String id = userInput.nextLine();
                    myCustomer.setID(id);
                    System.out.print("Enter name: ");
                    myCustomer.setName(userInput.nextLine());
                    System.out.print("Enter age: ");
                    myCustomer.setAge(userInput.nextInt());
                    userInput.nextLine(); // Clear the buffer
                    System.out.print("Enter Bill ID: ");
                    myCustomer.setbillID(userInput.nextLine());
                    myOldCustomer = (Customer) myOldCustomer.read(id);
                    myCustomer.update(myOldCustomer);
                    System.out.println("Updated customer with id: " + myCustomer.getID());
                    CustomerManagement.customersMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println((e.getMessage() != null) ? e.getMessage() : "Invalid input");
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }

    private static void deleteCustomer() {
        try (Scanner userInput = new Scanner(System.in)) {
            boolean continueInput = true;

            do {
                try {
                    Customer myCustomer = new Customer();
                    System.out.print("Enter id: ");
                    String id = userInput.nextLine();
                    myCustomer.setID(id);
                    myCustomer = (Customer) myCustomer.read(id);
                    myCustomer.delete(myCustomer);
                    System.out.println("Deleted employee with id: " + myCustomer.getID());
                    CustomerManagement.customersMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println((e.getMessage() != null) ? e.getMessage() : "Invalid input");
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }

    private static void allCustomers() {
        try {
            Customer myCustomer = new Customer();
            ArrayList<Customer> allCustomers = myCustomer.getAll();
            for (Customer customer : allCustomers) {
                System.out.println(customer.toString());
            }
            CustomerManagement.customersMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private static void getBill() {
        try (Scanner userInput = new Scanner(System.in)) {
            boolean continueInput = true;

            do {
                try {
                    Customer myCustomer = new Customer();
                    System.out.print("Enter id: ");
                    String id = userInput.nextLine();
                    myCustomer.setID(id);
                    myCustomer = (Customer) myCustomer.read(id);
                    System.out.println(myCustomer.getInvoice());
                    CustomerManagement.customersMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println((e.getMessage() != null) ? e.getMessage() : "Invalid input");
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }
}

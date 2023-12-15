package Views;

import Other.Service;
import Main.Main;

import java.util.ArrayList;
import java.util.Scanner;

public class ServiceManagement {
    public static void serviceMenu() {
        System.out.println("Service Menu: ");
        System.out.println("(1) Add Service");
        System.out.println("(2) View Service");
        System.out.println("(3) Update Service");
        System.out.println("(4) Delete Service");
        System.out.println("(5) View All Services");
        System.out.println("(6) Generate Service report");
        System.out.println("(7) Back");
        Scanner userInput = new Scanner(System.in);
        boolean continueInput = true;
        do {
            try {
                int option = userInput.nextInt();
                switch (option) {
                    case 1 -> {
                        continueInput = false;
                        ServiceManagement.addService();

                    }
                    case 2 -> {
                        continueInput = false;
                        ServiceManagement.viewService();
                    }
                    case 3 -> {
                        continueInput = false;
                        ServiceManagement.updateService();
                    }
                    case 4 -> {
                        continueInput = false;
                        ServiceManagement.deleteService();
                    }
                    case 5 -> {
                        ServiceManagement.allServices();
                        continueInput = false;
                    }
                    case 6 -> {
                        ServiceManagement.report();
                        continueInput = false;
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

    private static void addService() {
        try (Scanner userInput = new Scanner(System.in)) {
            Service myService = new Service();
            boolean continueInput = true;

            do {
                try {
                    System.out.print("Enter name: ");
                    myService.setName(userInput.nextLine());
                    System.out.print("Enter description: ");
                    myService.setDescription(userInput.nextLine());
                    System.out.print("Enter price: ");
                    myService.setPrice(userInput.nextInt());
                    myService.setUsage(0);
                    if (!myService.create()) {
                        throw new Exception("Error!");
                    }
                    System.out.println("Added service with id: " + myService.getID());
                    ServiceManagement.serviceMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println((e.getMessage() != null) ? e.getMessage() : "Invalid input");
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }

    private static void viewService() {
        try (Scanner userInput = new Scanner(System.in)) {
            Service myService = new Service();
            boolean continueInput = true;

            do {
                try {
                    System.out.print("Enter id: ");
                    String id = userInput.nextLine();
                    myService.setID(id);
                    myService = (Service) myService.read(id);
                    System.out.println(myService);
                    ServiceManagement.serviceMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println((e.getMessage() != null) ? e.getMessage() : "Invalid input");
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }

    private static void updateService() {
        try (Scanner userInput = new Scanner(System.in)) {
            Service myService = new Service();
            Service myOldService = new Service();
            boolean continueInput = true;
            do {
                try {
                    System.out.print("Enter id: ");
                    String id = userInput.nextLine();
                    myService.setID(id);
                    System.out.print("Enter name: ");
                    myService.setName(userInput.nextLine());
                    System.out.print("Enter description: ");
                    myService.setDescription(userInput.nextLine());
                    System.out.print("Enter price: ");
                    myService.setPrice(userInput.nextInt());
                    myOldService = (Service) myOldService.read(id);
                    myService.setUsage(myOldService.getUsage());
                    if (!myService.update(myOldService)) {
                        throw new Exception("Error!");
                    }
                    System.out.println("Updated employee with id: " + myService.getID());
                    ServiceManagement.serviceMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println((e.getMessage() != null) ? e.getMessage() : "Invalid input");
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }

    private static void deleteService() {
        try (Scanner userInput = new Scanner(System.in)) {
            Service myService = new Service();
            boolean continueInput = true;

            do {
                try {
                    System.out.print("Enter id: ");
                    String id = userInput.nextLine();
                    myService.setID(id);
                    myService = (Service) myService.read(id);
                    if (!myService.delete(myService)) {
                        throw new Exception("Error!");
                    }
                    System.out.println("Deleted Service with id: " + myService.getID());
                    ServiceManagement.serviceMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println((e.getMessage() != null) ? e.getMessage() : "Invalid input");
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }

    private static void allServices() {
        try {
            Service myService = new Service();
            ArrayList<Service> allServices = myService.getAll();
            for (Service service : allServices) {
                System.out.println(service.toString());
            }
            ServiceManagement.serviceMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private static void report() {

        try (Scanner userInput = new Scanner(System.in)) {
            Service myService = new Service();
            boolean continueInput = true;

            do {
                try {
                    System.out.print("Enter id: ");
                    String id = userInput.nextLine();
                    myService.setID(id);
                    myService = (Service) myService.read(id);
                    ArrayList<String> data = myService.generateUsageReport();
                    System.out.println("Id: "+data.get(0));
                    System.out.println("Name: "+data.get(1));
                    System.out.println("Usage: "+data.get(2));
                    System.out.println("Usage percentage: "+data.get(3)+"%");
                    ServiceManagement.serviceMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println((e.getMessage() != null) ? e.getMessage() : "Invalid input");
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }
}
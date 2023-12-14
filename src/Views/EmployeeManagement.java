package Views;

import User.Employee;
import Main.Main;

import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeManagement {
    public static void employeesMenu() {
        System.out.println("Employees Menu: ");
        System.out.println("(1) Add employee");
        System.out.println("(2) View employee");
        System.out.println("(3) Update employee");
        System.out.println("(4) Delete employee");
        System.out.println("(5) View all employees");
        System.out.println("(6) Back");
        Scanner userInput = new Scanner(System.in);
        boolean continueInput = true;
        do {
            try {
                int option = userInput.nextInt();
                switch (option) {
                    case 1 -> {
                        continueInput = false;
                        EmployeeManagement.addEmployee();

                    }
                    case 2 -> {
                        continueInput = false;
                        EmployeeManagement.viewEmployee();
                    }
                    case 3 -> {
                        continueInput = false;
                        EmployeeManagement.updateEmployee();
                    }
                    case 4 -> {
                        continueInput = false;
                        EmployeeManagement.deleteEmployee();
                    }
                    case 5 -> {
                        continueInput = false;
                        EmployeeManagement.allEmployees();
                    }
                    case 6 -> {
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

    private static void addEmployee() {
        try (Scanner userInput = new Scanner(System.in)) {
            boolean continueInput = true;

            do {
                try {
                    Employee myEmployee = new Employee();
                    System.out.print("Enter name: ");
                    myEmployee.setName(userInput.nextLine());
                    System.out.print("Enter age: ");
                    myEmployee.setAge(userInput.nextInt());
                    System.out.print("Enter salary: ");
                    myEmployee.setSalary(userInput.nextInt());
                    myEmployee.create();
                    System.out.println("Added employee with id: " + myEmployee.getID());
                    EmployeeManagement.employeesMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println((e.getMessage() != null) ? e.getMessage() : "Invalid input");
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }

    private static void viewEmployee() {
        try (Scanner userInput = new Scanner(System.in)) {
            boolean continueInput = true;

            do {
                try {
                    Employee myEmployee = new Employee();
                    System.out.print("Enter id: ");
                    String id = userInput.nextLine();
                    myEmployee.setID(id);
                    myEmployee = (Employee) myEmployee.read(id);
                    System.out.println(myEmployee);
                    EmployeeManagement.employeesMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println((e.getMessage() != null) ? e.getMessage() : "Invalid input");
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }

    private static void updateEmployee() {
        try (Scanner userInput = new Scanner(System.in)) {
            boolean continueInput = true;
            do {
                try {
                    Employee myEmployee = new Employee();
                    Employee myOldEmployee = new Employee();
                    System.out.print("Enter id: ");
                    String id = userInput.nextLine();
                    myEmployee.setID(id);
                    System.out.print("Enter name: ");
                    myEmployee.setName(userInput.nextLine());
                    System.out.print("Enter age: ");
                    myEmployee.setAge(userInput.nextInt());
                    System.out.print("Enter salary: ");
                    myEmployee.setSalary(userInput.nextInt());
                    myOldEmployee = (Employee) myOldEmployee.read(id);
                    myEmployee.update(myOldEmployee);
                    System.out.println("Updated employee with id: " + myEmployee.getID());
                    EmployeeManagement.employeesMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println((e.getMessage() != null) ? e.getMessage() : "Invalid input");
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }

    private static void deleteEmployee() {
        try (Scanner userInput = new Scanner(System.in)) {
            boolean continueInput = true;

            do {
                try {
                    Employee myEmployee = new Employee();
                    System.out.print("Enter id: ");
                    String id = userInput.nextLine();
                    myEmployee.setID(id);
                    myEmployee = (Employee) myEmployee.read(id);
                    myEmployee.delete(myEmployee);
                    System.out.println("Deleted employee with id: " + myEmployee.getID());
                    EmployeeManagement.employeesMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println((e.getMessage() != null) ? e.getMessage() : "Invalid input");
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }

    private static void allEmployees() {
        try {
            Employee myEmployee = new Employee();
            ArrayList<Employee> allEmployees = myEmployee.getAll();
            for (Employee employee : allEmployees) {
                System.out.println(employee.toString());
            }
            EmployeeManagement.employeesMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

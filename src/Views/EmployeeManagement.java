package Views;

import User.Employee;
import com.sun.tools.javac.Main;
import java.util.Scanner;

public class EmployeeManagement {
    public static void employeesMenu() {
        System.out.println("Employees Menu: ");
        System.out.println("(1) Add employee");
        System.out.println("(2) View employee");
        System.out.println("(3) Update employee");
        System.out.println("(4) Delete employee");
        System.out.println("(5) Back");
        Scanner userInput = new Scanner(System.in);
        boolean continueInput = true;
        do {
            try{
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
                        Main.main(null);
                        continueInput = false;
                    }
                    default -> throw new Exception("Invalid option");
                }
            } catch(Exception e) {
                System.out.println(e.getMessage());
                userInput.nextLine(); // Clear the buffer
            }
        } while(continueInput);
    }

    private static void addEmployee() {
        try (Scanner userInput = new Scanner(System.in)) {
            Employee myEmployee = new Employee();
            boolean continueInput = true;

            do {
                try {
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
                    System.out.println(e.getMessage());
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }

    private static void viewEmployee() {
        try (Scanner userInput = new Scanner(System.in)) {
            Employee myEmployee = new Employee();
            boolean continueInput = true;

            do {
                try {
                    System.out.print("Enter id: ");
                    String id = userInput.nextLine();
                    myEmployee.setID(id);
                    myEmployee = (Employee)myEmployee.read(id);
                    if(myEmployee.getName() == null) {
                        System.out.println("There is no employee with that id!");
                        continue;
                    }
                    System.out.println("ID: " + myEmployee.getID());
                    System.out.println("Name: " + myEmployee.getName());
                    System.out.println("Age: " + myEmployee.getAge());
                    System.out.println("Salary: " + myEmployee.getSalary());
                    EmployeeManagement.employeesMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }

    private static void updateEmployee() {
        try (Scanner userInput = new Scanner(System.in)) {
            Employee myEmployee = new Employee();
            Employee myOldEmployee = new Employee();
            boolean continueInput = true;
            do {
                try {
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
                    System.out.println(e.getMessage());
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }

    private static void deleteEmployee() {
        try (Scanner userInput = new Scanner(System.in)) {
            Employee myEmployee = new Employee();
            boolean continueInput = true;

            do {
                try {
                    System.out.print("Enter id: ");
                    String id = userInput.nextLine();
                    myEmployee.setID(id);
                    myEmployee = (Employee)myEmployee.read(id);
                    myEmployee.delete(myEmployee);
                    System.out.println("Deleted employee with id: " + myEmployee.getID());
                    EmployeeManagement.employeesMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }
}

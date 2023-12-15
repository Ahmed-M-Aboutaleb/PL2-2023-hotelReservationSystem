package Views;

import Room.Bill;
import Room.RoomStatus;
import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.Scanner;

public class BillManagement {
    public static void billMenu() {
        System.out.println("Room Menu: ");
        System.out.println("(1) Add Bill");
        System.out.println("(2) View Bill");
        System.out.println("(3) Update Bill");
        System.out.println("(4) View All Bills");
        System.out.println("(5) Back");
        Scanner userInput = new Scanner(System.in);
        boolean continueInput = true;
        do {
            try {
                int option = userInput.nextInt();
                switch (option) {
                    case 1 -> {
                        continueInput = false;
                        BillManagement.addBill();

                    }
                    case 2 -> {
                        continueInput = false;
                        BillManagement.viewBill();
                    }
                    case 3 -> {
                        continueInput = false;
                        BillManagement.updateBill();
                    }
                    case 4 -> {
                        BillManagement.allBills();
                        continueInput = false;
                    }
                    case 5 -> {
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

    private static void addBill() {
        try (Scanner userInput = new Scanner(System.in)) {
            boolean continueInput = true;

            do {
                try {
                    Bill myObject = new Bill();
                    System.out.print("Enter total bill price: ");
                    myObject.setTotal(userInput.nextInt());
                    userInput.nextLine();
                    System.out.print("Enter Customer id: ");
                    myObject.setCustomerID(userInput.nextLine());
                    System.out.print("Enter Room id: ");
                    myObject.setRoomID(userInput.nextLine());
                    System.out.print("Enter checkout Date (yyyy-mm-dd): ");
                    myObject.setCheckoutDate(userInput.nextLine());
                    System.out.print("Enter checkin Date (yyyy-mm-dd): ");
                    myObject.setCheckinDate(userInput.nextLine());


                    if (!myObject.create()) {
                        throw new Exception("Error!");
                    }
                    System.out.println("Added with id: " + myObject.getID());
                    BillManagement.billMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println((e.getMessage() != null) ? e.getMessage() : "Invalid input");
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }

    private static void viewBill() {
        try (Scanner userInput = new Scanner(System.in)) {
            Bill myObject = new Bill();
            boolean continueInput = true;

            do {
                try {
                    System.out.print("Enter id: ");
                    String id = userInput.nextLine();
                    myObject = (Bill) myObject.read(id);
                    System.out.println(myObject);
                    BillManagement.billMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println((e.getMessage() != null) ? e.getMessage() : "Invalid input");
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }

    private static void updateBill() {
        try (Scanner userInput = new Scanner(System.in)) {
            Bill myObject = new Bill();
            Bill myOldObject = new Bill();
            boolean continueInput = true;
            do {
                try {
                    System.out.print("Enter id: ");
                    String id = userInput.nextLine();
                    myObject.setID(id);
                    System.out.print("Enter total bill price: ");
                    myObject.setTotal(userInput.nextInt());
                    userInput.nextLine();
                    System.out.print("Enter Customer id: ");
                    myObject.setCustomerID(userInput.nextLine());
                    System.out.print("Enter Room id: ");
                    myObject.setRoomID(userInput.nextLine());
                    System.out.print("Enter checkout Date (yyyy-mm-dd): ");
                    myObject.setCheckoutDate(userInput.nextLine());
                    System.out.print("Enter checkin Date (yyyy-mm-dd): ");
                    myObject.setCheckinDate(userInput.nextLine());
                    myOldObject = (Bill) myOldObject.read(id);
                    if (!myObject.update(myOldObject)) {
                        throw new Exception("Error!");
                    }
                    System.out.println("Updated with id: " + myObject.getID());
                    RoomStatusManagement.roomMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println((e.getMessage() != null) ? e.getMessage() : "Invalid input");
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }

    private static void allBills() {
        try {
            Bill myObject = new Bill();
            ArrayList<Bill> allObjects = myObject.getAll();
            for (Bill object : allObjects) {
                System.out.println(object.toString());
            }
            RoomStatusManagement.roomMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
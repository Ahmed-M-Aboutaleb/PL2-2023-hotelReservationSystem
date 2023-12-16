package Views;

import Main.Main;
import Room.Bill;
import Room.Room;
import Room.RoomStatus;
import User.Customer;

import java.util.ArrayList;
import java.util.Scanner;

public class RoomStatusManagement {
    public static void roomMenu() {
        System.out.println("Room Status Menu: ");
        System.out.println("(1) Add Room Status");
        System.out.println("(2) View Room Status");
        System.out.println("(3) Update Room Status");
        System.out.println("(4) Delete Room Status");
        System.out.println("(5) View All Room Status");
        System.out.println("(6) View near checkout clients");
        System.out.println("(7) Assign room to guest");
        System.out.println("(8) Back");
        Scanner userInput = new Scanner(System.in);
        boolean continueInput = true;
        do {
            try {
                int option = userInput.nextInt();
                switch (option) {
                    case 1 -> {
                        continueInput = false;
                        RoomStatusManagement.addRoom();

                    }
                    case 2 -> {
                        continueInput = false;
                        RoomStatusManagement.viewRoom();
                    }
                    case 3 -> {
                        continueInput = false;
                        RoomStatusManagement.updateRoom();
                    }
                    case 4 -> {
                        continueInput = false;
                        RoomStatusManagement.deleteRoom();
                    }
                    case 5 -> {
                        RoomStatusManagement.allRooms();
                        continueInput = false;
                    }
                    case 6 -> {
                        RoomStatusManagement.nearCheckout();
                        continueInput = false;
                    }
                    case 7 -> {
                        RoomStatusManagement.assignRoom();
                        continueInput = false;
                    }
                    case 8 -> {
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

    private static void addRoom() {
        try (Scanner userInput = new Scanner(System.in)) {
            boolean continueInput = true;

            do {
                try {
                    RoomStatus myObject = new RoomStatus();
                    Customer customer = new Customer(), oldCustomer = new Customer();
                    System.out.print("Enter Customer id: ");
                    myObject.setCustomerID(userInput.nextLine());
                    customer = (Customer) customer.read(myObject.getCustomerID());
                    oldCustomer = (Customer) oldCustomer.read(myObject.getCustomerID());
                    System.out.print("Enter Room id: ");
                    myObject.setRoomID(userInput.nextLine());
                    System.out.print("Enter checkout Date (yyyy-mm-dd): ");
                    myObject.setCheckoutDate(userInput.nextLine());
                    System.out.print("Enter checkin Date (yyyy-mm-dd): ");
                    myObject.setCheckinDate(userInput.nextLine());
                    Room myRoom = new Room();
                    myRoom = (Room) myRoom.read(myObject.getRoomID());

                    Bill bill = new Bill(myRoom.getRoomPrice(), customer.getID(), myObject.getRoomID(), myObject.getCheckoutDate(), myObject.getCheckinDate());
                    bill.create();
                    customer.setbillID(bill.getID());
                    customer.update(oldCustomer);

                    if (!myObject.create()) {
                        throw new Exception("Error!");
                    }
                    System.out.println("Added with id: " + myObject.getID());
                    RoomStatusManagement.roomMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println((e.getMessage() != null) ? e.getMessage() : "Invalid input");
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }

    private static void viewRoom() {
        try (Scanner userInput = new Scanner(System.in)) {
            RoomStatus myObject = new RoomStatus();
            boolean continueInput = true;

            do {
                try {
                    System.out.print("Enter id: ");
                    String id = userInput.nextLine();
                    myObject = (RoomStatus) myObject.read(id);
                    System.out.println(myObject);
                    RoomStatusManagement.roomMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println((e.getMessage() != null) ? e.getMessage() : "Invalid input");
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }

    private static void updateRoom() {
        try (Scanner userInput = new Scanner(System.in)) {
            RoomStatus myObject = new RoomStatus();
            RoomStatus myOldObject = new RoomStatus();
            boolean continueInput = true;
            do {
                try {
                    System.out.print("Enter id: ");
                    String id = userInput.nextLine();
                    myObject.setID(id);
                    System.out.print("Enter Customer id: ");
                    myObject.setCustomerID(userInput.nextLine());
                    System.out.print("Enter Room id: ");
                    myObject.setRoomID(userInput.nextLine());
                    System.out.print("Room statuses:- \n");
                    for (String status: RoomStatus.statuses) {
                        System.out.println(status);
                    }
                    System.out.print("Enter Status: ");
                    myObject.setStatus(userInput.nextLine());
                    System.out.print("Enter checkout Date (yyyy-mm-dd): ");
                    myObject.setCheckoutDate(userInput.nextLine());
                    System.out.print("Enter checkin Date (yyyy-mm-dd): ");
                    myObject.setCheckinDate(userInput.nextLine());
                    myOldObject = (RoomStatus) myOldObject.read(id);
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

    private static void deleteRoom() {
        try (Scanner userInput = new Scanner(System.in)) {
            RoomStatus myObject = new RoomStatus();
            boolean continueInput = true;

            do {
                try {
                    System.out.print("Enter id: ");
                    String id = userInput.nextLine();
                    myObject.setID(id);
                    myObject = (RoomStatus) myObject.read(id);
                    if (!myObject.delete(myObject)) {
                        throw new Exception("Error!");
                    }
                    System.out.println("Deleted with id: " + myObject.getID());
                    RoomStatusManagement.roomMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println((e.getMessage() != null) ? e.getMessage() : "Invalid input");
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }

    private static void allRooms() {
        try {
            RoomStatus myObject = new RoomStatus();
            ArrayList<RoomStatus> allObjects = myObject.getAll();
            for (RoomStatus object : allObjects) {
                System.out.println(object.toString());
            }
            RoomStatusManagement.roomMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private static void nearCheckout() {
        try {
            RoomStatus myObject = new RoomStatus();
            ArrayList<RoomStatus> allObjects = myObject.nearCheckOut();
            for (RoomStatus object : allObjects) {
                System.out.println(object.toString());
            }
            RoomStatusManagement.roomMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private static void assignRoom() {
        try (Scanner userInput = new Scanner(System.in)) {
            RoomStatus myObject = new RoomStatus();
            boolean continueInput = true;

            do {
                try {
                    System.out.print("Enter id: ");
                    String id = userInput.nextLine();
                    myObject.setID(id);
                    myObject = (RoomStatus) myObject.read(id);
                    if (!myObject.assignRoomToGuest()) {
                        throw new Exception("Error!");
                    }
                    System.out.println("assigned successfully!");
                    RoomStatusManagement.roomMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println((e.getMessage() != null) ? e.getMessage() : "Invalid input");
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }
}
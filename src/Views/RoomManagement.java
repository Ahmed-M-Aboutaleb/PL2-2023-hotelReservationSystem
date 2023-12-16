package Views;

import Other.Service;
import Room.*;
import Main.Main;
import User.Customer;

import java.util.ArrayList;
import java.util.Scanner;

public class RoomManagement {
    public static void roomMenu() {
        System.out.println("Room Menu: ");
        System.out.println("(1) Add Room");
        System.out.println("(2) View Room");
        System.out.println("(3) Update Room");
        System.out.println("(4) Delete Room");
        System.out.println("(5) View All Room");
        System.out.println("(6) Filter Rooms");
        System.out.println("(7) Request Service");
        System.out.println("(8) Rooms Status Menu");
        System.out.println("(9) Back");
        Scanner userInput = new Scanner(System.in);
        boolean continueInput = true;
        do {
            try {
                int option = userInput.nextInt();
                switch (option) {
                    case 1 -> {
                        continueInput = false;
                        RoomManagement.addRoom();

                    }
                    case 2 -> {
                        continueInput = false;
                        RoomManagement.viewRoom();
                    }
                    case 3 -> {
                        continueInput = false;
                        RoomManagement.updateRoom();
                    }
                    case 4 -> {
                        continueInput = false;
                        RoomManagement.deleteRoom();
                    }
                    case 5 -> {
                        RoomManagement.allRooms();
                        continueInput = false;
                    }
                    case 6 -> {
                        RoomManagement.filterRooms();
                        continueInput = false;
                    }
                    case 7-> {
                        RoomManagement.requestService();
                        continueInput = false;
                    }
                    case 8 -> {
                        RoomStatusManagement.roomMenu();
                        continueInput = false;
                    }
                    case 9 -> {
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
            Room myObject = new Room();
            boolean continueInput = true;

            do {
                try {
                    System.out.print("Room types:-\n");
                    for (String type: Room.types) {
                        System.out.println(type);
                    }
                    System.out.print("Enter type: ");
                    myObject.setType(userInput.nextLine());
                    System.out.print("Enter price: ");
                    myObject.setRoomPrice(userInput.nextInt());

                    if (!myObject.create()) {
                        throw new Exception("Error!");
                    }
                    System.out.println("Added with id: " + myObject.getID());
                    RoomManagement.roomMenu();
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
            Room myObject = new Room();
            boolean continueInput = true;

            do {
                try {
                    System.out.print("Enter id: ");
                    String id = userInput.nextLine();
                    myObject = (Room) myObject.read(id);
                    System.out.println(myObject);
                    RoomManagement.roomMenu();
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
            Room myObject = new Room();
            Room myOldObject = new Room();
            boolean continueInput = true;
            do {
                try {
                    System.out.print("Enter id: ");
                    String id = userInput.nextLine();
                    myObject.setID(id);
                    System.out.print("Room types:-\n");
                    for (String type: Room.types) {
                        System.out.println(type);
                    }
                    System.out.print("Enter type: ");
                    myObject.setType(userInput.nextLine());
                    System.out.print("Enter status (available/Status id): ");
                    myObject.setStatus(userInput.nextLine());
                    System.out.print("Enter current Service ID: ");
                    myObject.setCurrentServiceID(userInput.nextLine());
                    System.out.print("Enter price: ");
                    myObject.setRoomPrice(userInput.nextInt());
                    myOldObject = (Room) myOldObject.read(id);
                    if (!myObject.update(myOldObject)) {
                        throw new Exception("Error!");
                    }
                    System.out.println("Updated with id: " + myObject.getID());
                    RoomManagement.roomMenu();
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
            Room myObject = new Room();
            boolean continueInput = true;

            do {
                try {
                    System.out.print("Enter id: ");
                    String id = userInput.nextLine();
                    myObject.setID(id);
                    myObject = (Room) myObject.read(id);
                    if (!myObject.delete(myObject)) {
                        throw new Exception("Error!");
                    }
                    System.out.println("Deleted with id: " + myObject.getID());
                    RoomManagement.roomMenu();
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
            Room myObject = new Room();
            ArrayList<Room> allObjects = myObject.getAll();
            for (Room object : allObjects) {
                System.out.println(object.toString());
            }
            RoomManagement.roomMenu();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private static void filterRooms() {
        try (Scanner userInput = new Scanner(System.in)) {
            boolean continueInput = true;

            do {
                try {
                    Room myObject = new Room();
                    System.out.println("(1) Not Busy Rooms");
                    System.out.println("(2) Busy Rooms");
                    System.out.println("(3) Room's Type");
                    System.out.println("(4) Room's Service Name");
                    System.out.print("Enter filter key: ");
                    int option = userInput.nextInt();
                    userInput.nextLine();
                    switch (option) {
                        case 1 -> {
                            ArrayList<Room> data = myObject.filter("notBusy", "");
                            for (Room room: data)
                                System.out.println(room.toString());
                        }
                        case 2 -> {
                            ArrayList<Room> data = myObject.filter("busy", "");
                            for (Room room: data)
                                System.out.println(room.toString());
                        }
                        case 3 -> {
                            System.out.print("Room types:-\n");
                            for (String type: Room.types) {
                                System.out.println(type);
                            }
                            System.out.print("Enter type: ");
                            String type = userInput.nextLine();
                            ArrayList<Room> data = myObject.filter("type", type);
                            for (Room room: data)
                                System.out.println(room.toString());
                        }
                        case 4 -> {
                            System.out.print("Enter service name: ");
                            String service = userInput.nextLine();
                            ArrayList<Room> data = myObject.filter("service", service);
                            for (Room room: data)
                                System.out.println(room.toString());
                        }
                        default -> throw new Exception("Invalid input");
                    }
                    RoomManagement.roomMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println((e.getMessage() != null) ? e.getMessage() : "Invalid input");
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }
    private static void requestService() {
        try (Scanner userInput = new Scanner(System.in)) {
            Room myObject = new Room(), oldRoom = new Room();
            Service myService = new Service();
            RoomStatus myRoomStatus = new RoomStatus();
            Bill myBill = new Bill();
            boolean continueInput = true;

            do {
                try {
                    Customer customer = new Customer();
                    System.out.print("Enter service id: ");
                    String sid = userInput.nextLine();
                    myService = (Service) myService.read(sid);
                    System.out.print("Enter room id: ");
                    String rid = userInput.nextLine();
                    myObject = (Room) myObject.read(rid);
                    oldRoom = (Room) oldRoom.read(rid);
                    myRoomStatus = (RoomStatus) myRoomStatus.read(myObject.getStatus());
                    if(!myRoomStatus.getStatus().equals("approved"))
                        throw new Exception("Pending Customer");
                    myObject.setCurrentServiceID(myService.getID());
                    myObject.update(oldRoom);
                    customer = (Customer) customer.read(myRoomStatus.getCustomerID());
                    myBill = (Bill) myBill.read(customer.getbillID());
                    myBill.requestService(myService);
                    System.out.println("Added :)");
                    RoomManagement.roomMenu();
                    continueInput = false;
                } catch (Exception e) {
                    System.out.println((e.getMessage() != null) ? e.getMessage() : "Invalid input");
                    userInput.nextLine(); // Clear the buffer
                }
            } while (continueInput);
        }
    }
}
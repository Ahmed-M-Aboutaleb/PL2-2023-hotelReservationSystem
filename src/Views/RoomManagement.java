package Views;

import Other.Service;
import com.sun.tools.javac.Main;

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
        System.out.println("(7) Rooms Status Menu");
        System.out.println("(8) Back");
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
                    case 7 -> {
                        RoomStatusManagement.roomMenu();
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
        // to do addRoom
    }

    private static void viewRoom() {
        // to do viewRoom
    }

    private static void updateRoom() {
        // to do updateRoom
    }

    private static void deleteRoom() {
        // to do deleteRoom
    }

    private static void allRooms() {
        // to do allRooms
    }
    private static void filterRooms() {
        // to do filterRooms
    }
}
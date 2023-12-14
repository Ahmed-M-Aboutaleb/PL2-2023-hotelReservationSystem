package Views;

import com.sun.tools.javac.Main;

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
    private static void nearCheckout() {
        // to do nearCheckout
    }
    private static void assignRoom() {
        // to do assignRoom
    }
}
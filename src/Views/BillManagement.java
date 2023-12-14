package Views;

import com.sun.tools.javac.Main;

import java.util.Scanner;

public class BillManagement {
    public static void billMenu() {
        System.out.println("Room Menu: ");
        System.out.println("(1) Add Bill");
        System.out.println("(2) View Bill");
        System.out.println("(3) Update Bill");
        System.out.println("(4) Delete Bill");
        System.out.println("(5) View All Bills");
        System.out.println("(6) Back");
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
                        continueInput = false;
                        BillManagement.deleteBill();
                    }
                    case 5 -> {
                        BillManagement.allBills();
                        continueInput = false;
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

    private static void addBill() {
        // to do addBill
    }

    private static void viewBill() {
        // to do viewBill
    }

    private static void updateBill() {
        // to do updateBill
    }

    private static void deleteBill() {
        // to do deleteBill
    }

    private static void allBills() {
        // to do allBills
    }
}
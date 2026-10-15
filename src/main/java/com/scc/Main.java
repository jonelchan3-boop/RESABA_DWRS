
package com.scc;

import com.scc.dao.UserDao;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final UserDao userDao = new UserDao();

    public static void main(String[] args) {

        System.out.println("\n================================");
        System.out.println("  DRINKING WATER REFILLING & DELIVERY SYSTEM");
        System.out.println("================================");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");

        String choice = sc.nextLine();

        switch (choice) {
            case "1":
                System.out.println("Login feature coming soon.");
                break;

            case "2":
                System.out.print("Enter new username: ");
                String user = sc.nextLine();

                System.out.print("Enter password: ");
                String pass = sc.nextLine();

                System.out.println("Select Role:");
                System.out.println("1. STAFF/CASHIER");
                System.out.println("2. DELIVERY RIDER");
                System.out.println("3. ADMIN");
                System.out.print("Choose role: ");

                int roleChoice = sc.nextInt();
                sc.nextLine();

                String role;

                switch (roleChoice) {
                    case 1:
                        role = "STAFF/CASHIER";
                        break;
                    case 2:
                        role = "DELIVERY RIDER";
                        break;
                    case 3:
                        role = "ADMIN";
                        break;
                    default:
                        role = null;
                }

                if (role != null) {
                    boolean registered =
                            userDao.registerUser(user, pass, role);

                    if (registered) {
                        System.out.println(
                                "Registration successful! Waiting for approval.");
                    } else {
                        System.out.println(
                                "Registration failed. Please try again.");
                    }
                } else {
                    System.out.println("Invalid role selected.");
                }
                break;

            case "3":
                System.out.println(
                        "Thank you for using Water Refilling System!");
                break;

            default:
                System.out.println("Invalid option.");
        }

        sc.close();
    }
}

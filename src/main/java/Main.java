package com.scc;

import com.scc.dao.UserDao;
import com.scc.model.User;
import com.scc.ui.AdminMenu;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final UserDao userDao = new UserDao();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n==================================");
            System.out.println(" DRINKING WATER REFILLING & DELIVERY SYSTEM ");
            System.out.println("==================================");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    handleLogin();
                    break;
                case "2":
                    System.out.print("Enter new username: ");
                    String user = sc.nextLine();
                    System.out.print("Enter password: ");
                    String pass = sc.nextLine();

                    System.out.print("Select Role [1] STAFF/CASHIER | [2] DELIVERY RIDER | [3] ADMIN: ");
                    int roleChoice = sc.nextInt();
                    sc.nextLine(); // Clear newline

                    String role;
                    switch (roleChoice) {
                        case 1:
                            role = "STAFF";
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
                        userDao.registerUser(user, pass, role);
                    } else {
                        System.out.println("Invalid role selection!");
                    }
                    break;
                case "3":
                    running = false;
                    System.out.println("Exiting application...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void handleLogin() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        User user = userDao.login(username, password);

        if (user != null) {
            System.out.println("\n -> Welcome " + user.username() + " | ROLE: " + user.roles() + "");
            switch (user.roles()) {
                case "ADMIN" -> new AdminMenu(userDao, sc).display(user);
                case "STAFF" -> System.out.println("Staff Dashboard coming soon!");
                case "DELIVERY RIDER" -> System.out.println("Delivery Rider Dashboard coming soon!");
                default -> System.out.println("Account roles not found!");
            }
        }
    }
}
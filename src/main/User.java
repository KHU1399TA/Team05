package main;

import main.Enums.AccessLevel;
import main.Enums.ActionResult;
import utils.FileManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

abstract class User{
    String firstName;
    String lastName;
    String phoneNumber;
    String userName;
    String password;
    AccessLevel accessLevel;
    String registrationDate; //edited from date to string
    String lastLoginDate; //edited from date to string




    static AccessLevel login() {

        String result = "not_ok";
        String accessLevel = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your username and password");
        String username = scanner.nextLine();
        String password = scanner.nextLine();
        StringBuilder allString = new StringBuilder();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("src/resources/Usernames-and-Passwords"));
            String line = reader.readLine();

            while (line != null) {
                // System.out.println(line);
                // read next line

                String ins1 = line;
                String[] ins2 = ins1.split(",");
                String username2 = ins2[3];
                String password2 = ins2[4];

                if (username.equals(username2) && password.equals(password2)) {

                    Main.username_for_order = username2;

                    result = "ok";
                    accessLevel = ins2[5];
                    //System.out.println("Success");
                    //System.out.println(manager2[7]);
                    ins2[7] = Main.currentTime();
                    StringBuilder ins3 = new StringBuilder();
                    for (int i = 0; i < 8; i++) {
                        if (i != 7) {
                            ins3.append(ins2[i] + ",");

                        } else {
                            ins3.append(ins2[i]);
                        }
                    }

                    allString.append(ins3);
                    allString.append("\n");

                } else {
                    allString.append(line);
                    allString.append("\n");
                }

                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileManager fmanager = new FileManager("src/resources/Usernames-and-Passwords");
        fmanager.write(allString.toString(), false);

        if (result.equals("ok")) {
            System.out.println("Login is successful :)");

            switch (accessLevel) {
                case "MANAGER":
                    return AccessLevel.MANAGER;
                //break;
                case "CASHIER":
                    return AccessLevel.CASHIER;
                //break;
                case "CHEF":
                    return AccessLevel.CHEF;
                //break;
                case "DELIVERYMAN":
                    return AccessLevel.DELIVERYMAN;
                //break;
                case "CLIENT":
                    return AccessLevel.CLIENT;
                //break;
            }

        } else {
            System.out.println("Login is not successful due to invalid username or password :(.");
            // System.out.println("Please run the program again!");
        }
        return AccessLevel.NOACCESSLEVEL;
    }
}

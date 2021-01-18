package main;

import main.Enums.AccessLevel;
import main.Enums.ActionResult;

import static main.Restaurant.user;

import java.util.Date;
import java.util.Scanner;

import static main.User.login;

public class Main {


    private static Scanner input;

    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        String userName;
        String password;
        AccessLevel accessLevel;
        Date registrationDate;
        Date lastLoginDate;
        String firstName;
        String lastName;
        String phoneNumber;
        String address;
        input = new Scanner(System.in);
        boolean exist = false;
        while (exist) {
            System.out.println("Enter a number ");
            System.out.println("1) Register(Clients)\n2) Login\n3) exit");
            int num = input.nextInt();
            switch (num) {
                case 1:
                    System.out.println("welcome ! \nplease answer the questions");
                    System.out.println("firstname    :");
                    firstName = input.nextLine();
                    System.out.println("lastname     : ");
                    lastName = input.nextLine();
                    System.out.println("phone number : ");
                    phoneNumber = input.nextLine();
                    System.out.println("address      : ");
                    address = input.nextLine();
                    System.out.println("username     : ");
                    userName = input.nextLine();
                    System.out.println("password     : ");
                    password = input.nextLine();
                    accessLevel = AccessLevel.CLIENT;
                    registrationDate = new Date();
                    lastLoginDate = registrationDate;
                    Client client = new Client(userName, password, accessLevel, registrationDate, lastLoginDate, firstName, lastName, phoneNumber, address);
                    System.out.println(restaurant.registerForClients(client));
                    if (restaurant.registerForClients(client) == ActionResult.SUCCESS) {
                        user.add(client);
                        System.out.println("You have successfully registered ! ");
                    }


                case 2:
                    System.out.println("Login ");
                    System.out.print("username : ");
                    userName = input.nextLine();
                    System.out.print("password :");
                    password = input.nextLine();
                    System.out.println(login(userName, password).actionResult);
                    if (login(userName, password).actionResult == ActionResult.SUCCESS) {
                        if (login(userName, password).user.accessLevel == AccessLevel.MANAGER) { //manager
                            boolean exitManager = false;
                            while (exitManager)
                                System.out.println("welcome Manager ! ");
                            System.out.println("1) register \n2) edit\n3) remove\n 4)exit");
                            num = input.nextInt();
                            switch (num) {
                                case 1:

                                case 2:

                                case 3:

                                case 4:
                                    exitManager = true;
                            }
                        }
                        if (login(userName, password).user.accessLevel == AccessLevel.CHEF) { //chef
                            boolean exitChef = false;
                            while (exitChef)
                                System.out.println("welcome Chef ! ");
                            System.out.println("1) add food \n2) edit food\n3) remove food\n 4)change food state\n5) cook\n6) exit");
                            num = input.nextInt();
                            switch (num) {
                                case 1:

                                case 2:

                                case 3:

                                case 4:

                                case 5:

                                case 6:
                                    exitChef = true;
                            }
                        }
                        if (login(userName, password).user.accessLevel == AccessLevel.DELIVERYMAN) { //deliverman
                            boolean exitDeliverMan = false;
                            while (exitDeliverMan)
                                System.out.println("welcome Deliverman ! ");
                            System.out.println("1) deliver\n2) exit");
                            num = input.nextInt();
                            switch (num) {
                                case 1:

                                case 2:
                                    exitDeliverMan = true;
                            }
                        }
                        if (login(userName, password).user.accessLevel == AccessLevel.CLIENT) {
                            boolean exitClient = false;
                            while (exitClient)
                                System.out.println("welcome Chef ! ");
                            System.out.println("1) order\n2)revoke \n3)  exit");
                            num = input.nextInt();
                            switch (num) {
                                case 1:

                                case 2:

                                case 3:
                                    exitClient = true;
                            }
                        }
                        if (login(userName, password).user.accessLevel == AccessLevel.CASHIER) {
                            boolean exitCasheir = false;
                            while (exitCasheir)
                                System.out.println("welcome Chef ! ");
                            System.out.println("1) confirm order\n)2  exit");
                            num = input.nextInt();
                            switch (num) {
                                case 1:

                                case 2:
                                    exitCasheir = true;
                            }
                        }

                    }


                case 3:
                    exist = true;
                    System.out.println("Have a nice time\nBye!");
                    return;
            }

        }
    }
}


















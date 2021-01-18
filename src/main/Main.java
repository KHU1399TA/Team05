package main;

import main.Enums.*;

import static main.Restaurant.user;

import java.util.*;

import static main.User.login;

public class Main {


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
        Scanner input = new Scanner(System.in);
        System.out.println("manager registration ! ");
        System.out.println("welcome ! \n\nplease answer the questions");
        System.out.print("firstname    : ");
        firstName = input.nextLine();
        System.out.print("lastname     : ");
        lastName = input.nextLine();
        System.out.print("phone number : ");
        phoneNumber = input.nextLine();
        System.out.println("!!!!   username should not contain space   !!!!!");
        System.out.print("username     : ");
        userName = input.nextLine();
        System.out.print("password     : ");
        password = input.nextLine();
        registrationDate = new Date();
        lastLoginDate = new Date();
        accessLevel = AccessLevel.MANAGER;
        Manager manager = new Manager(userName, password, accessLevel, registrationDate, lastLoginDate, firstName, lastName, phoneNumber);
        System.out.println("\n" + manager.register(manager));

        if (manager.register(manager) == ActionResult.SUCCESS) {
            user.add(manager);
            System.out.println("_".repeat(30) + "\n");
            System.out.println("You have successfully registered ! \n");
            System.out.println("_".repeat(30) + "\n");
        }

        boolean exist = true;
        while (exist) {
            System.out.println(">home\n");
            System.out.println("Enter a number ");
            System.out.println("1) Register(Clients)\n2) Login\n3) exit");
            System.out.print("number : ");
            int num = input.nextInt();
            input.nextLine();
            System.out.println("-".repeat(30));
            switch (num) {
                case 1:
                    System.out.println(">home>Register(Clients)\n");
                    System.out.println("welcome ! \n\nplease answer the questions");
                    System.out.print("firstname    : ");
                    firstName = input.nextLine();
                    System.out.print("lastname     : ");
                    lastName = input.nextLine();
                    System.out.print("phone number : ");
                    phoneNumber = input.nextLine();
                    System.out.print("address      : ");
                    address = input.nextLine();
                    System.out.println("!!!!   username should not contain space   !!!!!");
                    System.out.print("username     : ");
                    userName = input.nextLine();
                    System.out.print("password     : ");
                    password = input.nextLine();
                    accessLevel = AccessLevel.CLIENT;
                    registrationDate = new Date();
                    lastLoginDate = registrationDate;
                    Client client = new Client(userName, password, accessLevel, registrationDate, lastLoginDate, firstName, lastName, phoneNumber, address);
                    System.out.println("\n" + restaurant.registerForClients(client));
                    if (restaurant.registerForClients(client) == ActionResult.SUCCESS) {
                        user.add(client);
                        System.out.println("-".repeat(30) + "\n");
                        System.out.println("You have successfully registered ! \n");
                        System.out.println("-".repeat(30) + "\n");
                    }
                    break;


                case 2:
                    System.out.println(">home>login\n");
                    System.out.println("Login ");
                    System.out.print("username : ");
                    userName = input.nextLine();
                    System.out.print("password : ");
                    password = input.nextLine();
                    System.out.println("\n" + login(userName, password).actionResult);
                    System.out.println("-".repeat(30));
                    if (login(userName, password).actionResult == ActionResult.SUCCESS) {
                        if (login(userName, password).user.accessLevel == AccessLevel.MANAGER) { //manager
                            System.out.println("welcome Manager ! \n");
                            boolean exitManager = true;
                            while (exitManager) {
                                System.out.println(">home>login>Manager\n");
                                System.out.println("please enter a number ");
                                System.out.println(" 1) register \n 2) edit\n 3) remove\n 4) exit");
                                num = input.nextInt();
                                input.nextLine();
                                switch (num) {
                                    case 1:
                                        System.out.println(">home>login>Manager>register\n");
                                        System.out.println("which one ?");
                                        System.out.println(" 1) Manager\n 2) Chef\n 3) Cashier\n 4) Deliveran");
                                        num = input.nextInt();
                                        input.nextLine();
                                        switch (num) {
                                            case 2 -> accessLevel = AccessLevel.CHEF;
                                            case 3 -> accessLevel = AccessLevel.CASHIER;
                                            case 4 -> accessLevel = AccessLevel.DELIVERYMAN;
                                            default -> accessLevel = AccessLevel.MANAGER;
                                        }
                                        System.out.print("firstname    : ");
                                        firstName = input.nextLine();
                                        System.out.print("lastname     : ");
                                        lastName = input.nextLine();
                                        System.out.print("phone number : ");
                                        phoneNumber = input.nextLine();
                                        System.out.println("!!!!   username should not contain space   !!!!!");
                                        System.out.print("username     : ");
                                        userName = input.nextLine();
                                        System.out.print("password     : ");
                                        password = input.nextLine();
                                        registrationDate = new Date();
                                        lastLoginDate = new Date();
                                        if (accessLevel == AccessLevel.CASHIER) {
                                            Cashier cashier = new Cashier(userName, password, accessLevel, registrationDate, lastLoginDate, firstName, lastName, phoneNumber);
                                            System.out.println(manager.register(cashier));
                                            if (manager.register(cashier) == ActionResult.SUCCESS) {
                                                System.out.println("_".repeat(30) + "\n");
                                                System.out.println("the employee is successfully registered ! \n");
                                                System.out.println("_".repeat(30) + "\n");
                                            }
                                        } else if (accessLevel == AccessLevel.DELIVERYMAN) {
                                            DeliverMan deliverMan = new DeliverMan(userName, password, accessLevel, registrationDate, lastLoginDate, firstName, lastName, phoneNumber);
                                            System.out.println(manager.register(deliverMan));
                                            if (manager.register(deliverMan) == ActionResult.SUCCESS) {
                                                System.out.println("_".repeat(30) + "\n");
                                                System.out.println("the employee is successfully registered ! \n");
                                                System.out.println("_".repeat(30) + "\n");
                                            }
                                        } else if (accessLevel == AccessLevel.CHEF) {
                                            Chef chef = new Chef(userName, password, accessLevel, registrationDate, lastLoginDate, firstName, lastName, phoneNumber);
                                            System.out.println(manager.register(chef));
                                            if (manager.register(chef) == ActionResult.SUCCESS) {
                                                System.out.println("_".repeat(30) + "\n");
                                                System.out.println("the employee is successfully registered ! \n");
                                                System.out.println("_".repeat(30) + "\n");
                                            }
                                        } else {
                                            Manager otherManager = new Manager(userName, password, accessLevel, registrationDate, lastLoginDate, firstName, lastName, phoneNumber);
                                            System.out.println(manager.register(otherManager));
                                            if (manager.register(otherManager) == ActionResult.SUCCESS) {
                                                System.out.println("_".repeat(30) + "\n");
                                                System.out.println("the employee is successfully registered ! \n");
                                                System.out.println("_".repeat(30) + "\n");
                                            }
                                        }
                                        break;

                                    case 2:
                                        System.out.println(">home>login>Manager>edit");
                                        System.out.print("username : ");
                                        userName=input.nextLine();
                                        System.out.println( "\n"+manager.edit(userName));
                                        break;
                                    case 3:
                                        System.out.println(">home>login>Manager>remove");
                                        System.out.print("username : ");
                                        userName=input.nextLine();
                                        System.out.println(manager.remove(userName));
                                        break;

                                    case 4:
                                        exitManager = false;
                                        break;
                                }
                            }
                        } else if (login(userName, password).user.accessLevel == AccessLevel.CHEF) { //chef
                            boolean exitChef = true;
                            while (exitChef) {
                                System.out.println(">home>login>Chef\n");
                                System.out.println("welcome Chef ! \n");
                                System.out.println("please enter a number ");
                                System.out.println(" 1) add food \n 2) edit food\n 3) remove food\n 4) change food state\n 5) cook\n 6) exit");
                                num = input.nextInt();
                                input.nextLine();
                                switch (num) {
                                    case 1:

                                    case 2:

                                    case 3:

                                    case 4:

                                    case 5:

                                    case 6:
                                        exitChef = false;
                                        break;
                                }
                            }
                        } else if (login(userName, password).user.accessLevel == AccessLevel.DELIVERYMAN) { //deliverman
                            boolean exitDeliverMan = true;
                            while (exitDeliverMan) {
                                System.out.println(">home>login>Deliverman\n");
                                System.out.println("welcome Deliverman ! \n");
                                System.out.println("please enter a number ");
                                System.out.println(" 1) deliver\n 2) exit");
                                num = input.nextInt();
                                input.nextLine();
                                switch (num) {
                                    case 1:

                                    case 2:
                                        exitDeliverMan = false;
                                        break;
                                }
                            }
                        } else if (login(userName, password).user.accessLevel == AccessLevel.CLIENT) { //client
                            boolean exitClient = true;
                            while (exitClient) {
                                System.out.println(">home>login>Client\n");
                                System.out.println("welcome Client! \n");
                                System.out.println("please enter a number ");
                                System.out.println(" 1) order\n 2)revoke \n 3) exit");
                                num = input.nextInt();
                                input.nextLine();
                                switch (num) {
                                    case 1:

                                    case 2:

                                    case 3:
                                        exitClient = false;
                                        break;
                                }
                            }
                        } else if (login(userName, password).user.accessLevel == AccessLevel.CASHIER) { //cashier
                            boolean exitCasheir = true;
                            while (exitCasheir) {
                                System.out.println(">home>login>Cashier\n");
                                System.out.println("welcome cashier ! ");
                                System.out.println("please enter a number ");
                                System.out.println(" 1) confirm order\n 2) exit");
                                num = input.nextInt();
                                input.nextLine();
                                switch (num) {
                                    case 1:

                                    case 2:
                                        exitCasheir = false;
                                        break;
                                }
                            }
                        }

                    }
                    break;


                case 3:
                    exist = false;
                    System.out.println("Have a nice time\nBye !!");
                    return;
            }

        }
    }
}


















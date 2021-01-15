package main;

import main.Enums.*;

import java.util.*;

public class Main {
    private static Scanner input;
    public static String userName;
    private static String password;
    public static AccessLevel accessLevel;
    public static Date registrationDate;
    public static Date lastLoginDate;
    public static Restaurant restaurant;
    public static Manager manager;


    public static void main(String[] args) {
        restaurant = new Restaurant();
        input = new Scanner(System.in);
        boolean again = true;
        while (again) {
            X(userName, password, registrationDate, lastLoginDate);
            System.out.println("registrationDate : " + registrationDate.toString());
            System.out.println("lastLoginDate : " + registrationDate.toString());
            Manager manager = new Manager(userName, password, accessLevel, registrationDate, lastLoginDate);
            System.out.println(manager.register(manager));
            if (manager.register(manager) == ActionResult.SUCCESS) again = false;
        }

        System.out.println("1) register(for clients)\n2) login");
        int num = input.nextInt();
        switch (num) {
            case 1:
                registerClient(AccessLevel.CLIENT);


            case 2:
                System.out.println("Who are you ?");
                System.out.println("1) Manager \n2) Chef\n3) Cashier\n4) Deliveryman\n5) Client");
                num = input.nextInt();
                switch (num) {
                    case 1:
                        //login method

                        System.out.println("Enter the number : ");
                        System.out.println("1) register\n2) edit\n3) remove");
                        num = input.nextInt();
                        switch (num) {
                            case 1:
                                System.out.println("1) Manager\n2) Chef\n3) Cashier\n4)Deliverman");
                                num = input.nextInt();
                                switch (num) {
                                    case 1: {
                                        again = true;
                                        while (again) {
                                            X(userName, password, registrationDate, lastLoginDate);
                                            System.out.println("registrationDate : " + registrationDate.toString());
                                            System.out.println("lastLoginDate : " + registrationDate.toString());
                                            accessLevel = AccessLevel.MANAGER;
                                            manager = new Manager(userName, password, accessLevel, registrationDate, lastLoginDate);
                                            System.out.println(manager.register(manager));
                                            if (manager.register(manager) == ActionResult.SUCCESS) again = false;
                                        }
                                    }
                                    case 2: {
                                        again = true;
                                        while (again) {
                                            X(userName, password, registrationDate, lastLoginDate);
                                            System.out.println("registrationDate : " + registrationDate.toString());
                                            System.out.println("lastLoginDate : " + registrationDate.toString());
                                            accessLevel = AccessLevel.CHEF;
                                            Chef chef = new Chef(userName, password, accessLevel, registrationDate, lastLoginDate);
                                            System.out.println(manager.register(chef));
                                            if (manager.register(chef) == ActionResult.SUCCESS) again = false;
                                        }
                                    }
                                    case 3: {
                                        again = true;
                                        while (again) {
                                            X(userName, password, registrationDate, lastLoginDate);
                                            System.out.println("registrationDate : " + registrationDate.toString());
                                            System.out.println("lastLoginDate : " + registrationDate.toString());
                                            accessLevel = AccessLevel.CASHIER;
                                            Cashier cashier = new Cashier(userName, password, accessLevel, registrationDate, lastLoginDate);
                                            System.out.println(manager.register(cashier));
                                            if (manager.register(cashier) == ActionResult.SUCCESS) again = false;
                                        }
                                    }
                                    case 4: {
                                        again = true;
                                        while (again) {
                                            X(userName, password, registrationDate, lastLoginDate);
                                            System.out.println("registrationDate : " + registrationDate.toString());
                                            System.out.println("lastLoginDate : " + registrationDate.toString());
                                            accessLevel = AccessLevel.DELIVERYMAN;
                                            DeliverMan deliverMan = new DeliverMan(userName, password, accessLevel, registrationDate, lastLoginDate);
                                            System.out.println(manager.register(deliverMan));
                                            if (manager.register(deliverMan) == ActionResult.SUCCESS) again = false;
                                        }
                                    }
                                }


                        }


                    case 2: {

                    }


                }

        }
    }


    private static void registerClient(AccessLevel accessLevel) {
        String userName;
        String password;
        String address;
        boolean again = true;
        while (again) {
            System.out.println("username shouldn't contain space ! ");
            System.out.print("username : ");
            userName = input.nextLine();
            System.out.print("password : ");
            password = input.nextLine();
            System.out.print("address : ");
            address = input.nextLine();
            registrationDate = new Date();
            System.out.println("registrationDate : " + registrationDate.toString());
            lastLoginDate = new Date();
            System.out.println("lastLoginDate : " + registrationDate.toString());
            Client client = new Client(userName, password, accessLevel, registrationDate, lastLoginDate, address);
            System.out.println(register(client));
            if (register(client) == ActionResult.SUCCESS) again = false;
        }
    }

    private static ActionResult register(User user) {
        if (user.userName.contains(" ")) return ActionResult.INVALID_USERNAME;
        for (int i = 0; i < restaurant.user.size(); i++)
            if (restaurant.user.get(i).equals(user.userName)) return ActionResult.USERNAME_ALREADY_EXIST;
        restaurant.user.add(user);
        return ActionResult.SUCCESS;
    }

    private static void X(String userName, String password, Date registrationDate, Date lastLoginDate) {
        System.out.println("username shouldn't contain space ! ");
        System.out.print("username : ");
        userName = input.nextLine();
        System.out.print("password : ");
        password = input.nextLine();
        registrationDate = new Date();
        lastLoginDate = new Date();
    }
}


















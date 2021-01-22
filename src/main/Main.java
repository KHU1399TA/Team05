package main;
import main.Enums.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import static main.User.login;
import utils.FileManager;

public class Main {
    private static final String DATA_FILE_PATH = "src/resources/data.txt";
    private static Scanner input;

    public static void main(String[] args) throws ParseException {
        Restaurant restaurant=new Restaurant();
        input = new Scanner(System.in);
        FileManager fileManager = new FileManager(DATA_FILE_PATH);
        String userName;
        String password;
        AccessLevel accessLevel;
        Date registrationDate;
        Date lastLoginDate;
        String firstName;
        String lastName;
        String phoneNumber;
        String address;

        Chef chef = new Chef("asd", "sd", AccessLevel.CHEF, new Date(), new Date(), "dsf", "sdf", "sdf");
        Manager manager = new Manager("manager", "123", AccessLevel.MANAGER, new Date(), new Date(), "manager", "managery", "0910000");
        manager.register(manager);

        if (fileManager.readLine(1) != null) {
            int x = fillFoodArraylist(fillClientsArrayList(fillUsersArrayList(fileManager, manager), fileManager), fileManager);
            fillOrderArraylist(x, fileManager);
        }

        boolean exist = true;
        while (exist) {
            System.out.println(">home\n");
            System.out.println("Enter a number ");
            System.out.println("1) Register(Clients)\n2) Login\n3) exit");
            System.out.print("number : ");
            int selectedNumber = input.nextInt();
            input.nextLine();
            System.out.println("_".repeat(40));
            switch (selectedNumber) {
                case 1 -> {
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
                    System.out.println("\n" + "_".repeat(40) + "\n");
                }
                case 2 -> {
                    System.out.println(">home>login\n");
                    System.out.println("Login ");
                    System.out.print("username : ");
                    userName = input.nextLine();
                    System.out.print("password : ");
                    password = input.nextLine();
                    System.out.println("\n" + login(userName, password).actionResult);
                    System.out.println("_".repeat(40));
                    if (login(userName, password).actionResult == ActionResult.SUCCESS) {
                        if (login(userName, password).user.accessLevel == AccessLevel.MANAGER) { //manager
                            System.out.println("welcome Manager ! \n");
                            boolean previousManager = true;
                            while (previousManager) {
                                System.out.println(">home>login>Manager\n");
                                System.out.println("please enter a number ");
                                System.out.println(" 1) register \n 2) edit\n 3) remove\n 4) previous");
                                selectedNumber = input.nextInt();
                                input.nextLine();
                                switch (selectedNumber) {
                                    case 1 -> {
                                        System.out.println(">home>login>Manager>register\n");
                                        System.out.println("which one ?");
                                        System.out.println(" 1) Manager\n 2) Chef\n 3) Cashier\n 4) Deliveryman");
                                        selectedNumber = input.nextInt();
                                        input.nextLine();
                                        switch (selectedNumber) {
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
                                            System.out.println("\n" + "_".repeat(40) + "\n");
                                        } else if (accessLevel == AccessLevel.DELIVERYMAN) {
                                            Deliveryman deliveryman = new Deliveryman(userName, password, accessLevel, registrationDate, lastLoginDate, firstName, lastName, phoneNumber);
                                            System.out.println("\n" + manager.register(deliveryman));
                                            System.out.println("\n" + "_".repeat(40) + "\n");
                                        } else if (accessLevel == AccessLevel.CHEF) {
                                            chef = new Chef(userName, password, accessLevel, registrationDate, lastLoginDate, firstName, lastName, phoneNumber);
                                            System.out.println(manager.register(chef));
                                            System.out.println("\n" + "_".repeat(40) + "\n");
                                        } else {
                                            Manager otherManager = new Manager(userName, password, accessLevel, registrationDate, lastLoginDate, firstName, lastName, phoneNumber);
                                            System.out.println(manager.register(otherManager));
                                            System.out.println("\n" + "_".repeat(40) + "\n");
                                        }
                                    }
                                    case 2 -> {
                                        System.out.println(">home>login>Manager>edit");
                                        System.out.print("username : ");
                                        userName = input.nextLine();
                                        System.out.println("\n" + manager.edit(userName));
                                        System.out.println("\n" + "_".repeat(40) + "\n");
                                    }
                                    case 3 -> {
                                        System.out.println(">home>login>Manager>remove");
                                        System.out.print("username : ");
                                        userName = input.nextLine();
                                        System.out.println(manager.remove(userName));
                                        System.out.println("\n" + "_".repeat(40) + "\n");
                                    }
                                    case 4 -> {
                                        previousManager = false;
                                        System.out.println("\n" + "_".repeat(40) + "\n");
                                    }
                                }
                            }
                        } else if (login(userName, password).user.accessLevel == AccessLevel.CHEF) { //chef
                            boolean previousChef = true;
                            System.out.println("welcome Chef ! \n");
                            while (previousChef) {
                                System.out.println(">home>login>Chef\n");
                                System.out.println("please enter a number ");
                                System.out.println(" 1) add food \n 2) edit food\n 3) remove food\n 4) change food state\n 5) cook\n 6) previous");
                                selectedNumber = input.nextInt();
                                input.nextLine();
                                switch (selectedNumber) {
                                    case 1 -> System.out.println(chef.addFood(selectFood()));

                                    case 2 -> {
                                        System.out.print("id : ");
                                        System.out.println(chef.editFood(input.nextInt()));
                                    }

                                    case 3 -> {
                                        System.out.print("id : ");
                                        System.out.println(chef.removeFood(input.nextInt()));
                                    }

                                    case 4 -> chef.foodState();

                                    case 5 -> {
                                    }

                                    case 6 -> previousChef = false;
                                }
                            }
                        } else if (login(userName, password).user.accessLevel == AccessLevel.DELIVERYMAN) { //deliverman
                            boolean previousDeliverMan = true;
                            System.out.println("welcome Deliveryman ! \n");
                            while (previousDeliverMan) {
                                System.out.println(">home>login>Deliveryman\n");
                                System.out.println("please enter a number ");
                                System.out.println(" 1) deliver\n 2) previous");
                                selectedNumber = input.nextInt();
                                input.nextLine();
                                switch (selectedNumber) {
                                    case 1:    //[TODO]Deliverman

                                    case 2:
                                        previousDeliverMan = false;
                                        break;
                                }
                            }
                        } else if (login(userName, password).user.accessLevel == AccessLevel.CLIENT) { //client
                            boolean previousClient = true;
                            System.out.println("welcome Client! \n");
                            while (previousClient) {
                                System.out.println(">home>login>Client\n");
                                System.out.println("please enter a number ");
                                System.out.println(" 1) order\n 2)revoke \n 3) previous");
                                selectedNumber = input.nextInt();
                                input.nextLine();
                                switch (selectedNumber) {
                                    case 1:       // [TODO] client

                                    case 2:

                                    case 3:
                                        previousClient = false;
                                        break;
                                }
                            }
                        } else if (login(userName, password).user.accessLevel == AccessLevel.CASHIER) { //cashier
                            boolean previousCasheir = true;
                            System.out.println("welcome cashier ! ");
                            while (previousCasheir) {
                                System.out.println(">home>login>Cashier\n");
                                System.out.println("please enter a number ");
                                System.out.println(" 1) confirm order\n 2) previous");
                                selectedNumber = input.nextInt();
                                input.nextLine();
                                switch (selectedNumber) {
                                    case 1:      //[TODO]cashier

                                    case 2:
                                        previousCasheir = false;
                                        break;
                                }
                            }
                        }

                    }
                }
                case 3 -> {
                    exist = false;
                    fileManager.write("", false);
                    for (int i = 0; i < Restaurant.user.size(); i++) {            //filling file with users and clients and foods orders.//
                        fileManager.writeLine(Restaurant.user.get(i).toString(), true);
                    }
                    fileManager.writeLine("-".repeat(30), true);
                    for (int i = 0; i < Restaurant.client.size(); i++) {
                        fileManager.writeLine(Restaurant.client.get(i).toString(), true);
                    }
                    fileManager.writeLine("-".repeat(30), true);
                    for (int i = 0; i < Restaurant.food.size(); i++) {
                        fileManager.writeLine(Restaurant.food.get(i).toString(), true);
                    }
                    fileManager.writeLine("-".repeat(30), true);
                    System.out.println("Have a nice time\nBye !!");
                    return;
                }
            }
        }
    }

    private static Food selectFood() {
        int id;
        String name;
        int ingredientsSize;
        boolean isAvailable;
        System.out.print("Id   : ");
        id = input.nextInt();
        input.nextLine();
        System.out.print("Name : ");
        name = input.nextLine();
        System.out.println("how many ingredients ?");
        ingredientsSize = input.nextInt();
        input.nextLine();
        String[] ingredients=new String[ingredientsSize];
        for (int i = 0; i < ingredientsSize; i++) {
           ingredients[i]=input.nextLine();
        }
        System.out.println("Is available now ?");
        System.out.println("1)Yes");
        System.out.println("2)No");
        int selectedNumber = input.nextInt();
        isAvailable = selectedNumber == 1;
        return new Food(id, name, ingredients, isAvailable);
    }

    private static int fillUsersArrayList(FileManager fileManager, Manager manager) throws ParseException {
        String userName;
        String password;
        Date registrationDate;
        Date lastLoginDate;
        String firstName;
        String lastName;
        String phoneNumber;
        int x = 1;
        while (!fileManager.readLine(x).equals("-".repeat(30))) {
            userName = fileManager.readLine(x + 1).substring(18);
            password = fileManager.readLine(x + 2).substring(18);
            registrationDate = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy").parse(fileManager.readLine(x + 4).substring(18));
            lastLoginDate = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy").parse(fileManager.readLine(x + 5).substring(18));
            firstName = fileManager.readLine(x + 6).substring(18);
            lastName = fileManager.readLine(x + 7).substring(18);
            phoneNumber = fileManager.readLine(x + 8).substring(18);
            if (fileManager.readLine(x + 3).substring(18).equals("MANAGER")) {
                Manager manager2 = new Manager(userName, password, AccessLevel.MANAGER, registrationDate, lastLoginDate, firstName, lastName, phoneNumber);
                manager.register(manager);
            }
            if (fileManager.readLine(x + 3).substring(18).equals("CHEF")) {
                Chef chef = new Chef(userName, password, AccessLevel.CHEF, registrationDate, lastLoginDate, firstName, lastName, phoneNumber);
                manager.register(chef);
            }
            if (fileManager.readLine(x + 3).substring(18).equals("CASHIER")) {
                Cashier cashier = new Cashier(userName, password, AccessLevel.CASHIER, registrationDate, lastLoginDate, firstName, lastName, phoneNumber);
                manager.register(cashier);
            }
            if (fileManager.readLine(x + 3).substring(18).equals("DELIVERYMAN")) {
                Deliveryman deliveryman = new Deliveryman(userName, password, AccessLevel.DELIVERYMAN, registrationDate, lastLoginDate, firstName, lastName, phoneNumber);
                manager.register(deliveryman);
            }
            x += 10;
        }
        return x + 1;
    }

    private static int fillClientsArrayList(int x, FileManager fileManager) throws ParseException {
        String userName;
        String password;
        Date registrationDate;
        Date lastLoginDate;
        String firstName;
        String lastName;
        String phoneNumber;
        String address;
        if (fileManager.readLine(x) != null) {
            while (!fileManager.readLine(x).equals("-".repeat(30))) {
                userName = fileManager.readLine(x + 1).substring(18);
                password = fileManager.readLine(x + 2).substring(18);
                registrationDate = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy").parse(fileManager.readLine(x + 4).substring(18));
                lastLoginDate = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy").parse(fileManager.readLine(x + 5).substring(18));
                firstName = fileManager.readLine(x + 6).substring(18);
                lastName = fileManager.readLine(x + 7).substring(18);
                phoneNumber = fileManager.readLine(x + 8).substring(18);
                address = fileManager.readLine(x + 9).substring(18);
                Client client = new Client(userName, password, AccessLevel.CLIENT, registrationDate, lastLoginDate, firstName, lastName, phoneNumber, address);
                Restaurant.client.add(client);
                x += 11;
            }
        }
        return x + 1;
    }

    private static int fillFoodArraylist(int x, FileManager fileManager) {
        int id;
        String name;
        boolean isAvailable;
        if (fileManager.readLine(x) != null) {
            while (!fileManager.readLine(x).equals("-".repeat(30))) {
                id = Integer.parseInt(fileManager.readLine(x + 1).substring(14));
                name = fileManager.readLine(x + 2).substring(14);
                String array = fileManager.readLine(x + 3).substring(14);
                String array2 = array.replace("[", "");
                String array3 = array2.replace("]", "");
                String array4 = array3.replace(" ", "");
                String[] ingredients = array4.split(",");

                isAvailable = "true".equals(fileManager.readLine(x + 4).substring(14));
                Food food = new Food(id, name, ingredients, isAvailable);
                Restaurant.food.add(food);
                x += 6;
            }
        }
        return x + 1;
    }

    private static void fillOrderArraylist(int x, FileManager fileManager) {

    }
}

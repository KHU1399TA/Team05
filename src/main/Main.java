package main;
import main.Enums.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import utils.FileManager;

public class Main {
    private static final String DATA_FILE_PATH = "src/resources/data.txt";
    private static final Scanner input= new Scanner(System.in);

    public static void main(String[] args) throws ParseException {

        FileManager fileManager = new FileManager(DATA_FILE_PATH);
        String userName;
        String password;
        Manager manager = new Manager("manager", "123", AccessLevel.MANAGER, new Date(), new Date(), "manager", "managery", "0910000");
        manager.register(manager);

        if (fileManager.readLine(1) != null) {
            int x = fillFoodArraylist(fillClientsArrayList(fillUsersArrayList(fileManager, manager), fileManager), fileManager);
            fillOrderArraylist(x, fileManager);
        }

        boolean exist = true;
        while (exist) {
            int selectedNumber=firstPage();
            switch (selectedNumber) {
                case 1 -> registerClient();
                case 2 -> {
                    System.out.println(">home>login\n");
                    System.out.println("Login ");
                    System.out.print("username : ");
                    userName = input.nextLine();
                    System.out.print("password : ");
                    password = input.nextLine();
                    System.out.println("\n" + User.login(userName, password).actionResult);
                    System.out.println("_".repeat(40));
                    if (User.login(userName, password).actionResult == ActionResult.SUCCESS) {
                        if (User.login(userName, password).user.accessLevel == AccessLevel.MANAGER) {
                            User.login(userName, password).user.lastLoginDate=new Date();
                            System.out.println("welcome Manager ! \n");
                            boolean previousManager = true;
                            while (previousManager) {
                                selectedNumber=mainMenuManager();
                                switch (selectedNumber) {
                                    case 1 -> registerManager(manager);
                                    case 2 -> editManager(manager);
                                    case 3 -> removeManager(manager);
                                    case 4 -> previousManager = false;
                                }
                            }
                        } else if (User.login(userName, password).user.accessLevel == AccessLevel.CHEF) {
                            User.login(userName, password).user.lastLoginDate=new Date();
                            System.out.println("welcome Chef ! \n");
                            boolean previousChef = true;
                            while (previousChef) {
                               selectedNumber=mainMenuChef();
                                switch (selectedNumber) {
                                    case 1 -> System.out.println(Chef.addFood(selectFood()));
                                    case 2 -> {
                                        System.out.print("id : ");
                                        System.out.println(Chef.editFood(input.nextInt()));
                                    }
                                    case 3 -> {
                                        System.out.print("id : ");
                                        System.out.println(Chef.removeFood(input.nextInt()));
                                    }
                                    case 4 -> Chef.foodState();
                                    case 5 -> ordersChef();
                                    case 6 -> previousChef = false;
                                }
                            }
                        } else if (User.login(userName, password).user.accessLevel == AccessLevel.DELIVERYMAN) {
                            User.login(userName, password).user.lastLoginDate=new Date();
                            System.out.println("welcome Deliveryman ! \n");
                            boolean previousDeliverMan = true;
                            while (previousDeliverMan) {
                                selectedNumber=mainMenuDeliveryman();
                                switch (selectedNumber) {
                                    case 1 -> ordersForDeliveryman();
                                    case 2 -> previousDeliverMan = false;
                                }
                            }
                        } else if (User.login(userName, password).user.accessLevel == AccessLevel.CLIENT) {
                            User.login(userName, password).user.lastLoginDate=new Date();
                            System.out.println("welcome Client! \n");
                            boolean previousClient = true;
                            while (previousClient) {
                               selectedNumber=mainMenuClient();
                                switch (selectedNumber) {
                                    case 1 -> receivingOrderInformation();
                                    case 2 -> revokingOrder();
                                    case 3 -> previousClient = false;
                                }
                            }
                        } else if (User.login(userName, password).user.accessLevel == AccessLevel.CASHIER) {
                            User.login(userName, password).user.lastLoginDate=new Date();
                            System.out.println("welcome cashier ! ");
                            boolean previousCashier = true;
                            while (previousCashier) {
                            selectedNumber=mainMenuCashier();
                                switch (selectedNumber) {
                                    case 1 -> ordersForCashier();
                                    case 2 -> previousCashier = false;
                                }
                            }
                        }

                    }
                }
                case 3 -> {
                    exist = false;
                    writingFile(fileManager);
                }
            }
        }
    }

    private static int firstPage(){
        System.out.println(">home\n");
        System.out.println("Enter a number ");
        System.out.println(" 1) Register(Clients)");
        System.out.println(" 2) Login");
        System.out.println(" 3) exit");
        System.out.print("number : ");
        int selectedNumber = input.nextInt();
        input.nextLine();
        System.out.println("_".repeat(40));
        return selectedNumber;
    }

    private static void registerClient(){
        String userName;
        String password;
        AccessLevel accessLevel;
        Date registrationDate;
        Date lastLoginDate;
        String firstName;
        String lastName;
        String phoneNumber;
        String address;
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
        System.out.println("\n" + Restaurant.registerForClients(client));
        System.out.println("\n" + "_".repeat(40) + "\n");
    }

    private static int mainMenuManager(){
        int selectedNumber;
        System.out.println(">home>login>Manager\n");
        System.out.println("please enter a number ");
        System.out.println(" 1) register");
        System.out.println(" 2) edit");
        System.out.println(" 3) remove");
        System.out.println(" 4) previous");
        selectedNumber = input.nextInt();
        input.nextLine();
        return selectedNumber;
    }

    private static void registerManager(Manager manager){
        String userName;
        String password;
        AccessLevel accessLevel;
        Date registrationDate;
        Date lastLoginDate;
        String firstName;
        String lastName;
        String phoneNumber;
        int selectedNumber;
        System.out.println(">home>login>Manager>register\n");
        System.out.println("which one ?");
        System.out.println(" 1) Manager");
        System.out.println(" 2) Chef");
        System.out.println(" 3) Cashier");
        System.out.println(" 4) Deliveryman");
        selectedNumber = input.nextInt();
        input.nextLine();
        switch (selectedNumber) {
            case 2  -> accessLevel = AccessLevel.CHEF;
            case 3  -> accessLevel = AccessLevel.CASHIER;
            case 4  -> accessLevel = AccessLevel.DELIVERYMAN;
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
            System.out.println("\n"+manager.register(cashier));
            System.out.println("\n" + "_".repeat(40) + "\n");
        } else if (accessLevel == AccessLevel.DELIVERYMAN) {
            Deliveryman deliveryman = new Deliveryman(userName, password, accessLevel, registrationDate, lastLoginDate, firstName, lastName, phoneNumber);
            System.out.println("\n" + manager.register(deliveryman));
            System.out.println("\n" + "_".repeat(40) + "\n");
        } else if (accessLevel == AccessLevel.CHEF) {
            Chef chef = new Chef(userName, password, accessLevel, registrationDate, lastLoginDate, firstName, lastName, phoneNumber);
            System.out.println("\n"+manager.register(chef));
            System.out.println("\n" + "_".repeat(40) + "\n");
        } else {
            Manager otherManager = new Manager(userName, password, accessLevel, registrationDate, lastLoginDate, firstName, lastName, phoneNumber);
            System.out.println("\n"+manager.register(otherManager));
            System.out.println("\n" + "_".repeat(40) + "\n");
        }
    }

    private static void editManager(Manager manager){
        String userName;
        System.out.println(">home>login>Manager>edit");
        System.out.print("username : ");
        userName = input.nextLine();
        System.out.println("\n" + manager.edit(userName));
        System.out.println("\n" + "_".repeat(40) + "\n");
    }

    private static void removeManager(Manager manager){
        String userName;
        System.out.println(">home>login>Manager>remove");
        System.out.print("username : ");
        userName = input.nextLine();
        System.out.println(manager.remove(userName));
        System.out.println("\n" + "_".repeat(40) + "\n");
    }

    private static int mainMenuChef(){
        int selectedNumber;
        System.out.println(">home>login>Chef\n");
        System.out.println("please enter a number ");
        System.out.println(" 1) add food ");
        System.out.println(" 2) edit food");
        System.out.println(" 3) remove food");
        System.out.println(" 4) change food state");
        System.out.println(" 5) cook");
        System.out.println(" 6) previous");
        selectedNumber = input.nextInt();
        input.nextLine();
        return selectedNumber;
    }

    private static int mainMenuDeliveryman(){
        int selectedNumber;
        System.out.println(">home>login>Deliveryman\n");
        System.out.println("please enter a number ");
        System.out.println(" 1) deliver\n 2) previous");
        selectedNumber = input.nextInt();
        input.nextLine();
        return selectedNumber;
    }

    private static int mainMenuClient(){
        int selectedNumber;
        System.out.println(">home>login>Client\n");
        System.out.println("please enter a number ");
        System.out.println(" 1) order");
        System.out.println(" 2) revoke");
        System.out.println(" 3) previous");
        selectedNumber = input.nextInt();
        input.nextLine();
        return selectedNumber;
    }

    private static int mainMenuCashier(){
        int selectedNumber;
        System.out.println(">home>login>Cashier\n");
        System.out.println("please enter a number ");
        System.out.println(" 1) confirm order\n 2) previous");
        selectedNumber = input.nextInt();
        input.nextLine();
        return selectedNumber;
    }
    private static Food selectFood() {
        int id;
        String name;
        int ingredientsSize;
        boolean isAvailable;
        System.out.println("home>login>chef>add food\n");
        System.out.print("Id   : ");
        id = input.nextInt();
        input.nextLine();
        System.out.print("Name : ");
        name = input.nextLine();
        System.out.println("how many ingredients ?");
        ingredientsSize = input.nextInt();
        input.nextLine();
        String[] ingredients = new String[ingredientsSize];
        for (int i = 0; i < ingredientsSize; i++) {
            ingredients[i] = input.nextLine();
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
            lastLoginDate    = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy").parse(fileManager.readLine(x + 5).substring(18));
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
                lastLoginDate    = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy").parse(fileManager.readLine(x + 5).substring(18));
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

    private static void fillOrderArraylist(int x, FileManager fileManager) throws ParseException {
        int id;
        String userName;
        int foodId;
        OrderState state;
        Date orderedAt;
        if (fileManager.readLine(x) !=null){
            while (fileManager.readLine(x) !=null){
                id=Integer.parseInt(fileManager.readLine(x+1).substring(12));
                userName=fileManager.readLine(x+2).substring(12);
                foodId=Integer.parseInt(fileManager.readLine(x+3).substring(12));
                if (fileManager.readLine(x+4).substring(12).equals("MADE")) state=OrderState.MADE;
                else if (fileManager.readLine(x+4).substring(12).equals("CONFIRMED")) state=OrderState.CONFIRMED;
                else if (fileManager.readLine(x+4).substring(12).equals("COOKED")) state=OrderState.COOKED;
                else state=OrderState.DELIVERED;
                orderedAt= new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy").parse(fileManager.readLine(x + 5).substring(12));
                x+=7;
                Order order=new Order(id,userName,foodId,state,orderedAt);
                Restaurant.order.add(order);
            }
        }

    }

    private static void receivingOrderInformation() {
        int id;
        String userName;
        int foodId;
        OrderState state;
        Date orderedAt;
        System.out.println("home>login>client>make order\n");
        for (int i = 0; i < Restaurant.food.size(); i++) {
            System.out.println(Restaurant.food.get(i));
        }
        System.out.print("Order id : ");
        id = input.nextInt();
        input.nextLine();
        System.out.print("username : ");
        userName = input.nextLine();
        System.out.print("food id  : ");
        foodId = input.nextInt();
        state = OrderState.MADE;
        orderedAt = new Date();
        Order order = new Order(id, userName, foodId, state, orderedAt);
        System.out.println("\n" + Client.makeOrder(order));
        System.out.println("_".repeat(40));

    }

    private static void revokingOrder(){
        int id;
        String userName;
        System.out.println(">home>login>client>revoke order\n");
        System.out.print("Order id : ");
        id=input.nextInt();
        input.nextLine();
        System.out.print("username : ");
        userName=input.nextLine();
        System.out.println("\n"+Client.revokeOrder(id,userName));
        System.out.println("_".repeat(40));

    }

    private static void ordersForCashier(){
        int id;
        System.out.println(">home>login>cashier>confirm order\n");
        for (int i=0;i<Restaurant.order.size();i++){
            if (Restaurant.order.get(i).state==OrderState.MADE)
                System.out.println(Restaurant.order.get(i));
        }
        System.out.print("order id : ");
        id=input.nextInt();
        input.nextLine();
        System.out.println("\n"+Cashier.confirmOrder(id));
        System.out.println("_".repeat(40));
    }

    private static void ordersChef(){
        int id;
        System.out.println(">home>login>chef>cook\n");
        for (int i=0;i<Restaurant.order.size();i++){
            if (Restaurant.order.get(i).state==OrderState.CONFIRMED)
                System.out.println(Restaurant.order.get(i));
        }
        System.out.print("order id : ");
        id=input.nextInt();
        input.nextLine();
        System.out.println("\n"+Chef.cook(id));
        System.out.println("_".repeat(40));
    }

    private static void ordersForDeliveryman(){
        int id;
        System.out.println(">home>login>deliveryman>deliver\n");
        for (int i=0;i<Restaurant.order.size();i++){
            if (Restaurant.order.get(i).state==OrderState.COOKED) {
                System.out.println(Restaurant.order.get(i));
                for (int j=0;j<Restaurant.client.size();j++){
                    if (Restaurant.order.get(i).userName.equals(Restaurant.client.get(j).userName))
                        System.out.println(" address   ="+Restaurant.client.get(j).address);
                }
            }
        }
        System.out.print("Order id : ");
        id=input.nextInt();
        input.nextLine();
        System.out.println("\n"+Deliveryman.deliver(id));
        System.out.println("_".repeat(40));
    }

    private static void writingFile(FileManager fileManager){
        fileManager.write("", false);
        for (int i = 0; i < Restaurant.user.size(); i++) {
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
        for (int i = 0; i < Restaurant.order.size(); i++) {
            fileManager.writeLine(Restaurant.order.get(i).toString(), true);
        }
        System.out.println("Have a nice time\nBye !!");
    }
}

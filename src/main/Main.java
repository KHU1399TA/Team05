package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import main.Enums.AccessLevel;
import main.Enums.ActionResult;
import main.Enums.OrderState;
import utils.FileManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.*;
import java.util.stream.Collectors;

public class Main {

    public static String username_for_order = "";
    public static int id_of_order = 1000;
    public static boolean repeatFlag = true;
    public static int repeatFlagCounter = 0;
    static Scanner scanner = new Scanner(System.in);
    static FileManager fileManager = new FileManager("src/resources/Food");

    public static String currentTime() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }

    public static void main(String[] args) throws ParseException {
       fillOrderArraylist( fillFoodArraylist(fileManager),fileManager);
        while (repeatFlag) {
            System.out.println();
            if (repeatFlagCounter != 0) {
                //System.out.println("------------------------------------Job is Done-------------------------------");
                System.out.println("-----------Login as current user or another user to continue-----------");
            }
            String result_of_login = User.login().toString();

            if (result_of_login.equals("MANAGER")) {
                boolean managerFlag = true;
                System.out.println("You are a Manager!");
                Manager mng = new Manager();

                User usr = new User() {
                };

                while (managerFlag) {

                    System.out.println("What do you want to do? (register/edit/remove)");
                    String ans = scanner.nextLine();

                    if (ans.equals("register")) {
                        System.out.println("Please enter FirstName");
                        usr.firstName = scanner.nextLine();
                        System.out.println("Please enter LastName");
                        usr.lastName = scanner.nextLine();
                        System.out.println("Please enter PhoneNumber");
                        usr.phoneNumber = scanner.nextLine();
                        System.out.println("Please enter UserName");
                        usr.userName = scanner.nextLine();
                        System.out.println("Please enter PassWord");
                        usr.password = scanner.nextLine();

                        System.out.println("Please enter accessLevel! (MANAGER/CASHIER/CHEF/DELIVERYMAN/CLIENT)");
                        ans = scanner.nextLine();
                        switch (ans) {
                            case "MANAGER":
                                usr.accessLevel = AccessLevel.MANAGER;
                                break;
                            case "CASHIER":
                                usr.accessLevel = AccessLevel.CASHIER;
                                break;
                            case "CHEF":
                                usr.accessLevel = AccessLevel.CHEF;
                                break;
                            case "DELIVERYMAN":
                                usr.accessLevel = AccessLevel.DELIVERYMAN;
                                break;
                            case "CLIENT":
                                usr.accessLevel = AccessLevel.CLIENT;
                                break;
                        }
                        usr.registrationDate = currentTime();
                        usr.lastLoginDate = currentTime();

                        if (mng.register(usr) == ActionResult.SUCCESS) {
                            System.out.println("User was added successfully");

                        } else {
                            System.out.println("Username already exist and was not added successfully. Try again!");
                        }

                    } else if (ans.equals("edit")) {
                        boolean repeat = true;
                        int repeatCounter = 1;
                        System.out.println("Enter the username to edit!");
                        String user_to_edit = scanner.nextLine().toString();

                        while (repeat) {

                            if (repeatCounter == 1) {

                                System.out.println("Which one do you want to edit for the user " + user_to_edit + "?");
                                System.out.println("name(enter 0) / familyName (enter 1) / phoneNumber (enter 2) / password (enter 4) / accessLevel (enter 5)");
                                int index = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Please Enter your change:");
                                String ans3 = scanner.nextLine().toString();
                                if (mng.edit(user_to_edit, index, ans3).toString().equals("SUCCESS")) {
                                    System.out.println("Your change is Done!");
                                    System.out.println("Do you want to edit another field for the user " + user_to_edit + "? y/n");
                                    String ans2 = scanner.nextLine().toString();
                                    if (ans2.equals("n")) {
                                        repeat = false;

                                    }
                                }
                                repeatCounter++;

                            } else {

                                System.out.println("Which one do you want to edit for the user " + user_to_edit + "?");
                                System.out.println(
                                        "name(enter 0) / familyName (enter 1) / phoneNumber (enter 2) / password (enter 4) / accessLevel (enter 5)");
                                int index = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Please Enter your change!");
                                String ans3 = scanner.nextLine().toString();
                                if (mng.edit(user_to_edit, index, ans3).toString().equals("SUCCESS")) {
                                    System.out.println("Your change is Done!");
                                    System.out.println("Do you want to edit another field for the user " + user_to_edit + "? y/n");
                                    String ans2 = scanner.nextLine().toString();
                                    if (ans2.equals("n")) {
                                        repeat = false;

                                    }
                                }
                                repeatCounter++;
                            }
                        }

                    } else if (ans.equals("remove")) {

                        boolean repeat = true;
                        int repeatCounter = 1;

                        while (repeat) {
                            System.out.println("Enter the username to remove!");
                            String user_to_remove = scanner.nextLine().toString();

                            if (repeatCounter == 1) {

                                if (mng.remove(user_to_remove).toString().equals("SUCCESS")) {
                                    System.out.println("The user removed successfully!");
                                    System.out.println("Do you want to remove another user? y/n");
                                    String ans2 = scanner.nextLine().toString();
                                    if (ans2.equals("n")) {
                                        repeat = false;

                                    }
                                }
                                repeatCounter++;
                            } else {
                                if (mng.remove(user_to_remove).toString().equals("SUCCESS")) {
                                    System.out.println("The user removed successfully!");
                                    System.out.println("Do you want to remove another user? y/n");
                                    String ans2 = scanner.nextLine().toString();
                                    if (ans2.equals("n")) {
                                        repeat = false;

                                    }
                                }
                                repeatCounter++;
                            }
                        }
                    }
                    System.out.println();
                    System.out.println("To logout current user please enter 1, else enter 2");
                    String r = scanner.nextLine().toString();
                    if (r.equals("1")) {
                        managerFlag = false;
                    }
                }

            } else if (result_of_login.equals("CHEF")) {
                int selectedNumber = mainMenuForChef();
                switch (selectedNumber) {
                    case 1 -> System.out.println(Chef.addFood(selectFood()));
                    case 2 -> {
                        System.out.print("id : ");
                        System.out.println(Chef.editFood(scanner.nextInt()));
                        scanner.nextLine();
                    }
                    case 3 -> {
                        System.out.print("id : ");
                        System.out.println(Chef.removeFood(scanner.nextInt()));
                        scanner.nextLine();
                    }
                    case 4 -> {Chef.foodState();
                    scanner.nextLine();
                    }
                    case 5 -> {
                        scanner.nextLine();
                        boolean ifanyorder = false;

                        for (int i = 0; i < Restaurant.order.size(); i++) {
                            if (Restaurant.order.get(i).state.equals("NOTREADY")) {
                                ifanyorder = true;

                            }
                        }

                        if (Restaurant.order.size() == 0 || !ifanyorder) {
                            System.out.println("Sorry, there is no order to cook!");
                            //ifanyorder = false;

                        } else {

                            System.out.println();
                            System.out.println("There are some order(s) to cook!");
                            System.out.println();
                            System.out.println("This is the list of order(s):");

//                            for (int i = 0; i < Restaurant.order.size(); i++) {
//
//                                BufferedReader reader;
//                                try {
//                                    reader = new BufferedReader(new FileReader("src/resources/Food"));
//                                    String line = reader.readLine();
//
//                                    while (line != null) {
//                                        String ins1 = line;
//                                        String[] ins2 = ins1.split(",");                                    ؟؟؟؟؟؟
//                                        String foodid = ins2[0];
//                                        String nameoffood = ins2[1];
//                                        int foodid2 = Integer.parseInt(foodid);
//
//                                        if (Restaurant.order.get(i).foodId == foodid2 && Restaurant.order.get(i).state.equals("NOTREADY")) {
//
//                                            System.out.println("Order id: " + Restaurant.order.get(i).id + ", Type of food: " + nameoffood);
//                                        }
//
//                                        line = reader.readLine();
//                                    }
//
//                                    reader.close();
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                            }
                        }

                        System.out.println();

                        if (ifanyorder) {
                            System.out.println("Do you want to cook the foods? y/n");
                            String ans = scanner.nextLine().toString();
                            if (ans.equals("y")) {
                                for (int i = 0; i < Restaurant.order.size(); i++) {
                                    Chef newChef = new Chef();

                                    if (Restaurant.order.get(i).state.equals("NOTREADY")) {
                                        if (newChef.cook(Restaurant.order.get(i).id).toString().equals("SUCCESS")) {
                                            System.out.println("Order(s) cooked");

                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            } else if (result_of_login.equals("CASHIER")) {
                System.out.println("You are a Cashier!");

                boolean ifanyorder = false;

                for (int i = 0; i < Restaurant.order.size(); i++) {
                    if (Restaurant.order.get(i).state.equals("COOKED")) {
                        ifanyorder = true;
                    }
                }

                if (Restaurant.order.size() == 0 || !ifanyorder) {
                    System.out.println("Sorry, there is no order to confirm!");
                    //ifanyorder = false;

                } else {

                    System.out.println();
                    System.out.println("There are some order(s) to confirm!");
                    System.out.println();
                    System.out.println("This is the list of order(s):");

                    for (int i = 0; i < Restaurant.order.size(); i++) {
                        if (Restaurant.order.get(i).state.toString().equals("COOKED")) {

                            System.out.println("Order id: " + Restaurant.order.get(i).id + ", Username: " +
                                    Restaurant.order.get(i).userName);
                        }
                    }
                }

                if (ifanyorder) {
                    System.out.println("Do you want to confirm the foods? y/n");
                    String ans = scanner.nextLine().toString();
                    if (ans.equals("y")) {
                        for (int i = 0; i < Restaurant.order.size(); i++) {

                            Cashier newCashier = new Cashier();
                            if (Restaurant.order.get(i).state.toString().equals("COOKED")) {
                                if (newCashier.confirmOrder(Restaurant.order.get(i).id).toString().equals("SUCCESS")) {
                                    System.out.println("Order(s) confirmed");

                                }
                            }
                        }
                    }
                }

            } else if (result_of_login.equals("DELIVERYMAN")) {
                System.out.println("You are a Deliveryman!");

                boolean ifanyorder = false;

                for (int i = 0; i < Restaurant.order.size(); i++) {
                    if (Restaurant.order.get(i).state.toString().equals("CONFIRMED")) {
                        ifanyorder = true;

                    }
                }
                if (Restaurant.order.size() == 0 || !ifanyorder) {
                    System.out.println("Sorry, there is no order to deliver!");
                    //ifanyorder = false;

                } else {
                    System.out.println();
                    System.out.println("There are some order(s) to deliver!");
                    System.out.println();
                    System.out.println("This is the list of order(s):");

                    for (int i = 0; i < Restaurant.order.size(); i++) {
                        if (Restaurant.order.get(i).state.toString().equals("CONFIRMED")) {

                            System.out.println("Order id: " + Restaurant.order.get(i).id + ", Username: " +
                                    Restaurant.order.get(i).userName + ", Address: " + Restaurant.order.get(i).address);
                        }
                    }
                }

                if (ifanyorder) {
                    System.out.println("Do you want to deliver the foods? y/n");
                    String ans = scanner.nextLine().toString();
                    if (ans.equals("y")) {
                        for (int i = 0; i < Restaurant.order.size(); i++) {
                            DeliverMan newDelMan = new DeliverMan();
                            if (Restaurant.order.get(i).state.toString().equals("CONFIRMED")) {
                                if (newDelMan.deliver(Restaurant.order.get(i).id).toString().equals("SUCCESS")) {
                                    System.out.println("Order(s) delivered");

                                }
                            }
                        }
                    }
                }

            } else if (result_of_login.equals("CLIENT")) {
                System.out.println("You are a Client!");
                System.out.println("Please enter 1 if you ordered before, otherwise enter 2 to make a new order!");
                String ans = scanner.nextLine();
                if (ans.equals("1")) {

                    System.out.println("Enter 1 to check your state of order,or enter 2 to revoke your order!");
                    ans = scanner.nextLine();
                    if (ans.equals("1")) {

                        System.out.println("Please enter your order id!");
                        int ans2 = scanner.nextInt();
                        scanner.nextLine();

                        if (Restaurant.order.size() == 0) {
                            System.out.println("Sorry! We didn't find such order with your username!");
                        }

                        for (int i = 0; i < Restaurant.order.size(); i++) {
                            if (Restaurant.order.get(i).userName.equals(username_for_order) && Restaurant.order.get(i).id == ans2) {
                                System.out.print("Your order state is: ");
                                System.out.println(Restaurant.order.get(i).state.toString());

                            } else {
                                System.out.println("Sorry! We didn't find such order with your username!");
                            }
                        }

                    } else {
                        System.out.println("Please enter your order id to revoke!");
                        int ans2 = scanner.nextInt();
                        scanner.nextLine();

                        Client newClient = new Client();
                        String str = newClient.revokeOrder(ans2).toString();
                        String str2 = "ORDER_ALREADY_COOKED";
                        if (str.equals(str2)) {
                            System.out.println("Sorry! Order Already Cooked or Does not Exist and Can not be Revoked");

                        } else {
                            System.out.println("Thank You, your order revoked!");
                        }
                        //revoke
                    }
                } else if (ans.equals("2")) {

                    System.out.println("------This is our MENU------");
                    System.out.println();
                    BufferedReader reader;
                    try {
                        reader = new BufferedReader(new FileReader(
                                "src/resources/Food"));
                        String line = reader.readLine();
                        while (line != null) {
                            // System.out.println(line);
                            // read next line

                            String ins1 = line;
                            String[] ins2 = ins1.split(",");
                            System.out.print(ins2[0]);
                            System.out.print(",");
                            System.out.println(ins2[1]);
                            line = reader.readLine();
                        }

                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Order newOrder = new Order();
                    Client newClient = new Client();
                    String str = newClient.makeOrder(newOrder).toString();
                    String str2 = "ORDER_ALREADY_EXIST";
                    if (str.equals(str2)) {
                        System.out.println("Sorry! Order Already Exist");
                    } else {
                        System.out.println("Thank You, We Received Your Order!");
                    }
                }
            }

            repeatFlagCounter = 1;
            System.out.println();
            System.out.println("------------------------------------Job is Done------------------------------------");
            System.out.println("-----------if you want to exit this program please enter 1, else enter 2-----------");
            String r = scanner.nextLine().toString();
            if (r.equals("1")) {
                repeatFlag = false;
                writingFileFood(fileManager);
            }
        }
    }

    private static int mainMenuForChef() {
        int selectedNumber;
        System.out.println("You are a Chef!");
        System.out.println("What do you want to do?");
        System.out.println(" 1) addFood");
        System.out.println(" 2) editFood");
        System.out.println(" 3) removeFood");
        System.out.println(" 4) change state");
        System.out.println(" 5) cook");
        selectedNumber = scanner.nextInt();
        return selectedNumber;
    }

    private static Food selectFood() {
        int id;
        String name;
        int ingredientsSize;
        boolean isAvailable;
        System.out.println("home>login>chef>add food\n");
        System.out.print("Id   : ");
        id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Name : ");
        name = scanner.nextLine();
        System.out.println("how many ingredients ?");
        ingredientsSize = scanner.nextInt();
        scanner.nextLine();
        String[] ingredients = new String[ingredientsSize];
        for (int i = 0; i < ingredientsSize; i++) {
            ingredients[i] = scanner.nextLine();
        }
        System.out.println("Is available now ?");
        System.out.println("1)Yes");
        System.out.println("2)No");
        int selectedNumber = scanner.nextInt();
        scanner.nextLine();
        isAvailable = selectedNumber == 1;
        return new Food(id, name, ingredients, isAvailable);
    }

    private static void writingFileFood(FileManager fileManager) {
        fileManager.write("", false);
        for (int i = 0; i < Restaurant.food.size(); i++) {
            fileManager.writeLine(Restaurant.food.get(i).toString(), true);
        } fileManager.writeLine("-".repeat(30), true);
        for (int i = 0; i < Restaurant.order.size(); i++) {
            fileManager.writeLine(Restaurant.order.get(i).toString(), true);
        }
    }

    private static int fillFoodArraylist(FileManager fileManager) {
        int x = 1;
        int id;
        String name;
        boolean isAvailable;
        if (fileManager.readLine(x) != null) {
            while (fileManager.readLine(x)!=null) {
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
        return x+1;
    }

    private static void fillOrderArraylist(int x, FileManager fileManager) throws ParseException {
        int id;
        String userName;
        int foodId;
        String state;
        String orderedAt;
        String address;
        if (fileManager.readLine(x) != null) {
            while (fileManager.readLine(x) != null) {
                id = Integer.parseInt(fileManager.readLine(x + 1).substring(12));
                userName = fileManager.readLine(x + 2).substring(12);
                foodId = Integer.parseInt(fileManager.readLine(x + 3).substring(12));
                if (fileManager.readLine(x + 4).substring(12).equals("MADE")) state ="MADE";
                else if (fileManager.readLine(x + 4).substring(12).equals("CONFIRMED")) state ="CONFIRMED";
                else if (fileManager.readLine(x + 4).substring(12).equals("COOKED")) state ="COOKED";
                else state ="DELIVERED";
                orderedAt =fileManager.readLine(x + 5).substring(12);
                address=fileManager.readLine(x+6).substring(12);
                x += 8;
                Order order = new Order();
                order.id=id;
                order.address = address;
                order.foodId = foodId;
                order.orderedAt = orderedAt;
                order.userName =userName;
                order.state = state;
                Restaurant.order.add(order);
            }
        }

    }






//baste shodane class e manine barname
}

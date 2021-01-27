package main;
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

    public static ArrayList<Order> currentOrders = new ArrayList<>();
    public static String username_for_order = "";
    public static int id_of_order = 1000;
    public static boolean repeatFlag = true;
    public static int repeatFlagCounter = 0;

    public static String currentTime() {

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;

    }

    public static AccessLevel login() {

        String result = "not_ok";
        String accessLevel = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your username and password");
        String username = scanner.nextLine();
        String password = scanner.nextLine();
        StringBuilder allString = new StringBuilder();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "src/resources/Usernames-and-Passwords"));
            String line = reader.readLine();

            while (line != null) {
                // System.out.println(line);
                // read next line

                String ins1 = line;
                String[] ins2 = ins1.split(",");
                String username2 = ins2[3];
                String password2 = ins2[4];

                if (username.equals(username2) && password.equals(password2)) {

                    username_for_order = username2;

                    result = "ok";
                    accessLevel = ins2[5];
                    //System.out.println("Success");
                    //System.out.println(manager2[7]);
                    ins2[7] = currentTime();
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

        if (result == "ok") {
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


    public static void main(String[] args) {

        while (repeatFlag) {

            System.out.println();
            if (repeatFlagCounter != 0) {
                //System.out.println("------------------------------------Job is Done-------------------------------");
                System.out.println("-----------Login as current user or another user to continue-----------");
            }

            Scanner scanner = new Scanner(System.in);
            String result_of_login = login().toString();

            if (result_of_login == "MANAGER") {
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
                                System.out.println(
                                        "name(enter 0) / familyName (enter 1) / phoneNumber (enter 2) / password (enter 4) / accessLevel (enter 5)");
                                int index = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("Please Enter your change:");
                                String ans3 = scanner.nextLine().toString();
                                if (mng.edit(user_to_edit, index, ans3).toString() == "SUCCESS") {
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
                                if (mng.edit(user_to_edit, index, ans3).toString() == "SUCCESS") {
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

                                if (mng.remove(user_to_remove).toString() == "SUCCESS") {
                                    System.out.println("The user removed successfully!");
                                    System.out.println("Do you want to remove another user? y/n");
                                    String ans2 = scanner.nextLine().toString();
                                    if (ans2.equals("n")) {
                                        repeat = false;

                                    }
                                }
                                repeatCounter++;
                            } else {
                                if (mng.remove(user_to_remove).toString() == "SUCCESS") {
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

            } else if (result_of_login == "CHEF") {
                System.out.println("You are a Chef!");

                System.out.println("What do you want to do? (cook/addFood/editFood/removeFood)");
                String ans = scanner.nextLine();

                if (ans.equals("addFood")) {

                } else if (ans.equals("editFood")) {

                } else if (ans.equals("removeFood")) {

                } else if (ans.equals("changeFoodState")) {

                } else if (ans.equals("cook")) {

                    boolean ifanyorder = false;

                    for (int i = 0; i < currentOrders.size(); i++) {
                        if (currentOrders.get(i).state == "NOTREADY") {
                            ifanyorder = true;

                        }
                    }

                    if (currentOrders.size() == 0 || ifanyorder == false) {
                        System.out.println("Sorry, there is no order to cook!");
                        //ifanyorder = false;

                    } else {

                        System.out.println();
                        System.out.println("There are some order(s) to cook!");
                        System.out.println();
                        System.out.println("This is the list of order(s):");

                        for (int i = 0; i < currentOrders.size(); i++) {

                            BufferedReader reader;
                            try {
                                reader = new BufferedReader(new FileReader(
                                        "src/resources/Food"));
                                String line = reader.readLine();

                                while (line != null) {
                                    String ins1 = line;
                                    String[] ins2 = ins1.split(",");
                                    String foodid = ins2[0];
                                    String nameoffood = ins2[1];
                                    int foodid2 = Integer.parseInt(foodid);

                                    if (currentOrders.get(i).foodId == foodid2 && currentOrders.get(i).state == "NOTREADY") {

                                        System.out.println("Order id: " + currentOrders.get(i).id + ", Type of food: " + nameoffood);
                                    }

                                    line = reader.readLine();
                                }

                                reader.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    System.out.println();

                    if (ifanyorder) {
                        System.out.println("Do you want to cook the foods? y/n");
                        ans = scanner.nextLine().toString();
                        if (ans.equals("y")) {
                            for (int i = 0; i < currentOrders.size(); i++) {
                                Chef newChef = new Chef();

                                if (currentOrders.get(i).state == "NOTREADY") {
                                    if (newChef.cook(currentOrders.get(i).id).toString() == "SUCCESS") {
                                        System.out.println("Order(s) cooked");

                                    }
                                }
                            }
                        }
                    }
                }

            } else if (result_of_login == "CASHIER") {
                System.out.println("You are a Cashier!");

                boolean ifanyorder = false;

                for (int i = 0; i < currentOrders.size(); i++) {
                    if (currentOrders.get(i).state == "COOKED") {
                        ifanyorder = true;
                    }
                }

                if (currentOrders.size() == 0 || ifanyorder == false) {
                    System.out.println("Sorry, there is no order to confirm!");
                    //ifanyorder = false;

                } else {

                    System.out.println();
                    System.out.println("There are some order(s) to confirm!");
                    System.out.println();
                    System.out.println("This is the list of order(s):");

                    for (int i = 0; i < currentOrders.size(); i++) {
                        if (currentOrders.get(i).state.toString() == "COOKED") {

                            System.out.println("Order id: " + currentOrders.get(i).id + ", Username: " +
                                    currentOrders.get(i).userName);
                        }
                    }
                }

                if (ifanyorder) {
                    System.out.println("Do you want to confirm the foods? y/n");
                    String ans = scanner.nextLine().toString();
                    if (ans.equals("y")) {
                        for (int i = 0; i < currentOrders.size(); i++) {

                            Cashier newCashier = new Cashier();
                            if (currentOrders.get(i).state.toString() == "COOKED") {
                                if (newCashier.confirmOrder(currentOrders.get(i).id).toString() == "SUCCESS") {
                                    System.out.println("Order(s) confirmed");

                                }
                            }
                        }
                    }
                }

            } else if (result_of_login == "DELIVERYMAN") {
                System.out.println("You are a Deliveryman!");

                boolean ifanyorder = false;

                for (int i = 0; i < currentOrders.size(); i++) {
                    if (currentOrders.get(i).state.toString() == "CONFIRMED") {
                        ifanyorder = true;

                    }
                }
                if (currentOrders.size() == 0 || ifanyorder == false) {
                    System.out.println("Sorry, there is no order to deliver!");
                    //ifanyorder = false;

                } else {
                    System.out.println();
                    System.out.println("There are some order(s) to deliver!");
                    System.out.println();
                    System.out.println("This is the list of order(s):");

                    for (int i = 0; i < currentOrders.size(); i++) {
                        if (currentOrders.get(i).state.toString() == "CONFIRMED") {

                            System.out.println("Order id: " + currentOrders.get(i).id + ", Username: " +
                                    currentOrders.get(i).userName + ", Address: " + currentOrders.get(i).address);
                        }
                    }
                }

                if (ifanyorder) {
                    System.out.println("Do you want to deliver the foods? y/n");
                    String ans = scanner.nextLine().toString();
                    if (ans.equals("y")) {
                        for (int i = 0; i < currentOrders.size(); i++) {
                            DeliverMan newDelMan = new DeliverMan();
                            if (currentOrders.get(i).state.toString() == "CONFIRMED") {
                                if (newDelMan.deliver(currentOrders.get(i).id).toString() == "SUCCESS") {
                                    System.out.println("Order(s) delivered");

                                }
                            }
                        }
                    }
                }

            } else if (result_of_login == "CLIENT") {
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

                        if (currentOrders.size() == 0) {
                            System.out.println("Sorry! We didn't find such order with your username!");
                        }

                        for (int i = 0; i < currentOrders.size(); i++) {
                            if (currentOrders.get(i).userName.equals(username_for_order) && currentOrders.get(i).id == ans2) {
                                System.out.print("Your order state is: ");
                                System.out.println(currentOrders.get(i).state.toString());

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
                        if (str == str2) {
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
                    if (str == str2) {
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
            }
//baste shodane while avale maine barname
        }
//baste shodane main barname
    }

//baste shodane class e manine barname
}

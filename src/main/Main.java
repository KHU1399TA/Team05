package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import main.Enums.AccessLevel;
import main.Enums.ActionResult;
import utils.FileManager;

import java.util.Date;
import java.util.Scanner;
import java.io.*;
import java.util.stream.Collectors;

public class Main {



    public static boolean repeatFlag = true;
    public static int repeatFlagCounter = 0;

    public static String currentTime(){

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;

    }


    public static AccessLevel login(){

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

                }

                else{
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

        if(result == "ok"){
            System.out.println("Login is successful :)");

            switch (accessLevel){
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

        }
        else {
            System.out.println("Login is not successful due to invalid username or password :(.");
            System.out.println("Please run the program again!");
        }
        return AccessLevel.NOACCESSLEVEL;
    }






    public static void main(String[] args) {

        while(repeatFlag) {

            System.out.println();
            if(repeatFlagCounter != 0){
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

                while(managerFlag) {

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

                    } else if (ans.equals("remove")) {

                    }
                    System.out.println();
                    System.out.println("To logout current user please enter 1, else enter 2");
                    String r = scanner.nextLine().toString();
                    if(r.equals("1") ) {
                        managerFlag = false;
                    }
                }





            }

            else if (result_of_login == "CHEF") {
                System.out.println("You are a Chef!");

                System.out.println("What do you want to do? (addFood/editFood/removeFood/changeFoodState/cook)");
                String ans = scanner.nextLine();

                if (ans.equals("addFood")) {

                } else if (ans.equals("editFood")) {

                } else if (ans.equals("removeFood")) {

                } else if (ans.equals("changeFoodState")) {

                } else if (ans.equals("cook")) {

                }


            } else if (result_of_login == "CASHIER") {
                System.out.println("You are a Cashier!");

            } else if (result_of_login == "DELIVERYMAN") {
                System.out.println("You are a Deliveryman!");

            } else if (result_of_login == "CLIENT") {
                System.out.println("You are a Client!");
                System.out.println("Please enter 1 if you ordered before, otherwise enter 2 to make a new order!");
                String ans = scanner.nextLine();
                if (ans.equals("1")) {

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

                    System.out.println();
                    System.out.println("Enter a \"food id\" from list above!");
                    String food_ID = scanner.nextLine().toString();


                    System.out.println("Please enter your current address for delivering your order!");
                    Client cli = new Client();
                    cli.address = scanner.nextLine().toString();

                }


            }


            repeatFlagCounter = 1;
            System.out.println();
            System.out.println("------------------------------------Job is Done------------------------------------");
            System.out.println("-----------if you want to exit this program please enter 1, else enter 2-----------");
            String r = scanner.nextLine().toString();
            if(r.equals("1") ) {
                repeatFlag = false;
            }

//baste shodane while avale maine barname
        }
//baste shodane main barname
    }

//baste shodane class e manine barname
}

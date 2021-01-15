package main;

import main.Enums.AccessLevel;

import java.util.Date;
import java.util.Scanner;

import static main.User.login;

public class Main {
    private static Scanner input;

    public static void main(String[] args) {
        String userName;
        String password;
        input=new Scanner(System.in);
        boolean mainPage=true;
        while (mainPage){
            System.out.println("Enter a number ");
            System.out.println("1) Register(Clients)\n2) Login\n3) exit");
            int num=input.nextInt();
            switch (num){
                case 1 :

                case 2 :
                    System.out.println("Login ");
                    System.out.print("username : ");
                    userName=input.nextLine();
                    System.out.print("password :");
                    password=input.nextLine();
                    Date registerDate=new Date();
                    Manager currentUser=new Manager("jan!","joon!", AccessLevel.MANAGER,registerDate,registerDate,"hoom","what?","123");
                    System.out.println(login(userName,password,currentUser));


                case 3 :
                    mainPage=false;
                    System.out.println("Have a nice time\nBye!");
                    return;
            }

        }
    }
}


















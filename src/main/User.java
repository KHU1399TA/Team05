package main;

import main.Enums.AccessLevel;
import main.Enums.ActionResult;

import java.util.Date;

abstract class User{
    String firstName;
    String lastName;
    String phoneNumber;
    String userName;
    String password;
    AccessLevel accessLevel;
    String registrationDate; //edited from date to string
    String lastLoginDate; //edited from date to string




    ActionResult login(String userName, String password) {
        return ActionResult.SUCCESS;
    }
}

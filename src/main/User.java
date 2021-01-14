package main;

import main.Enums.AccessLevel;
import main.Enums.ActionResult;
import main.Person;

import java.util.Date;

abstract class User implements Person {
    String userName;
    String password;
    AccessLevel accessLevel;
    Date registrationDate;
    Date lastLoginDate;

    ActionResult login(String userName, String password) {
        return ActionResult.SUCCESS;
    }
}

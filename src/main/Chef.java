package main;

import main.Enums.AccessLevel;
import main.Enums.ActionResult;

import java.util.Date;

class Chef extends User {
    ActionResult addFood(Food food) {
        return ActionResult.SUCCESS;
    }

    ActionResult editFood(int id) {
        return ActionResult.SUCCESS;
    }

    ActionResult removeFood(int id) {
        return ActionResult.SUCCESS;
    }

    ActionResult changeFoodState(int id, boolean isAvailable) {
        return ActionResult.SUCCESS;
    }

    ActionResult cook(int id) {
        return ActionResult.SUCCESS;
    }

    public Chef(String userName, String password, AccessLevel accessLevel, Date registrationDate, Date lastLoginDate, String firstName, String lastName, String phoneNumber) {
        super(userName, password, accessLevel, registrationDate, lastLoginDate, firstName, lastName, phoneNumber);
    }
}
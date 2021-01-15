package main;

import main.Enums.AccessLevel;
import main.Enums.ActionResult;

import java.util.Date;

class DeliverMan extends User {
    ActionResult deliver(int id) {
        return ActionResult.SUCCESS;
    }

    public DeliverMan(String userName, String password, AccessLevel accessLevel, Date registrationDate, Date lastLoginDate) {
        super(userName, password, accessLevel, registrationDate, lastLoginDate);
    }
}


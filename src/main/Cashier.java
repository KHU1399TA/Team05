package main;
import main.Enums.AccessLevel;
import main.Enums.ActionResult;

import java.util.Date;

class Cashier extends User {
        ActionResult confirmOrder(int id) {
            return ActionResult.SUCCESS;
        }

    public Cashier(String userName, String password, AccessLevel accessLevel, Date registrationDate, Date lastLoginDate) {
        super(userName, password, accessLevel, registrationDate, lastLoginDate);
    }
}


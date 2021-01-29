package main;

import main.Enums.AccessLevel;
import main.Enums.ActionResult;
import main.Enums.OrderState;

import java.util.Date;

class Cashier extends User {

    static ActionResult confirmOrder(int id) {
          //to do miss
    }

    public Cashier(String userName, String password, AccessLevel accessLevel, Date registrationDate, Date lastLoginDate, String firstName, String lastName, String phoneNumber) {
        super(userName, password, accessLevel, registrationDate, lastLoginDate, firstName, lastName, phoneNumber);
    }
}


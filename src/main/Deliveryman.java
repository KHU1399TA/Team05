package main;

import main.Enums.AccessLevel;
import main.Enums.ActionResult;
import main.Enums.OrderState;

import java.util.Date;

class Deliveryman extends User {
    static ActionResult deliver(int id) {
       //to do miss
    }

    public Deliveryman(String userName, String password, AccessLevel accessLevel, Date registrationDate, Date lastLoginDate, String firstName, String lastName, String phoneNumber) {
        super(userName, password, accessLevel, registrationDate, lastLoginDate, firstName, lastName, phoneNumber);
    }
}


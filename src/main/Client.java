package main;

import main.Enums.AccessLevel;
import main.Enums.ActionResult;

import java.util.Date;

public class Client extends User {
    String address;
    ActionResult makeOrder(Order order){
        return ActionResult.SUCCESS;
    }
    ActionResult revokeOrder(int id){
        return ActionResult.SUCCESS;
    }

    public Client(String userName, String password, AccessLevel accessLevel, Date registrationDate, Date lastLoginDate, String firstName, String lastName, String phoneNumber, String address) {
        super(userName, password, accessLevel, registrationDate, lastLoginDate, firstName, lastName, phoneNumber);
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client:\n" +
                " userName        =" + userName + '\n' +
                " password        =" + password + '\n' +
                " accessLevel     =" + accessLevel +"\n"+
                " registrationDate=" + registrationDate +"\n"+
                " lastLoginDate   =" + lastLoginDate +"\n"+
                " firstName       =" + firstName + '\n' +
                " lastName        =" + lastName + '\n' +
                " phoneNumber     =" + phoneNumber + '\n' +
                " address         =" + address+"\n"
                ;
    }
}


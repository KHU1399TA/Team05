package main;

import main.Enums.*;

import static main.Restaurant.user;

import java.util.Date;

public abstract class User {
    String userName;
     String password;
    AccessLevel accessLevel;
    Date registrationDate;
    Date lastLoginDate;
    String firstName;
    String lastName;
    String phoneNumber;


    static LoginResult login(String userName, String password) {
        for (int i = 0; i < user.size(); i++) {
            if (user.get(i).userName.equals(userName))
                if (user.get(i).password.equals(password)) {

                    LoginResult loginResult = new LoginResult();
                    loginResult.actionResult = ActionResult.SUCCESS;
                    loginResult.user = user.get(i);
                    return loginResult;
                }
        }
        LoginResult loginResult = new LoginResult();
        loginResult.actionResult = ActionResult.INVALID_PASSWORD_OR_USERNAME;
        return loginResult;
    }

    public User(String userName, String password, AccessLevel accessLevel, Date registrationDate, Date lastLoginDate, String firstName, String lastName, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.accessLevel = accessLevel;
        this.registrationDate = registrationDate;
        this.lastLoginDate = lastLoginDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "User :\n" +
                " userName        =" + userName + '\n' +
                " password        =" + password + '\n' +
                " accessLevel     =" + accessLevel +"\n"+
                " registrationDate=" + registrationDate +"\n"+
                " lastLoginDate   =" + lastLoginDate +"\n"+
                " firstName       =" + firstName + '\n' +
                " lastName        =" + lastName + '\n' +
                " phoneNumber     =" + phoneNumber + '\n'
                ;
    }
}

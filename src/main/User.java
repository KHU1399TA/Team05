package main;

import main.Enums.*;

import static main.Restaurant.user;

import java.util.Date;

abstract class User {
    String userName;
    private String password;
    AccessLevel accessLevel;
    Date registrationDate;
    Date lastLoginDate;
    String firstName;
    String lastName;
    String phoneNumber;


    static ActionResult login(String userName, String password ,User users) {
        for (int i = 0; i < user.size(); i++) {
            if (user.get(i).userName.equals(userName))
                if (user.get(i).password.equals(password)){
                   users.accessLevel= user.get(i).accessLevel;
                    return ActionResult.SUCCESS;
                }
        }
        return ActionResult.INVALID_PASSWORD_OR_USERNAME;
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
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", accessLevel=" + accessLevel +
                ", registrationDate=" + registrationDate +
                ", lastLoginDate=" + lastLoginDate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

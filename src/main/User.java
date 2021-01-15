package main;

import main.Enums.AccessLevel;
import main.Enums.ActionResult;

import java.util.ArrayList;
import java.util.Date;


abstract class User implements Person {
    Restaurant restaurant=new Restaurant();
    String userName;
   private String password;
    AccessLevel accessLevel;
    Date registrationDate;
    Date lastLoginDate;


    ActionResult login(String userName, String password, AccessLevel accessLevel) {

        for (int i=0;i<restaurant.user.size();i++){
            if (restaurant.user.get(i).accessLevel==accessLevel) {
                if (!restaurant.user.get(i).userName.equals(userName)) return ActionResult.INVALID_USERNAME;
                if (!restaurant.user.get(i).password.equals(password)) return ActionResult.INVALID_PASSWORD;
            }
        }
       return ActionResult.SUCCESS;
    }

    public User( String userName, String password, AccessLevel accessLevel, Date registrationDate, Date lastLoginDate) {

        this.userName = userName;
        this.password = password;
        this.accessLevel = accessLevel;
        this.registrationDate = registrationDate;
        this.lastLoginDate = lastLoginDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "restaurant=" + restaurant +
                ", userName='" + userName + '\'' +
                ", accessLevel=" + accessLevel +
                ", registrationDate=" + registrationDate +
                ", lastLoginDate=" + lastLoginDate +
                '}';
    }

}

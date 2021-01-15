package main;
import main.Enums.AccessLevel;
import main.Enums.ActionResult;
import java.util.Date;
import java.util.Scanner;
class Manager extends User {
    private static Scanner input = new Scanner(System.in);

    ActionResult register(User user) {
        if (user.userName.contains(" ")) return ActionResult.INVALID_USERNAME;
        for (int i = 0; i < Restaurant.user.size(); i++)
            if (Restaurant.user.get(i).equals(user.userName)) return ActionResult.USERNAME_ALREADY_EXIST;
        Restaurant.user.add(user);
        return ActionResult.SUCCESS;
    }

    ActionResult edit(String userName) {
        return ActionResult.SUCCESS;
    }

    ActionResult remove(String userName) {
        return ActionResult.SUCCESS;
    }

    public Manager(String userName, String password, AccessLevel accessLevel, Date registrationDate, Date lastLoginDate, String firstName, String lastName, String phoneNumber) {
        super(userName, password, accessLevel, registrationDate, lastLoginDate, firstName, lastName, phoneNumber);
    }
}

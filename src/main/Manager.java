package main;
import main.Enums.AccessLevel;
import main.Enums.ActionResult;
import java.util.Date;
import java.util.Scanner;

class Manager extends User {
    private static Scanner input = new Scanner(System.in);
    Restaurant restaurant = new Restaurant();
    
    ActionResult register(User user) {
        if (user.userName.contains(" ")) return ActionResult.INVALID_USERNAME;
        for (int i = 0; i < restaurant.user.size(); i++)
            if (restaurant.user.get(i).equals(user.userName)) return ActionResult.USERNAME_ALREADY_EXIST;
        restaurant.user.add(user);
        return ActionResult.SUCCESS;
    }

    ActionResult edit(String userName) {
        return ActionResult.SUCCESS;
    }

    ActionResult remove(String userName) {
        return ActionResult.SUCCESS;
    }

    public Manager( String userName, String password, AccessLevel accessLevel, Date registrationDate, Date lastLoginDate) {
        super( userName, password, accessLevel, registrationDate, lastLoginDate);
    }
}

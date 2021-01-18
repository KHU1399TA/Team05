package main;
import main.Enums.ActionResult;

import java.util.ArrayList;

public class Restaurant {
   static ArrayList<User>  user  =new ArrayList<>();
    ArrayList<Food>  food  =new ArrayList<>();
    ArrayList<Order> order =new ArrayList<>();

    ActionResult registerForClients(User user){
        if (user.userName.contains(" ")) return ActionResult.INVALID_USERNAME;
        for (int i = 0; i < Restaurant.user.size(); i++)
            if (Restaurant.user.get(i).userName.equals(user.userName)) return ActionResult.USERNAME_ALREADY_EXIST;
        Restaurant.user.add(user);
        return ActionResult.SUCCESS;
    }
    @Override
    public String toString() {
        return "Restaurant{" +
                "user=" + user +
                ", food=" + food +
                ", order=" + order +
                '}';
    }
}

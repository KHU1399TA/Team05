package main;
import java.util.ArrayList;

public class Restaurant {
   static ArrayList<User>  user  =new ArrayList<>();
    ArrayList<Food>  food  =new ArrayList<>();
    ArrayList<Order> order =new ArrayList<>();

    @Override
    public String toString() {
        return "Restaurant{" +
                "user=" + user +
                ", food=" + food +
                ", order=" + order +
                '}';
    }
}

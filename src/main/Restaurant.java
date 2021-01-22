package main;

import main.Enums.ActionResult;

import java.util.ArrayList;

public class Restaurant {
    static ArrayList<User> user = new ArrayList<>();
    static ArrayList<Food> food = new ArrayList<>();
    static ArrayList<Order> order = new ArrayList<>();
    static ArrayList<Client> client = new ArrayList<>();

    ActionResult registerForClients(Client client) {
        if (client.userName.contains(" ")) return ActionResult.INVALID_USERNAME;
        for (int i = 0; i < Restaurant.client.size(); i++)
            if (Restaurant.client.get(i).userName.equals(client.userName)) return ActionResult.USERNAME_ALREADY_EXIST;
        Restaurant.client.add(client);
        return ActionResult.SUCCESS;
    }

    @Override
    public String toString() {
        return "Restaurant :\n" +
                " user    : \n" + user +
                "\n food  : \n" + food +
                "\n order : \n" + order
                ;
    }
}

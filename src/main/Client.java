package main;

import main.Enums.AccessLevel;
import main.Enums.ActionResult;

import java.util.Date;

public class Client extends User {
    String address;

    static ActionResult makeOrder(Order order) {
        for (int i = 0; i < Restaurant.order.size(); i++) {
            if (Restaurant.order.get(i).id == order.id) return ActionResult.ORDER_ALREADY_EXIST;
        }
        for (int i = 0; i < Restaurant.client.size(); i++) {
            if (Restaurant.client.get(i).userName.equals(order.userName)) {
                for (int j = 0; j < Restaurant.food.size(); j++) {
                    if (Restaurant.food.get(j).id == order.foodId) {
                        if (!Restaurant.food.get(j).isAvailable) return ActionResult.FOOD_IS_NOT_AVAILABLE;
                        Restaurant.order.add(order);
                        return ActionResult.SUCCESS;
                    }
                }
                return ActionResult.FOOD_NOT_FOUND;
            }

        }
        return ActionResult.USERNAME_NOT_FOUND;
    }

    static ActionResult revokeOrder(int id, String userName) {
        for (int i = 0; i < Restaurant.order.size(); i++) {
            if (Restaurant.order.get(i).id == id) {
                if (Restaurant.order.get(i).userName.equals(userName)) {
                    Restaurant.order.remove(i);
                    return ActionResult.ORDER_REVOKED;
                } else return ActionResult.USERNAME_NOT_FOUND;

            }
        }
        return ActionResult.ORDER_NOT_FOUND;
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
                " accessLevel     =" + accessLevel + "\n" +
                " registrationDate=" + registrationDate + "\n" +
                " lastLoginDate   =" + lastLoginDate + "\n" +
                " firstName       =" + firstName + '\n' +
                " lastName        =" + lastName + '\n' +
                " phoneNumber     =" + phoneNumber + '\n' +
                " address         =" + address + "\n"
                ;
    }
}


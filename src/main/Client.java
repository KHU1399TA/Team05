package main;

import main.Enums.ActionResult;
import java.util.Scanner;

public class Client extends Main {
    // String address = ""; //added to enum Order
    ActionResult makeOrder(main.Order order) {

        System.out.println();
        System.out.println("Enter a \"food id\" from list above!");
        Scanner scanner = new Scanner(System.in);
        int food_ID = scanner.nextInt();
        scanner.nextLine(); //nokte dare in line
        System.out.println("Please enter your current address for delivering your order!");
        String orderAddress = scanner.nextLine().toString();

        order.address = orderAddress;
        order.foodId = food_ID;
        order.orderedAt = currentTime();
        order.userName = username_for_order;
        order.state = "NOTREADY";
        boolean flag = true;

        for (int j = 0; j < Restaurant.order.size(); j++) {
            if (Restaurant.order.get(j).userName.equals(username_for_order) && Restaurant.order.get(j).foodId == food_ID
                    && Restaurant.order.get(j).state.equals("NOTREADY")) {
                flag = false;
            }
        }

        if (flag) {
            order.id = id_of_order;
            System.out.println("Your order id is: " + id_of_order);
            id_of_order = id_of_order + 1;
            Restaurant.order.add(order);

            return ActionResult.SUCCESS;
        } else {
            return ActionResult.ORDER_ALREADY_EXIST;
        }
    }

    ActionResult revokeOrder(int id) {

        for (int j = 0; j < Restaurant.order.size(); j++) {

            if (Restaurant.order.get(j).id == id && Restaurant.order.get(j).state.toString().equals("NOTREADY")) {
                Restaurant.order.remove(j);
                return ActionResult.SUCCESS;

            } else {
                return ActionResult.ORDER_ALREADY_COOKED;
            }
        }
        return ActionResult.ORDER_ALREADY_COOKED;

    }
}



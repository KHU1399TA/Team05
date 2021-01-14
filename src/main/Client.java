package main;

import main.Enums.ActionResult;

public class Client {
    String address;
    ActionResult makeOrder(Order order){
        return ActionResult.SUCCESS;
    }
    ActionResult revokeOrder(int id){
        return ActionResult.SUCCESS;
    }
}


package main;

import main.Enums.ActionResult;

class Chef extends Main { //changed from "extends main.User" to extend Main
    ActionResult addFood(Food food) {
        return ActionResult.SUCCESS;
    }

    ActionResult editFood(int id) {
        return ActionResult.SUCCESS;
    }

    ActionResult removeFood(int id) {
        return ActionResult.SUCCESS;
    }

    ActionResult changeFoodState(int id, boolean isAvailable) {
        return ActionResult.SUCCESS;
    }

    ActionResult cook(int id) {

        for (int i = 0; i < currentOrders.size(); i++){
            if(currentOrders.get(i).id == id){
                currentOrders.get(i).state = "COOKED";
            }
        }

        return ActionResult.SUCCESS;
    }
}



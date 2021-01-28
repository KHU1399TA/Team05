package main;

import main.Enums.ActionResult;

class DeliverMan extends Main {
    ActionResult deliver(int id) {
        for (int i = 0; i < Restaurant.order.size(); i++){
            if(Restaurant.order.get(i).id == id){
               Restaurant.order.get(i).state = "DELIVERED";
            }
        }

        return ActionResult.SUCCESS;
    }
}


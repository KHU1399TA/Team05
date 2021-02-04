package main;

import main.Enums.ActionResult;

class DeliverMan extends Main {
    ActionResult deliver(int id) {
        for (int i = 0; i < currentOrders.size(); i++){
            if(currentOrders.get(i).id == id){
                currentOrders.get(i).state = "DELIVERED";
            }
        }

        return ActionResult.SUCCESS;
    }
}


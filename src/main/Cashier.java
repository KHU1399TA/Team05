package main;
import main.Enums.ActionResult;

class Cashier extends Main {
        ActionResult confirmOrder(int id) {

            for (int i = 0; i < currentOrders.size(); i++){
                if(currentOrders.get(i).id == id){
                    currentOrders.get(i).state = "CONFIRMED";
                }
            }
            return ActionResult.SUCCESS;
        }
    }


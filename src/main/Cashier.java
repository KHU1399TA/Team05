package main;
import main.Enums.ActionResult;

class Cashier extends Main {
        ActionResult confirmOrder(int id) {

            for (int i = 0; i < Restaurant.order.size(); i++){
                if(Restaurant.order.get(i).id == id){
                    Restaurant.order.get(i).state = "CONFIRMED";
                }
            }
            return ActionResult.SUCCESS;
        }
    }


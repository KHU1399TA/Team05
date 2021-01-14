package main;
import main.Enums.ActionResult;

class Cashier extends User {
        ActionResult confirmOrder(int id) {
            return ActionResult.SUCCESS;
        }
    }


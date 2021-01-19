package main;
import main.Enums.ActionResult;

class Cashier extends main.User {
        ActionResult confirmOrder(int id) {
            return ActionResult.SUCCESS;
        }
    }


package main;

import main.Enums.ActionResult;

class Chef extends main.User {
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
        return ActionResult.SUCCESS;
    }
}



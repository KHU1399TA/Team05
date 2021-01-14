package main;

import main.Enums.ActionResult;

class Manager extends User {
    ActionResult register(User user) {
        return ActionResult.SUCCESS;
    }

    ActionResult edit(String userName) {
        return ActionResult.SUCCESS;
    }

    ActionResult remove(String userName) {
        return ActionResult.SUCCESS;
    }
}

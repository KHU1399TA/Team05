package main.Enums;

public enum ActionResult {
    SUCCESS("SUCCESS"),
    INVALID_USERNAME("INVALID_USERNAME"),
    INVALID_PASSWORD_OR_USERNAME("INVALID_PASSWORD_OR_USERNAME"),
    USERNAME_NOT_FOUND("USERNAME_NOT_FOUND"),
    USERNAME_ALREADY_EXIST("USERNAME_ALREADY_EXIST"),
    FOOD_NOT_FOUND("FOOD_NOT_FOUND"),
    ID_OR_FOOD_ALREADY_EXIST("ID_OR_FOOD_ALREADY_EXIST"),
    ORDER_NOT_FOUND("ORDER_NOT_FOUND"),
    ORDER_ALREADY_EXIST("ORDER_ALREADY_EXIST"),
    ORDER_REVOKED("Order is revoked successfully ..."),
    FOOD_IS_NOT_AVAILABLE("FOOD_IS_NOT_AVAILABLE"),
    ORDER_CONFIRMED("ORDER_CONFIRMED"),
    ID_ALREADY_EXIST("ID_ALREADY_EXIST");
    public String action;

    ActionResult(String action) {
        this.action = action;
    }

    public String toString() {
        return action;
    }

}

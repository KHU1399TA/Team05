package main.Enums;

public enum ActionResult {
    SUCCESS("SUCCESS"),
    INVALID_USERNAME("INVALID_USERNAME"),
    INVALID_PASSWORD("INVALID_PASSWORD"),
    INVALID_USER_OR_PASS("INVALID_USERNAME_OR_PASSWORD"),
    USERNAME_NOT_FOUND("USERNAME_NOT_FOUND"),
    USERNAME_ALREADY_EXIST("USERNAME_ALREADY_EXIST"),
    FOOD_NOT_FOUND("FOOD_NOT_FOUND"),
    FOOD_ALREADY_EXIST("FOOD_ALREADY_EXIST"),
    ORDER_NOT_FOUND("ORDER_NOT_FOUND"),
    ORDER_ALREADY_EXIST("ORDER_ALREADY_EXIST"),
    UNKNOWN_ERROR("UNKNOWN_ERROR");
    public String action;

    ActionResult(String action) {
        this.action = action;
    }

    public String toString() {
        return action;
    }

}

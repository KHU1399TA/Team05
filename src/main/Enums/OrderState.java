package main.Enums;

public enum OrderState {
    MADE("MADE"),
    CONFIRMED("CONFIRMED"),
    COOKED("COOKED"),
    DELIVERED("DELIVERED"),
    NOTREADY("NOTREADY");
    public String state;

    OrderState(String state) {
        this.state = state;
    }

    public String toString() {
        return state;
    }
}

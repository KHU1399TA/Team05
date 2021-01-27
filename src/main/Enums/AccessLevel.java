package main.Enums;

public enum AccessLevel {
    MANAGER("MANAGER"),
    CASHIER("CASHIER"),
    CHEF("CHEF"),
    DELIVERYMAN("DELIVERYMAN"),
    CLIENT("CLIENT"),
    NOACCESSLEVEL("NOACCESSLEVEL");

    public String level;

    AccessLevel(String level) {
        this.level = level;
    }

    public String toString() {
        return level;
    }
}

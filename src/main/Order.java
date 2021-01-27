package main;

import main.Enums.OrderState;

import java.util.Date;

public class Order {
    int id;
    String userName;
    int foodId;
    OrderState state;
    Date orderedAt;

    public Order(int id, String userName, int foodId, OrderState state, Date orderedAt) {
        this.id = id;
        this.userName = userName;
        this.foodId = foodId;
        this.state = state;
        this.orderedAt = orderedAt;
    }

    @Override
    public String toString() {
        return  "Order\n" +
                " id        =" + id       +"\n"+
                " userName  =" + userName +"\n"+
                " foodId    =" + foodId   +"\n"+
                " state     =" + state    +"\n"+
                " orderedAt =" + orderedAt+"\n"
                ;
    }
}

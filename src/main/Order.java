package main;

import main.Enums.OrderState;

import java.util.Date;

public class Order {
    int id;
    String userName;
    int foodId;
    OrderState state;
    Date orderedAt;
    String address;

    public Order(int id, String userName, int foodId, OrderState state, Date orderedAt,String address) {
        this.id = id;
        this.userName = userName;
        this.foodId = foodId;
        this.state = state;
        this.orderedAt = orderedAt;
        this.address=address;
    }

    @Override
    public String toString() {
        return  "Order\n" +
                " id        =" + id       +"\n"+
                " userName  =" + userName +"\n"+
                " foodId    =" + foodId   +"\n"+
                " state     =" + state    +"\n"+
                " orderedAt =" + orderedAt+"\n"+
                " address   =" + address  +"\n"
                ;
    }

    public String toStringDeliveryman(){
        return "Order\n" +
                " id        =" + id       +"\n"+
                " foodId    =" + foodId   +"\n"+
                " orderedAt =" + orderedAt+"\n"+
                " address   =" + address  +"\n"
                ;
    }

    public String toStringForEmployees(){
        return "Order\n" +
                " id        =" + id       +"\n"+
                " foodId    =" + foodId   +"\n"+
                " orderedAt =" + orderedAt+"\n"
                ;
    }


}

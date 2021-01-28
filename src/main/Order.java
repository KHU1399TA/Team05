package main;

public class Order {
    int id;
    String userName;
    int foodId;
    String state; //changed from OrderState to string
    String orderedAt; //changed from date to string
    String address; //i added this one



    @Override
    public String toString() {
        return "Order\n" +
                " id        =" + id +"\n"+
                " userName  =" + userName + "\n" +
                " foodId    =" + foodId + "\n"+
                " state     =" + state + "\n" +
                " orderedAt =" + orderedAt + "\n" +
                " address   =" + address +  "\n"
                ;
    }
}

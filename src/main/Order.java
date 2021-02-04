package main;

import main.Enums.OrderState;

import java.util.Date;

public class Order {
    int id;
    String userName;
    int foodId;
    String state; //changed from OrderState to string
    String orderedAt; //changed from date to string
    String address; //i added this one
}

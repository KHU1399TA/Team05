package main;

import main.Enums.AccessLevel;
import main.Enums.ActionResult;
import main.Enums.OrderState;

import java.util.Date;

class Deliveryman extends User {
    static ActionResult deliver(int id) {
        for (int i=0;i<Restaurant.order.size();i++){
            if (Restaurant.order.get(i).id==id)
                if (Restaurant.order.get(i).state== OrderState.COOKED){
                    Restaurant.order.get(i).state=OrderState.DELIVERED;
                    return ActionResult.SUCCESS;
                }
        }
        return ActionResult.ORDER_NOT_FOUND;
    }

    public Deliveryman(String userName, String password, AccessLevel accessLevel, Date registrationDate, Date lastLoginDate, String firstName, String lastName, String phoneNumber) {
        super(userName, password, accessLevel, registrationDate, lastLoginDate, firstName, lastName, phoneNumber);
    }
}


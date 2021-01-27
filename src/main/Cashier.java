package main;

import main.Enums.AccessLevel;
import main.Enums.ActionResult;
import main.Enums.OrderState;

import java.util.Date;

class Cashier extends User {

    static ActionResult confirmOrder(int id) {
            for (int i=0;i<Restaurant.order.size();i++){
                if (Restaurant.order.get(i).id==id)
                    if (Restaurant.order.get(i).state== OrderState.MADE){
                        Restaurant.order.get(i).state=OrderState.CONFIRMED;
                        return ActionResult.ORDER_CONFIRMED;
                    }
            }
            return ActionResult.ORDER_NOT_FOUND;
    }

    public Cashier(String userName, String password, AccessLevel accessLevel, Date registrationDate, Date lastLoginDate, String firstName, String lastName, String phoneNumber) {
        super(userName, password, accessLevel, registrationDate, lastLoginDate, firstName, lastName, phoneNumber);
    }
}


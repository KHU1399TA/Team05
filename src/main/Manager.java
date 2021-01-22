package main;
import main.Enums.AccessLevel;
import main.Enums.ActionResult;
import java.util.Date;
import java.util.Scanner;

class Manager extends User {
    private static Scanner input = new Scanner(System.in);

    ActionResult register(User user) {
        if (user.userName.contains(" ")) return ActionResult.INVALID_USERNAME;
        for (int i = 0; i < Restaurant.user.size(); i++)
            if (Restaurant.user.get(i).userName.equals(user.userName)) return ActionResult.USERNAME_ALREADY_EXIST;
        Restaurant.user.add(user);
        return ActionResult.SUCCESS;
    }

    ActionResult edit(String userName) {
        for (int i = 0; i < Restaurant.user.size(); i++) {
            if (Restaurant.user.get(i).userName.equals(userName)) {
                System.out.println(Restaurant.user.get(i) + "\n");
                System.out.println("which field ? \nchoose a number ");
                System.out.println("1) firstname\t2) lastname\t3) phone number\t4) username\t5) password\t6) accesslevel ");
                int selectedNumber = input.nextInt();
                input.nextLine();
                switch (selectedNumber) {
                    case 1:
                        System.out.print("edited firstname    : ");
                        Restaurant.user.get(i).firstName = input.nextLine();
                        return ActionResult.SUCCESS;

                    case 2:
                        System.out.print("edited lastname     : ");
                        Restaurant.user.get(i).lastName = input.nextLine();
                        break;
                    case 3:
                        System.out.print("edited phone number : ");
                        Restaurant.user.get(i).phoneNumber = input.nextLine();
                        return ActionResult.SUCCESS;
                    case 4:
                        System.out.println("!!!!   username should not contain space   !!!!!");
                        System.out.print("edited username     : ");
                        String editedUserName = input.nextLine();
                        if (editedUserName.contains(" ")) return ActionResult.INVALID_USERNAME;
                        for (int j = 0; j < Restaurant.user.size(); j++) {
                            if (Restaurant.user.get(i).userName.equals(editedUserName))
                                return ActionResult.USERNAME_ALREADY_EXIST;
                        }
                        Restaurant.user.get(i).userName = editedUserName;
                        return ActionResult.SUCCESS;
                    case 5:
                        System.out.print("edited password     : ");
                        Restaurant.user.get(i).password = input.nextLine();
                        return ActionResult.SUCCESS;
                    case 6:
                        System.out.println("choose the accessevel ");
                        System.out.println(" 1) Manager\n 2) Chef\n 3) Cashier\n 4) DeliverYman");
                        selectedNumber = input.nextInt();
                        switch (selectedNumber) {
                            default -> Restaurant.user.get(i).accessLevel = AccessLevel.MANAGER;
                            case 2 -> Restaurant.user.get(i).accessLevel = AccessLevel.CHEF;
                            case 3 -> Restaurant.user.get(i).accessLevel = AccessLevel.CASHIER;
                            case 4 -> Restaurant.user.get(i).accessLevel = AccessLevel.DELIVERYMAN;
                        }
                        return ActionResult.SUCCESS;
                }
                break;
            }
        }
        return ActionResult.USERNAME_NOT_FOUND;
    }

    ActionResult remove(String userName) {
        for (int i=0;i<Restaurant.user.size();i++){
            if (Restaurant.user.get(i).userName.equals(userName)){
                Restaurant.user.remove(i);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.USERNAME_NOT_FOUND;
    }

    public Manager(String userName, String password, AccessLevel accessLevel, Date registrationDate, Date lastLoginDate, String firstName, String lastName, String phoneNumber) {
        super(userName, password, accessLevel, registrationDate, lastLoginDate, firstName, lastName, phoneNumber);
    }
}

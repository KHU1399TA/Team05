package main;
import main.Enums.*;
import java.util.*;

class Chef extends User {
    private static final Scanner input = new Scanner(System.in);

    ActionResult addFood(Food food) {
        for (int i = 0; i < Restaurant.food.size(); i++) {
            if (Restaurant.food.get(i).id == food.id) return ActionResult.ID_OR_FOOD_ALREADY_EXIST;
        }
        Restaurant.food.add(food);
        return ActionResult.SUCCESS;
    }

    ActionResult editFood(int id) {
        for (int i = 0; i < Restaurant.food.size(); i++) {
            if (Restaurant.food.get(i).id == id) {
                System.out.println(Restaurant.food.get(i));
                System.out.println("which field ? \n choose a number");
                System.out.println(" 1) id\n 2) name\n 3) ingredients");
                int selectedNumber = input.nextInt();
                input.nextLine();
                switch (selectedNumber) {
                    case 1 -> {
                        System.out.print("new id : ");
                        int newFoodId = input.nextInt();
                        input.nextLine();
                        for (int j = 0; j < Restaurant.food.size(); j++) {
                            if (Restaurant.food.get(j).id == newFoodId) return ActionResult.ID_ALREADY_EXIST;
                        }
                        Restaurant.food.get(i).id = newFoodId;
                        return ActionResult.SUCCESS;
                    }

                    case 2 -> {
                        System.out.print("new name : ");
                        Restaurant.food.get(i).name = input.nextLine();
                        return ActionResult.SUCCESS;
                    }

                    case 3 -> {
                        System.out.println("how many ingredients ?");
                        int newIngredientsSize = input.nextInt();
                        input.nextLine();
                        Restaurant.food.get(i).ingredients=new String[newIngredientsSize];
                        for (int j = 0; j < newIngredientsSize; j++) {
                            Restaurant.food.get(i).ingredients[j]=input.nextLine();
                        }
                        return ActionResult.SUCCESS;
                    }
                }
            }
        }
        return ActionResult.FOOD_NOT_FOUND;
    }

    ActionResult removeFood(int id) {
        for (int i = 0; i < Restaurant.food.size(); i++) {
            if (Restaurant.food.get(i).id == id) {
                Restaurant.food.remove(i);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FOOD_NOT_FOUND;
    }

    public  void foodState(){
        int  selectedNumber;
        boolean isAvailable;
        System.out.print("id : ");
        int id=input.nextInt();
        System.out.println("Is available now ?");
        System.out.println("1) Yes");
        System.out.println("2) No");
        selectedNumber=input.nextInt();
        isAvailable = selectedNumber == 1;
        System.out.println( changeFoodState(id,isAvailable));
    }

     ActionResult  changeFoodState(int id, boolean isAvailable) {
        for (int i = 0; i< Restaurant.food.size(); i++){
            if (Restaurant.food.get(i).id==id){
                Restaurant.food.get(i).isAvailable=isAvailable;
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FOOD_NOT_FOUND;
    }

    ActionResult cook(int id) {
return ActionResult.FOOD_NOT_FOUND;
    }

    public Chef(String userName, String password, AccessLevel accessLevel, Date registrationDate, Date lastLoginDate, String firstName, String lastName, String phoneNumber) {

        super(userName, password, accessLevel, registrationDate, lastLoginDate, firstName, lastName, phoneNumber);
    }
}
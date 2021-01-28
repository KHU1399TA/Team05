package main;

import main.Enums.ActionResult;

import java.util.Scanner;

class Chef extends Main {
    //changed from "extends main.User" to extend Main
private static final Scanner scanner=new Scanner(System.in);
    static ActionResult addFood(Food food) {
        for (int i = 0; i < Restaurant.food.size(); i++) {
            if (Restaurant.food.get(i).id == food.id) return ActionResult.ID_OR_FOOD_ALREADY_EXIST;
        }
        Restaurant.food.add(food);
        return ActionResult.SUCCESS;
    }

    static ActionResult editFood(int id) {
        for (int i = 0; i < Restaurant.food.size(); i++) {
            if (Restaurant.food.get(i).id == id) {
                System.out.println(Restaurant.food.get(i));
                System.out.println("which field ? \n choose a number");
                System.out.println(" 1) id\n 2) name\n 3) ingredients");
                int selectedNumber = scanner.nextInt();
                scanner.nextLine();
                switch (selectedNumber) {
                    case 1 -> {
                        System.out.print("new id : ");
                        int newFoodId = scanner.nextInt();
                        scanner.nextLine();
                        for (int j = 0; j < Restaurant.food.size(); j++) {
                            if (Restaurant.food.get(j).id == newFoodId) return ActionResult.ID_ALREADY_EXIST;
                        }
                        Restaurant.food.get(i).id = newFoodId;
                        return ActionResult.SUCCESS;
                    }

                    case 2 -> {
                        System.out.print("new name : ");
                        Restaurant.food.get(i).name = scanner.nextLine();
                        return ActionResult.SUCCESS;
                    }

                    case 3 -> {
                        System.out.println("how many ingredients ?");
                        int newIngredientsSize = scanner.nextInt();
                        scanner.nextLine();
                        Restaurant.food.get(i).ingredients=new String[newIngredientsSize];
                        for (int j = 0; j < newIngredientsSize; j++) {
                            Restaurant.food.get(i).ingredients[j]=scanner.nextLine();
                        }
                        return ActionResult.SUCCESS;
                    }
                }
            }
        }
        return ActionResult.FOOD_NOT_FOUND;
    }

    static ActionResult removeFood(int id) {
        for (int i = 0; i < Restaurant.food.size(); i++) {
            if (Restaurant.food.get(i).id == id) {
                Restaurant.food.remove(i);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FOOD_NOT_FOUND;
    }
    static void foodState(){
        int  selectedNumber;
        boolean isAvailable;
        System.out.print("id : ");
        int id=scanner.nextInt();
        System.out.println("Is available now ?");
        System.out.println("1) Yes");
        System.out.println("2) No");
        selectedNumber=scanner.nextInt();
        isAvailable = selectedNumber == 1;
        System.out.println( changeFoodState(id,isAvailable));
    }

    static ActionResult  changeFoodState(int id, boolean isAvailable) {
        for (int i = 0; i< Restaurant.food.size(); i++){
            if (Restaurant.food.get(i).id==id){
                Restaurant.food.get(i).isAvailable=isAvailable;
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FOOD_NOT_FOUND;
    }

    ActionResult cook(int id) {

        for (int i = 0; i < Restaurant.order.size(); i++){
            if(Restaurant.order.get(i).id == id){
                Restaurant.order.get(i).state = "COOKED";
            }
        }

        return ActionResult.SUCCESS;
    }
}



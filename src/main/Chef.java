package main;

import main.Enums.ActionResult;
import utils.FileManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Chef extends Main { //changed from "extends main.User" to "extend Main"
    ActionResult addFood(Food food) {


        String result_of_search = "not_ok";

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "src/resources/Food"));
            String line = reader.readLine();


            while (line != null) {

                String ins1 = line;
                String[] ins2 = ins1.split(",");
                String foodID = ins2[0];
                String foodName = ins2[1];
                int foodID2 = Integer.parseInt(foodID);
                if (food.id == foodID2 || food.name.equals(foodName)) {
                    result_of_search = "ok";
                    break;
                }

                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (result_of_search == "ok") {
            return ActionResult.FOOD_ALREADY_EXIST;
        } else {

            FileManager fmanager = new FileManager("src/resources/Food");
            StringBuilder allString = new StringBuilder();
            allString.append(food.id + ",");
            allString.append(food.name + ",");
            allString.append("true" + ",");

            int size = food.ingredients.size();
            for (int i = 0; i < size; i++) {
                if (i == size - 1) {
                    allString.append(food.ingredients.get(i));

                } else {
                    allString.append(food.ingredients.get(i) + ",");

                }
            }

            allString.append("\n");
            fmanager.write(allString.toString(), true);


            return ActionResult.SUCCESS;
        }

    }

    ActionResult editFood(int id) {
        return ActionResult.SUCCESS;
    }

    ActionResult removeFood(String id) {

        boolean flag = false;
        StringBuilder allString = new StringBuilder();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "src/resources/Food"));
            String line = reader.readLine();

            while (line != null) {


                String ins1 = line;
                String[] ins2 = ins1.split(",");
                String id2 = ins2[0];

                if (id.equals(id2)) {
                    flag = true;

                } else {
                    allString.append(line);
                    allString.append("\n");
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (flag) {

            FileManager fmanager = new FileManager("src/resources/Food");
            fmanager.write(allString.toString(), false);
            return ActionResult.SUCCESS;
        } else {
            return ActionResult.FOOD_NOT_FOUND;
        }
    }

    ActionResult changeFoodState(int id, boolean isAvailable) {
        return ActionResult.SUCCESS;
    }

    ActionResult cook(int id) {

        for (int i = 0; i < currentOrders.size(); i++) {
            if (currentOrders.get(i).id == id) {
                currentOrders.get(i).state = "COOKED";
            }
        }

        return ActionResult.SUCCESS;
    }
}



package main;

import java.util.ArrayList;
import java.util.Arrays;

public class Food {
    int id;
    String name;
    String[] ingredients;
    boolean isAvailable;

    public Food(int id, String name,String[] ingredients, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return  "Food\n" +
                " id          =" + id +"\n"+
                " name        =" + name + "\n"+
                " ingredients =" + Arrays.toString(ingredients) +"\n"+
                " isAvailable =" + isAvailable +"\n"
                ;
    }
}


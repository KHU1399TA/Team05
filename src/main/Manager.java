package main;

import main.Enums.ActionResult;
import utils.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class Manager extends main.User {
    ActionResult register(main.User user) {
        //System.out.println(user.lastLoginDate);

        String result_of_search = "not_ok";

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "src/resources/Usernames-and-Passwords"));
            String line = reader.readLine();


            while (line != null) {
                // System.out.println(line);
                // read next line

                String ins1 = line;
                String[] ins2 = ins1.split(",");
                String username2 = ins2[3];
                //String password2 = ins2[4];
                if(user.userName.equals(username2)){
                    result_of_search = "ok";
                    break;

                }

                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(result_of_search == "ok"){
            return ActionResult.USERNAME_ALREADY_EXIST;
        }
        else{
            StringBuilder allString = new StringBuilder();
            allString.append(user.firstName + ",");
            allString.append(user.lastName + ",");
            allString.append(user.phoneNumber + ",");
            allString.append(user.userName + ",");
            allString.append(user.password + ",");
            allString.append(user.accessLevel + ",");
            allString.append(user.registrationDate + ",");
            allString.append(user.lastLoginDate);

            FileManager fmanager = new FileManager("src/resources/Usernames-and-Passwords");
            fmanager.write(allString.toString(), true);
            return ActionResult.SUCCESS;
        }


























    }

    ActionResult edit(String userName) {
        return ActionResult.SUCCESS;
    }

    ActionResult remove(String userName) {
        return ActionResult.SUCCESS;
    }
}

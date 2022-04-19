package com.nagy.ch06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Users {

    public static List<User> THE_USER_DB = new ArrayList<>();

    private static long AUTO_INCREMENT_USER_ID = 1L;

    public static void populateDB() {
        if (THE_USER_DB.isEmpty()){
            User user1 = new User(AUTO_INCREMENT_USER_ID++,"derrick.nagy@kirkwood.edu", "Derrick", "Nagy", "2813899902", "P@ssw0rd");
            Map<String, Boolean> permissions = new HashMap<>();

            permissions.put("active", true);
            permissions.put("admin", true);
            user1.setPermissions(permissions);
            THE_USER_DB.add(user1);
            //usersArr[0] = user1;

            User user2 = new User(AUTO_INCREMENT_USER_ID++,"random@kirkwood.edu", "Ran", "Dom", "1234567890", "P@ssw0rd");
            Map<String, Boolean> permissions2 = new HashMap<>();
            permissions2.put("active", true);
            permissions2.put("admin", false);
            user2.setPermissions(permissions2);

            THE_USER_DB.add(user2);


            User user3 = new User(AUTO_INCREMENT_USER_ID++,"marc@school.com", "Marc", "Hauschildt", "1234567890", "P@ssw0rd");
            Map<String, Boolean> permissions3 = new HashMap<>();

            permissions3.put("active", true);
            permissions3.put("admin", true);
            user3.setPermissions(permissions3);
            THE_USER_DB.add(user3);

        }
    }

    public static long addUser(){
        User user = new User();
        user.setUserId(AUTO_INCREMENT_USER_ID++);

        THE_USER_DB.add(user);
        return user.getUserId();

    }

}

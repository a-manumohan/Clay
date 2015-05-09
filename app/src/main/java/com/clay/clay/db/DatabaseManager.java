package com.clay.clay.db;

import com.activeandroid.query.Select;
import com.clay.clay.model.User;

import java.util.UUID;

/**
 * Created by manuMohan on 15/05/09.
 */
public class DatabaseManager {
    public static void createRootUserIfNotPresent() {
        User user = new Select().
                from(User.class).
                where("role = ?", User.Role.ROOT).
                executeSingle();
        if(user == null){
            user = new User();
            user.setName("Root User");
            user.setUsername("root");
            user.setPassword("admin1234");
            user.setUserId(UUID.randomUUID().toString());
            user.setRole(User.Role.ROOT);
            user.save();
        }
    }
}

package com.clay.clay.db;

import com.activeandroid.query.Select;
import com.clay.clay.model.Door;
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
        if (user == null) {
            user = new User();
            user.setName("Root User");
            user.setUsername("root");
            user.setPassword("admin1234");
            user.setUserId(UUID.randomUUID().toString());
            user.setRole(User.Role.ROOT);
            user.save();
        }
    }

    public static void createDummyDoors() {
        if (new Select().from(Door.class).execute().size() > 0) {
            return;
        }
        Door door = new Door();
        door.setName("Front Door");
        door.setDoorId(UUID.randomUUID().toString());
        door.save();

        door = new Door();
        door.setName("Back Door");
        door.setDoorId(UUID.randomUUID().toString());
        door.save();

        door = new Door();
        door.setName("Office Door");
        door.setDoorId(UUID.randomUUID().toString());
        door.save();

        door = new Door();
        door.setName("Conf Door");
        door.setDoorId(UUID.randomUUID().toString());
        door.save();

        door = new Door();
        door.setName("Kitchen Door");
        door.setDoorId(UUID.randomUUID().toString());
        door.save();
    }

    public static void createDummyUsers() {
        if (new Select().from(User.class).execute().size() > 1) {
            return;
        }
        User user = new User();
        user.setName("John");
        user.setUserId(UUID.randomUUID().toString());
        user.setUsername("john");
        user.setPassword("1234");
        user.setRole(User.Role.USER);
        user.save();

        user = new User();
        user.setName("Manu");
        user.setUserId(UUID.randomUUID().toString());
        user.setUsername("manu");
        user.setPassword("1234");
        user.setRole(User.Role.USER);
        user.save();

        user = new User();
        user.setName("Mohan");
        user.setUserId(UUID.randomUUID().toString());
        user.setUsername("mohan");
        user.setPassword("1234");
        user.setRole(User.Role.USER);
        user.save();

        user = new User();
        user.setName("James");
        user.setUserId(UUID.randomUUID().toString());
        user.setUsername("james");
        user.setPassword("1234");
        user.setRole(User.Role.USER);
        user.save();

        user = new User();
        user.setName("Helen");
        user.setUserId(UUID.randomUUID().toString());
        user.setUsername("helen");
        user.setPassword("1234");
        user.setRole(User.Role.USER);
        user.save();
    }
}

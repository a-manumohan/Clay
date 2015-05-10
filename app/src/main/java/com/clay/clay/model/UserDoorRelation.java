package com.clay.clay.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by manuMohan on 15/05/10.
 */
@Table(name = "userdoor_relation")
public class UserDoorRelation extends Model{
    @Column(name = "user")
    private User user;

    @Column(name = "door")
    private Door door;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }
}

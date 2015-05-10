package com.clay.clay.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by manuMohan on 15/05/10.
 */
@Table(name = "doorlog")
public class DoorLog extends Model{
    public static enum Status{
        ACCESS_DENIED,
        OPENED
    }
    @Column
    private Door door;
    @Column
    private User user;
    @Column
    private String timestamp;
    @Column
    private Status status;

    public Door getDoor() {
        return door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}


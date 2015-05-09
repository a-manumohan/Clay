package com.clay.clay.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by manuMohan on 15/05/09.
 */
@Table(name = "Doors")
public class Door extends Model{
    @Column(name = "doorid",unique = true)
    private String doorId;

    @Column(name = "name")
    private String name;

    public String getDoorId() {
        return doorId;
    }

    public void setDoorId(String doorId) {
        this.doorId = doorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

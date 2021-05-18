package com.example.project.DataBase;

import android.location.Address;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users")
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "lastname")
    private String lastname;



    @ColumnInfo(name = "email")
    private String email;

    @ColumnInfo(name = "phone")
    private String phone;

    public UserEntity(){}
    public UserEntity(String name, String lname, String email,String phone) {
        this.ID = 0;
        this.name = name;
        this.lastname = lname;
        this.email = email;
        this.phone = phone;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}

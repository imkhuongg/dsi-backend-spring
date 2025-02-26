package com.DSI_V1.dsi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_address")
public class User_address {
    @Id
    private int user_address_id;
    private int user_id;
    private String address;
    private String full_name;
    private int phone;

    public int getUser_address_id() {
        return user_address_id;
    }

    public void setUser_address_id(int user_address_id) {
        this.user_address_id = user_address_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}

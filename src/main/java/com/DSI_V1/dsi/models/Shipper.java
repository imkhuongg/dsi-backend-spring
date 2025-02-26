package com.DSI_V1.dsi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "shipper")
public class Shipper {
    @Id
    private int shipper_id;
    private String email;
    private String password;
    private int phone;
    private String address;
    private LocalDateTime birth;
    private int transportCompany_Id;

    public int getShipper_id() {
        return shipper_id;
    }

    public void setShipper_id(int shipper_id) {
        this.shipper_id = shipper_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getBirth() {
        return birth;
    }

    public void setBirth(LocalDateTime birth) {
        this.birth = birth;
    }

    public int getTransportCompany_Id() {
        return transportCompany_Id;
    }

    public void setTransportCompany_Id(int transportCompany_Id) {
        this.transportCompany_Id = transportCompany_Id;
    }
}

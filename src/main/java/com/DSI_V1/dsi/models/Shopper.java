package com.DSI_V1.dsi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "shopper")
public class Shopper {
    @Id
    private int shopper_id;
    private int user_id;
    private String name_shop;
    private String email;
    private String avatar;
    private int phone;
    @Column(name = "shop_address")
    private String shop_address;
    private int follower;
    private int total_sold;
    private BigDecimal total_revenue;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public int getShopper_id() {
        return shopper_id;
    }

    public void setShopper_id(int shopper_id) {
        this.shopper_id = shopper_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName_shop() {
        return name_shop;
    }

    public void setName_shop(String name_shop) {
        this.name_shop = name_shop;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public int getTotal_sold() {
        return total_sold;
    }

    public void setTotal_sold(int total_sold) {
        this.total_sold = total_sold;
    }

    public BigDecimal getTotal_revenue() {
        return total_revenue;
    }

    public void setTotal_revenue(BigDecimal total_revenue) {
        this.total_revenue = total_revenue;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }
}

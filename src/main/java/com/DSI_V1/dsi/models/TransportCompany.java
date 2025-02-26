package com.DSI_V1.dsi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transport_company")
public class TransportCompany {
    @Id
    @Column(name = "transportCompany_id")
    private int transportCompany_Id;
    private String transportCompany_name;
    private String taxCode;
    private String address;
    private String logo;
    private String email;
    private String phone;

    public int getTransportCompany_Id() {
        return transportCompany_Id;
    }

    public void setTransportCompany_Id(int transportCompany_Id) {
        this.transportCompany_Id = transportCompany_Id;
    }

    public String getTransportCompany_name() {
        return transportCompany_name;
    }

    public void setTransportCompany_name(String transportCompany_name) {
        this.transportCompany_name = transportCompany_name;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

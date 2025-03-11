package com.DSI_V1.dsi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "seller_transport")
public class SellerTransport {
    @Id
    private int seller_transport_id;
    private int shopper_id;
    private int transport_company_id;
    private boolean status;

    public int getSeller_transport_id() {
        return seller_transport_id;
    }

    public void setSeller_transport_id(int seller_transport_id) {
        this.seller_transport_id = seller_transport_id;
    }

    public int getShopper_id() {
        return shopper_id;
    }

    public void setShopper_id(int shopper_id) {
        this.shopper_id = shopper_id;
    }

    public int getTransport_company_id() {
        return transport_company_id;
    }

    public void setTransport_company_id(int transport_company_id) {
        this.transport_company_id = transport_company_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

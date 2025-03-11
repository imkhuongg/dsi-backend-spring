package com.DSI_V1.dsi.dto.requests.Products;

public class UpdateProductDTO {
    private String name_product;
    private double price;
    private String description;
    private int name_brand;
    private String thumb;
    private int product_id;


    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getName_brand() {
        return name_brand;
    }

    public void setName_brand(int name_brand) {
        this.name_brand = name_brand;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
}

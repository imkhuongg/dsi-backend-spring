package com.DSI_V1.dsi.services.manageShop;

import com.DSI_V1.dsi.models.product;
import com.DSI_V1.dsi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ManageProductService {
    @Autowired
    private ProductRepository productReposity;


    public int createProduct(String nameProduct, double price, int user_id, String description, String name_brand, String thumb,int shopper_id,LocalDateTime created_at) {
        return productReposity.createProduct(nameProduct, price, user_id, description, name_brand, thumb,shopper_id,created_at);
    }

    public List<product> products(int user_id) {
        return productReposity.products(user_id);
    }

    public int deleteProduct(int product_id) {
        return productReposity.deleteProduct(product_id);
    }

    public int updateProduct(String name_product, double price, String description, String name_brand, String thumb, LocalDateTime updated_at, int product_id){
        return productReposity.updateProduct(name_product,price,description,name_brand,thumb,updated_at,product_id);
    }

    public List<String> searchByName(int user_id, String name_product) {return productReposity.searchByName(user_id,name_product);}
    public List<product> resultSearch(int user_id, String name_product) {return productReposity.ResultProduct(user_id,name_product);}

}

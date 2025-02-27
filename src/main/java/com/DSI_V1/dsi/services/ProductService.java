package com.DSI_V1.dsi.services;

import com.DSI_V1.dsi.models.product;
import com.DSI_V1.dsi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productReposity;


    public List<product> AllProduct(){ return productReposity.getAllProduct();

    }

}

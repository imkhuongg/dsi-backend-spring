package com.DSI_V1.dsi.services;

import com.DSI_V1.dsi.models.product;
import com.DSI_V1.dsi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productReposity;


    public Page<product> AllProduct(int page, int size){ return productReposity.findAllByOrderByCreatedAtDesc(PageRequest.of(page, size));}
    public Page<product> getAllProductsSortedByQuantitySold(int page, int size){return productReposity.findAllByOrderByQuantitySoldDesc(PageRequest.of(page,size));}

    public product getProductById(int id){return productReposity.findById(id).orElseThrow(() -> new RuntimeException("ID" +id+ "not found "));}

}

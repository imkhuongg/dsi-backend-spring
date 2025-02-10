package com.DSI_V1.dsi.controllers;


import com.DSI_V1.dsi.helpers.ExtractUserIDFromToken;
import com.DSI_V1.dsi.models.product;
import com.DSI_V1.dsi.services.ProductService;
import com.DSI_V1.dsi.services.manageShop.ManageProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("api/v1/product")
public class ProductController {
    @Autowired
    private ExtractUserIDFromToken extractUserIDFromToken;

    @Autowired
    private ProductService productServiceAll;


    @Autowired
    private ManageProductService productService;

    private final String uploadDir = System.getProperty("user.dir") + "/resources/images/productImage/";


    @GetMapping("/get_shopper_product")
    public ResponseEntity<?> getShopperProduct(HttpServletRequest request){
        int user_id = extractUserIDFromToken.getUserIDFromToken(request);
        List<product> ShopperProducts = productService.products(user_id);

        return new ResponseEntity<>(ShopperProducts, HttpStatus.OK);

    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(
            @RequestParam("name_product") String name_product,
            @RequestParam("price")  double price,
            @RequestParam("description")   String description,
            @RequestParam("name_brand") String name_brand,
            @RequestParam("thumb") String thumb,
            HttpServletRequest request
    ){
        Integer user_id = extractUserIDFromToken.getUserIDFromToken(request);
        if(user_id == null) return new ResponseEntity<>("Something went wrong" , HttpStatus.BAD_REQUEST);

        int result = productService.createProduct(name_product , price, user_id,description,name_brand,thumb);

        if(result != 1) return new ResponseEntity<>("Failed to create product" , HttpStatus.BAD_REQUEST);
        return  new ResponseEntity<>("product Create Successfully" , HttpStatus.CREATED);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteProduct(@RequestParam("product_id") String product_id){
        int productId = Integer.parseInt(product_id);

        int result = productService.deleteProduct(productId);
        if(result != 1) return new ResponseEntity<>("Failed to create product" , HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("product delete successfully" , HttpStatus.ACCEPTED);
    }
    @PostMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestParam("name_product") String name_product,
                                        @RequestParam("price") String price,
                                        @RequestParam("description") String description,
                                        @RequestParam("name_brand") String name_brand,
                                        @RequestParam("thumb") String thumb,
                                        @RequestParam("product_id") String product_id){
        int productId = Integer.parseInt(product_id);
        double price_double = Double.parseDouble(price);

        LocalDateTime updated_at = LocalDateTime.now();

        int result =productService.updateProduct(name_product,price_double,description,name_brand,thumb,updated_at,productId);
        if(result != 1) return new ResponseEntity<>("Failed to create product" , HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("Product update successfully" , HttpStatus.ACCEPTED);
    }

    @GetMapping("/productsAll")
    public ResponseEntity<?> getAllProduct(){
        List<product> AllProduct = productServiceAll.AllProduct();

        return new ResponseEntity<>(AllProduct , HttpStatus.OK);
    }

}

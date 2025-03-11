package com.DSI_V1.dsi.controllers;


import com.DSI_V1.dsi.dto.requests.ProductDTO;
import com.DSI_V1.dsi.dto.requests.Products.UpdateProductDTO;
import com.DSI_V1.dsi.dto.responses.ProductResponsePage;
import com.DSI_V1.dsi.helpers.ExtractUserIDFromToken;
import com.DSI_V1.dsi.helpers.SuccessResponse;
import com.DSI_V1.dsi.models.product;
import com.DSI_V1.dsi.services.ProductService;
import com.DSI_V1.dsi.services.manageShop.ManageProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

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


    @GetMapping("")
    public ResponseEntity<?> getShopperProduct(HttpServletRequest request){
        int user_id = extractUserIDFromToken.getUserIDFromToken(request);
        List<product> ShopperProducts = productService.products(user_id);

        return new ResponseEntity<>(ShopperProducts, HttpStatus.OK);

    }

    @PostMapping("")
    public ResponseEntity<?> createProduct(HttpServletRequest request, @RequestBody ProductDTO productDTO){
        Integer user_id = extractUserIDFromToken.getUserIDFromToken(request);
        if(user_id == null) return new ResponseEntity<>("Something went wrong" , HttpStatus.BAD_REQUEST);

        String path = user_id  + "/" + productDTO.getThumb();
        LocalDateTime created_at = LocalDateTime.now();

        int result = productService.createProduct(productDTO.getName_product() , productDTO.getPrice(), user_id,productDTO.getDescription(), productDTO.getName_brand(), path,productDTO.getShopper_id(),created_at);

        if(result != 1) return new ResponseEntity<>("Failed to create product" , HttpStatus.BAD_REQUEST);
        return  ResponseEntity.ok(SuccessResponse.StatusMessage("Created product successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id){
        int productId = Integer.parseInt(id);

        int result = productService.deleteProduct(productId);
        if(result != 1) return new ResponseEntity<>("Failed to create product" , HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("product delete successfully" , HttpStatus.ACCEPTED);
    }
    @PutMapping("")
    public ResponseEntity<?> updateProduct(@RequestBody UpdateProductDTO productDTO){
        LocalDateTime updated_at = LocalDateTime.now();

        int result =productService.updateProduct(productDTO.getName_product(),productDTO.getPrice(),productDTO.getDescription(), productDTO.getName_brand(), productDTO.getThumb(),updated_at, productDTO.getProduct_id());
        if(result != 1) return new ResponseEntity<>("Failed to create product" , HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(SuccessResponse.StatusMessage("Product updated"));
    }

    @GetMapping("/productsAll")
    public ResponseEntity<?> getAllProduct(@RequestParam int page , @RequestParam int size){
        Page<product> AllProduct = productServiceAll.AllProduct(page,size);

        return ResponseEntity.ok(new ProductResponsePage<>(AllProduct));
    }
    @GetMapping("/topProduct")
    public ResponseEntity<ProductResponsePage<product>> getSortedByQuantitySold(@RequestParam(defaultValue = "0") int page,
                                                                                @RequestParam(defaultValue = "10") int size){
        Page<product> products = productServiceAll.getAllProductsSortedByQuantitySold(page,size);
        return ResponseEntity.ok(new ProductResponsePage<>(products));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable int id){

        return ResponseEntity.ok(productServiceAll.getProductById(id));
    }

}

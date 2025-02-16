package com.DSI_V1.dsi.controllers;

import com.DSI_V1.dsi.helpers.ExtractUserIDFromToken;
import com.DSI_V1.dsi.models.Shopper;
import com.DSI_V1.dsi.services.ShopperService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("api/v1/shopper")
public class ShopperController  {
    @Autowired
    private ShopperService shopperService;

    @Autowired
    private ExtractUserIDFromToken extractUserIDFromToken;

    @GetMapping("/check")
    public ResponseEntity<?> checkShopper(HttpServletRequest request){
        int user_id = extractUserIDFromToken.getUserIDFromToken(request);

        int check = shopperService.checkShopper(user_id);

        Map<String, Integer> response = new HashMap<>();
        response.put("isShopper", check);
        return new ResponseEntity<>(response , HttpStatus.OK);

    }
    @GetMapping("/getShopper")
    public ResponseEntity<?> getShopper(HttpServletRequest request){
        int user_id = extractUserIDFromToken.getUserIDFromToken(request);
        Shopper shopper = shopperService.getShopper(user_id);

        return new ResponseEntity<>(shopper, HttpStatus.OK);

    }

    @PostMapping("/Registration")
    public ResponseEntity<?> ShopperRegistration(HttpServletRequest request,
                                                 @RequestParam("name_shop") String name_shop,
                                                 @RequestParam("email") String email,
                                                 @RequestParam("phone") int phone,
                                                 @RequestParam("shop_address") String shop_address
                                                 ){
        int user_id = extractUserIDFromToken.getUserIDFromToken(request);

        LocalDateTime created_at = LocalDateTime.now();

        int result = shopperService.ShopperRegister(user_id,name_shop,email,phone,shop_address,0,0, BigDecimal.valueOf(0.0),created_at);

        if(result != 1)  return new ResponseEntity<>("Failed to create shopper" , HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>("Shopper created Successfullty" , HttpStatus.CREATED);
    }
}

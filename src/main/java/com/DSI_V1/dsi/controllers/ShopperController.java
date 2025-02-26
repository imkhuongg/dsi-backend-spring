package com.DSI_V1.dsi.controllers;

import com.DSI_V1.dsi.dto.requests.ShopperUpdateDTO;
import com.DSI_V1.dsi.helpers.ExtractUserIDFromToken;
import com.DSI_V1.dsi.helpers.SuccessResponse;
import com.DSI_V1.dsi.models.Shopper;
import com.DSI_V1.dsi.services.ShopperService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("")
    public ResponseEntity<?> getShopper(HttpServletRequest request){
        int user_id = extractUserIDFromToken.getUserIDFromToken(request);
        Shopper shopper = shopperService.getShopper(user_id);
        System.out.println(shopper.getShop_address());
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

        return new ResponseEntity<>("Shopper created Successfully" , HttpStatus.CREATED);
    }
    @PostMapping("/updateAvatar")
    public ResponseEntity<?> UpdateAvatar(HttpServletRequest request,
                                          @RequestParam("avatar") String avatar){
        int user_id = extractUserIDFromToken.getUserIDFromToken(request);
        int result = shopperService.UpdateIMG(user_id,avatar);
        if(result != 1)  return new ResponseEntity<>("Failed to create shopper" , HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>("avatar updated Successfully" , HttpStatus.CREATED);
    }
    @PutMapping("")
    public ResponseEntity<?> UpdateShopper(HttpServletRequest request, @RequestBody ShopperUpdateDTO shopperUpdateDTO){
        int user_id = extractUserIDFromToken.getUserIDFromToken(request);
        LocalDateTime updated_at = LocalDateTime.now();
        int result = shopperService.UpdateShopper(user_id,shopperUpdateDTO.getName_shop(),shopperUpdateDTO.getEmail(),shopperUpdateDTO.getPhone(),shopperUpdateDTO.getShop_address(),updated_at);
        if(result != 1)  return new ResponseEntity<>("Failed to update shopper" , HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(SuccessResponse.StatusMessage("Shopper updated"));

    }
}

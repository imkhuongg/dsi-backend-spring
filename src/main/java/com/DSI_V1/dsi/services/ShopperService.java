package com.DSI_V1.dsi.services;

import com.DSI_V1.dsi.models.Shopper;
import com.DSI_V1.dsi.repository.ShopperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class ShopperService {
    @Autowired
    private ShopperRepository shopperRepository;

    public int checkShopper(int user_id){return shopperRepository.check(user_id);}

    public Shopper getShopper(int user_id){return shopperRepository.getShopper(user_id);}

    public int ShopperRegister(int user_id, String name_shop, String email, int phone, String shop_address, int follower, int total_sold, BigDecimal total_revenue, LocalDateTime created_at){ return shopperRepository.ShopperRegister(user_id, name_shop, email, phone, shop_address,follower,total_sold,total_revenue,created_at);}

    public int UpdateIMG(int user_id, String avatar){return shopperRepository.UpdateAvatar(user_id,avatar);}

    public int UpdateShopper(int user_id,String name_shop, String email, int phone, String shop_address,LocalDateTime updated_at){return shopperRepository.updateShopper(user_id,name_shop, email, phone, shop_address,updated_at);}
}

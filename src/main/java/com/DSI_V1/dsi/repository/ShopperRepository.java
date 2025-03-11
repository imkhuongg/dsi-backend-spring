package com.DSI_V1.dsi.repository;

import com.DSI_V1.dsi.models.Shopper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Repository
public interface ShopperRepository extends JpaRepository<Shopper , Integer> {
    @Query(value = "SELECT COUNT(*) FROM shopper WHERE user_id = :user_id" , nativeQuery = true)
    int check(@Param("user_id") int user_id);

    @Query(value = "SELECT * FROM shopper WHERE user_id = :user_id" , nativeQuery = true)
    Shopper getShopper(@Param("user_id") int user_id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO shopper(user_id,name_shop,email,phone,shop_address,follower,total_sold,total_revenue,created_at) VALUES (:user_id,:name_shop,:email,:phone,:shop_address,:follower,:total_sold,:total_revenue,:created_at)" , nativeQuery = true)
    int ShopperRegister(@Param("user_id") int user_id,
                        @Param("name_shop") String name_shop,
                        @Param("email") String email,
                        @Param("phone") int phone,
                        @Param("shop_address") String shop_address,
                        @Param("follower") int follower,
                        @Param("total_sold") int total_sold,
                        @Param("total_revenue") BigDecimal total_revenue,
                        @Param("created_at")LocalDateTime created_at
                      );
    @Transactional
    @Modifying
    @Query(value = "UPDATE shopper set avatar = :avatar WHERE user_id = :user_id" , nativeQuery = true)
    int UpdateAvatar(@Param("user_id") int user_id,
                     @Param("avatar") String avatar);


    @Transactional
    @Modifying
    @Query(value = "UPDATE shopper SET name_shop = :name_shop,email = :email,phone = :phone, shop_address = :shop_address,updated_at =:updated_at WHERE user_id = :user_id", nativeQuery = true)
    int updateShopper(@Param("user_id") int user_id,
                      @Param("name_shop") String name_shop,
                      @Param("email") String email,
                      @Param("phone") int phone,
                      @Param("shop_address") String shop_address,
                      @Param("updated_at") LocalDateTime updated_at
    );

}

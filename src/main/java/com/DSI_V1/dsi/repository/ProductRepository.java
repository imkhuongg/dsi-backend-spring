package com.DSI_V1.dsi.repository;

import com.DSI_V1.dsi.models.product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<product , Integer> {


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO product(name_product,price,user_id,description,rate,brand_id,thumb,quantity_sold,shopper_id,created_at) VALUES (:name_product,:price,:user_id,:description,0,:brand_id,:thumb,0,:shopper_id,:created_at)" , nativeQuery = true)
    int createProduct(
                    @Param("name_product") String name_product,
                    @Param("price") double price,
                    @Param("user_id") int user_id,
                    @Param("description") String description,
                    @Param("brand_id") int brand_id,
                    @Param("thumb") String thumb,
                    @Param("shopper_id") int shopper_id,
                    @Param("created_at") LocalDateTime created_at);

    @Query(value = "SELECT * FROM product WHERE user_id = :user_id" , nativeQuery = true)
    List<product> products(@Param("user_id") int user_id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM product Where product_id = :product_id" , nativeQuery = true)
    int deleteProduct(@Param("product_id") int product_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE product SET name_product = :name_product , price = :price ,description = :description , brand_id = :brand_id , thumb = :thumb, updated_at = :updated_at WHERE product_id = :product_id" , nativeQuery = true)
    int updateProduct(@Param("name_product") String name_product,
                      @Param("price") double price,
                      @Param("description") String description,
                      @Param("brand_id") int brand_id,
                      @Param("thumb") String thumb,
                      @Param("updated_at") LocalDateTime updated_at,
                      @Param("product_id") int product_id);

    @NonNull
    Page<product>  findAllByOrderByCreatedAtDesc(@NonNull Pageable pageable);

    @Query(value = "SELECT name_product FROM product WHERE user_id = :user_id AND name_product LIKE :name_product%", nativeQuery = true)
    List<String> searchByName(@Param("user_id") int user_id,
                              @Param("name_product") String nameProduct);

    @Query(value = "SELECT * FROM product WHERE user_id = :user_id AND name_product LIKE :name_product%" , nativeQuery = true)
    List<product> ResultProduct(@Param("user_id") int user_id,
                                @Param("name_product") String nameProduct);

    @NonNull
    Page<product> findAllByOrderByQuantitySoldDesc(@NonNull Pageable pageable);


}

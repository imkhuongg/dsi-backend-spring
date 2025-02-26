package com.DSI_V1.dsi.repository;

import com.DSI_V1.dsi.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepositoty extends CrudRepository<User, Integer> {
    @Query(value = "SELECT * FROM Users WHERE email = :email" , nativeQuery = true)
    User getUserByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Users(first_name,last_name,email,password) VALUES(:first_name,:last_name,:email,:password)" , nativeQuery = true)
    int registerUser(@Param("first_name") String first_name,
                     @Param("last_name") String last_name,
                     @Param("email") String email,
                     @Param("password") String password);






}

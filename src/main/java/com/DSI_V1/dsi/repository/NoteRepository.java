package com.DSI_V1.dsi.repository;

import com.DSI_V1.dsi.models.Note;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NoteRepository extends CrudRepository<Note ,Integer> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO notes(user_id,title,body) VALUES (:user_id,:title,:body)" , nativeQuery = true)
    int createNote(@Param("user_id") int user_id,
                   @Param("title") String title,
                   @Param("body") String body);
}

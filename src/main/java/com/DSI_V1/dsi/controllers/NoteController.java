package com.DSI_V1.dsi.controllers;

import com.DSI_V1.dsi.helpers.ExtractUserIDFromToken;
import com.DSI_V1.dsi.services.NoteService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/note")
public class NoteController {

    @Autowired
    private ExtractUserIDFromToken extractUserIDFromToken;

    @Autowired
    private NoteService noteService;
    @PostMapping("/create")
    public ResponseEntity createNote(@RequestParam("title") String title,
                                     @RequestParam("body") String body,
                                     HttpServletRequest request){
        Integer user_id = extractUserIDFromToken.getUserIDFromToken(request);
        if(user_id == null) return new ResponseEntity("Something went wrong" , HttpStatus.BAD_REQUEST);

        int result = noteService.createNote(user_id , title, body);

        if(result != 1) return new ResponseEntity("Failed to create note" , HttpStatus.BAD_REQUEST);

        return  new ResponseEntity("Note Create Successfully" , HttpStatus.CREATED);


    }

}

package com.DSI_V1.dsi.services;

import com.DSI_V1.dsi.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public int createNote(int id, String title, String body){
        return noteRepository.createNote(id,title,body);
    }

}

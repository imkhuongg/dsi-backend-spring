package com.DSI_V1.dsi.services;

import com.DSI_V1.dsi.repository.TransportCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransportCompanyServices {

    @Autowired
    private TransportCompanyRepository transportCompanyRepository;

    public long count(){return transportCompanyRepository.count();}
}

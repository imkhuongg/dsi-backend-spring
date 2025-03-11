package com.DSI_V1.dsi.controllers;

import com.DSI_V1.dsi.dto.responses.CountResponse;
import com.DSI_V1.dsi.services.TransportCompanyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/transport_company")
public class TransportCompanyController {
    @Autowired
    private TransportCompanyServices transportCompanyServices;

    @GetMapping("/count")
    public ResponseEntity<?> count(){
        long count = transportCompanyServices.count();

        return ResponseEntity.ok(new CountResponse(count));
    }
}

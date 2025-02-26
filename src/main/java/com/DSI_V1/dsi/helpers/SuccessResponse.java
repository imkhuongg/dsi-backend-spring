package com.DSI_V1.dsi.helpers;

import java.util.HashMap;
import java.util.Map;

public class SuccessResponse {
    public static Map<String,String> StatusMessage(String message){
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }
}

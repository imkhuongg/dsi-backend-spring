package com.DSI_V1.dsi.dto.responses;

public class CountResponse {
    private long total;

    public CountResponse(long total) {
        this.total = total;
    }

    public long getTotal() {
        return total;
    }
}

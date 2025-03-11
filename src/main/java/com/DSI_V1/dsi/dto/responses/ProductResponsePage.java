package com.DSI_V1.dsi.dto.responses;

import org.springframework.data.domain.Page;

import java.util.List;

public class ProductResponsePage<T> {
    private List<T> content;
    private int totalPages;
    private long totalElements;
    private int currentPage;
    private int numberOfElement;

    public ProductResponsePage(Page<T> page) {
        this.content = page.getContent();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.currentPage = page.getNumber();
        this.numberOfElement = page.getNumberOfElements();
    }

    public List<T> getContent() {
        return content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getCurrentPage() {
        return currentPage;
    }
    public int getNumberOfElement(){
        return numberOfElement;
    }
}

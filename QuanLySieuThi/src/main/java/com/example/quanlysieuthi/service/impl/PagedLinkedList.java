package com.example.quanlysieuthi.service.impl;

import java.util.LinkedList;

public class PagedLinkedList<T> {
    private LinkedList<T> data;
    private int currentPage;
    private int totalPages;
    private boolean hasPreviousPage;
    private boolean hasNextPage;

    public PagedLinkedList(LinkedList<T> data, int currentPage, int totalPages, boolean hasPreviousPage, boolean hasNextPage) {
        this.data = data;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.hasPreviousPage = hasPreviousPage;
        this.hasNextPage = hasNextPage;
    }

    // Getters và setters

    public LinkedList<T> getData() {
        return data;
    }

    public void setData(LinkedList<T> data) {
        this.data = data;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }
}

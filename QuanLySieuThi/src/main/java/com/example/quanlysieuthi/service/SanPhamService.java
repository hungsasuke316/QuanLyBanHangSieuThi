package com.example.quanlysieuthi.service;

import com.example.quanlysieuthi.data.entity.SanPham;
import com.example.quanlysieuthi.dto.request.SanPhamRequest;
import com.example.quanlysieuthi.service.impl.PagedLinkedList;

import java.util.LinkedList;

public interface SanPhamService {
    public PagedLinkedList<SanPham> getAllSanPham(Integer pageNumber, Integer pageSize);
    public void createSanPham(SanPhamRequest dto);
    public void updateSanPham(SanPhamRequest dto);
    public LinkedList<SanPham> deleteSanPham(String ma);
    public SanPham getSanPham(String ma);
}

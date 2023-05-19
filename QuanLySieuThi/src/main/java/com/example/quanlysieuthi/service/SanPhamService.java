package com.example.quanlysieuthi.service;

import com.example.quanlysieuthi.data.entity.SanPham;
import com.example.quanlysieuthi.dto.request.SanPhamRequest;

import java.util.LinkedList;

public interface SanPhamService {
    public LinkedList<SanPham> getAllSanPham();
    public void createSanPham(SanPhamRequest dto);
    public void updateSanPham(SanPhamRequest dto);
    public LinkedList<SanPham> deleteSanPham(String ma);
    public SanPham getSanPham(String ma);
}

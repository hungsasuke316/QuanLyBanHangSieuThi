package com.example.quanlysieuthi.service;

import com.example.quanlysieuthi.data.entity.KhachHang;
import com.example.quanlysieuthi.data.entity.NhaCungCap;
import com.example.quanlysieuthi.dto.request.KhachHangRequest;

import java.util.LinkedList;

public interface KhachHangService {
    public LinkedList<KhachHang> getAllKhachHang();
    public void creatKhachHang(KhachHangRequest dto);
    public void updateKhachHang(KhachHangRequest dto);
    public LinkedList<KhachHang> deleteKhachHang(String ma);

    public KhachHang getKhachHang(String ma);
}

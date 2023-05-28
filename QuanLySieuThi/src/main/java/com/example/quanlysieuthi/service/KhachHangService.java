package com.example.quanlysieuthi.service;

import com.example.quanlysieuthi.data.entity.KhachHang;
import com.example.quanlysieuthi.dto.request.KhachHangRequest;
import com.example.quanlysieuthi.service.impl.PagedLinkedList;


public interface KhachHangService {
    public PagedLinkedList<KhachHang> getAllKhachHang(Integer pageNumber, Integer pageSize);
    public void creatKhachHang(KhachHangRequest dto);
    public void updateKhachHang(KhachHangRequest dto);
    public void deleteKhachHang(String ma);

    public KhachHang getKhachHang(String ma);
    public PagedLinkedList<KhachHang> searchKhachHang(String ma, String ten, Integer pageNumber, Integer pageSize);
}

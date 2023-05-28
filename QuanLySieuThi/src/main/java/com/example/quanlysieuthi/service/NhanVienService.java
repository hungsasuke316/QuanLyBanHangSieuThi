package com.example.quanlysieuthi.service;

import com.example.quanlysieuthi.data.entity.NhanVien;
import com.example.quanlysieuthi.dto.request.NhanVienRequest;
import com.example.quanlysieuthi.service.impl.PagedLinkedList;


public interface NhanVienService {
    public PagedLinkedList<NhanVien> getAllNhanVien(Integer pageNumber, Integer pageSize);
    public void creatNhanVien(NhanVienRequest dto);
    public void updateNhanVien(NhanVienRequest dto);
    public void deleteNhanVien(String ma);
    public NhanVien getNhanVien(String ma);
    public PagedLinkedList<NhanVien> searchNhanVien(String ma, String ten, Integer pageNumber, Integer pageSize);
}

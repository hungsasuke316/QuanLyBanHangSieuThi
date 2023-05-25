package com.example.quanlysieuthi.service;


import com.example.quanlysieuthi.data.entity.NhanVien;
import com.example.quanlysieuthi.dto.request.NhanVienRequest;
import com.example.quanlysieuthi.service.impl.PagedLinkedList;

import java.util.LinkedList;

public interface NhanVienService {
    public PagedLinkedList<NhanVien> getAllNhanVien(Integer pageNumber, Integer pageSize);
    public void creatNhanVien(NhanVienRequest dto);
    public void updateNhanVien(NhanVienRequest dto);
    public LinkedList<NhanVien> deleteNhanVien(String ma);
    public NhanVien getNhanVien(String ma);
}

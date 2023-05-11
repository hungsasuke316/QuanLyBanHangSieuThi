package com.example.quanlysieuthi.service;


import com.example.quanlysieuthi.data.entity.NhanVien;
import com.example.quanlysieuthi.dto.request.NhanVienRequest;

import java.util.LinkedList;

public interface NhanVienService {
    public LinkedList<NhanVien> getAllNhanVien();
    public void creatNhanVien(NhanVienRequest dto);
    public void updateNhanVien(NhanVienRequest dto);
    public LinkedList<NhanVien> deleteNhanVien(String ma);
    public NhanVien getNhanVien(String ma);
}

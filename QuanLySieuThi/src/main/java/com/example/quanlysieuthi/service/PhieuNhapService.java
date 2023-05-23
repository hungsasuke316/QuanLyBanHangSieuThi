package com.example.quanlysieuthi.service;

import com.example.quanlysieuthi.data.entity.PhieuNhap;
import com.example.quanlysieuthi.dto.request.PhieuNhapRequest;

import java.util.LinkedList;

public interface PhieuNhapService {
    public LinkedList<PhieuNhap> getAllPhieuNhap();
    public void createPhieuNhap(PhieuNhapRequest dto);
}

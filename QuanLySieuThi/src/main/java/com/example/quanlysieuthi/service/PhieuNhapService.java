package com.example.quanlysieuthi.service;

import com.example.quanlysieuthi.data.entity.PhieuNhap;
import com.example.quanlysieuthi.dto.request.PhieuNhapRequest;
import com.example.quanlysieuthi.service.impl.PagedLinkedList;

public interface PhieuNhapService {
    public PagedLinkedList<PhieuNhap> getAllPhieuNhap(Integer pageNumber, Integer pageSize);
    public void createPhieuNhap(PhieuNhapRequest dto);
}

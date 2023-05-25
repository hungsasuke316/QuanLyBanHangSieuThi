package com.example.quanlysieuthi.service;

import com.example.quanlysieuthi.data.entity.HoaDon;
import com.example.quanlysieuthi.dto.request.HoaDonRequest;
import com.example.quanlysieuthi.service.impl.PagedLinkedList;

public interface HoaDonService {
    public PagedLinkedList<HoaDon> getAllHoaDon(Integer pageNumber, Integer pageSize);
    public void createHoaDon(HoaDonRequest dto);
}

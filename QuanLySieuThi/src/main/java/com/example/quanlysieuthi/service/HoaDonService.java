package com.example.quanlysieuthi.service;

import com.example.quanlysieuthi.data.entity.HoaDon;
import com.example.quanlysieuthi.dto.request.HoaDonRequest;

import java.util.LinkedList;

public interface HoaDonService {
    public LinkedList<HoaDon> getAllHoaDon();
    public void createHoaDon(HoaDonRequest dto);
}

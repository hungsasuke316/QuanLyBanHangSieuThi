package com.example.quanlysieuthi.service;

import com.example.quanlysieuthi.dto.response.DoanhThuResponse;

public interface DoanhThuService {
    public DoanhThuResponse getDoanhThuReport(int year, int month);
}

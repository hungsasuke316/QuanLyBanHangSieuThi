package com.example.quanlysieuthi.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HoaDonRequest {
    private String ma;
    private String nhanVien;
    private String khachHang;
    private String sanPham;
    private Integer soLuong;
}

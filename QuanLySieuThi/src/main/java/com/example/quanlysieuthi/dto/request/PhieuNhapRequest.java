package com.example.quanlysieuthi.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhieuNhapRequest {
    private String ma;
    private String nhanVien;
    private String nhaCungCap;
    private String sanPham;
    private Integer soLuong;
}

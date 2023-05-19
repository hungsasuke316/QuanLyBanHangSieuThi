package com.example.quanlysieuthi.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SanPhamRequest {
    private String ma;
    private String ten;
    private String donViTinh;
    private Integer donGia;
    private Integer soLuongTon;
}

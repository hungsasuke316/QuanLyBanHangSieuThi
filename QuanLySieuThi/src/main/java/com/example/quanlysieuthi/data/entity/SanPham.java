package com.example.quanlysieuthi.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SanPham {
    @Id
    private String ma;
    private String ten;
    private String donViTinh;
    private Integer donGia;
    private Integer soLuongTon;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPham")
    private Set<HoaDon> hoaDon;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPham")
    private Set<PhieuNhap> phieuNhap;
}

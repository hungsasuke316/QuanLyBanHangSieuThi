package com.example.quanlysieuthi.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
    private Long donGia;
    private Integer soLuongTon;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPham")
    private Set<HoaDon> hoaDon;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPham")
    private Set<PhieuNhap> phieuNhap;
}

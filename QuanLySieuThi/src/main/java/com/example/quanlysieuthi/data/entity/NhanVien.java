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
public class NhanVien {
    @Id
    private String ma;
    private String ten;
    private Integer cccd;
    private Integer sdt;
    private String diaChi;

    @JsonIgnore
    @OneToMany(mappedBy = "nhanVien")
    private Set<HoaDon> hoaDon;

    @JsonIgnore
    @OneToMany(mappedBy = "nhanVien")
    private Set<PhieuNhap> phieuNhap;
}

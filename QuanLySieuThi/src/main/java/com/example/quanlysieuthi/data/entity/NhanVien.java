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
public class NhanVien {
    @Id
    private String ma;
    private String ten;
    private String cccd;
    private String sdt;
    private String diaChi;

    @JsonIgnore
    @OneToMany(mappedBy = "nhanVien")
    private Set<HoaDon> hoaDon;

    @JsonIgnore
    @OneToMany(mappedBy = "nhanVien")
    private Set<PhieuNhap> phieuNhap;
}

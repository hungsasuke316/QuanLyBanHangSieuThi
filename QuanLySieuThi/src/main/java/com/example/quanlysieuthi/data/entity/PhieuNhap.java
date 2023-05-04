package com.example.quanlysieuthi.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class PhieuNhap {
    @Id
    private String ma;
    private Date ngayLapPhieuNhap;
    private Integer soLuong;
    private Integer tongTien;

    @ManyToOne
    @JoinColumn(name = "ma_nhan_vien")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "ma_nha_cung_cap")
    private NhaCungCap nhaCungCap;

    @ManyToOne
    @JoinColumn(name = "ma_san_pham")
    private SanPham sanPham;
}

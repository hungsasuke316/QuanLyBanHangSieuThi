package com.example.quanlysieuthi.data.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HoaDon {
    @Id
    private String ma;
    private Date ngayLapHoaDon;
    private Integer soLuong;
    private Integer tongTien;

    @ManyToOne
    @JoinColumn(name = "ma_nhan_vien")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "ma_khach_hang")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "ma_san_pham")
    private SanPham sanPham;
}

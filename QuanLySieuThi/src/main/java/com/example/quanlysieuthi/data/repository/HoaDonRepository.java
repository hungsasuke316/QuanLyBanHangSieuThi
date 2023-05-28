package com.example.quanlysieuthi.data.repository;

import com.example.quanlysieuthi.data.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, String> {
    @Query("SELECT COALESCE(SUM(h.tongTien), 0) FROM HoaDon h WHERE YEAR(h.ngayLapHoaDon) = :year AND MONTH(h.ngayLapHoaDon) = :month")
    int getTongTienHoaDon(@Param("year") int year, @Param("month") int month);

    Page<HoaDon> findByMa(String ma, Pageable pageable);
    Page<HoaDon> findByNhanVien_Ma(String maNhanVien, Pageable pageable);
    Page<HoaDon> findBySanPham_Ma(String maSanPham, Pageable pageable);
    Page<HoaDon> findByKhachHang_Ma(String maNhaCungCap, Pageable pageable);



}

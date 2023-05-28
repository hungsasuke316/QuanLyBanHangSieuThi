package com.example.quanlysieuthi.data.repository;

import com.example.quanlysieuthi.data.entity.PhieuNhap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface PhieuNhapRepository extends JpaRepository<PhieuNhap, String> {
    @Query("SELECT COALESCE(SUM(h.tongTien), 0) FROM PhieuNhap h WHERE YEAR(h.ngayLapPhieuNhap) = :year AND MONTH(h.ngayLapPhieuNhap) = :month")
    int getTongTienPhieuNhap(@Param("year") int year, @Param("month") int month);

    Page<PhieuNhap> findByMa(String ma, Pageable pageable);
    Page<PhieuNhap> findByNhanVien_Ma(String maNhanVien, Pageable pageable);
    Page<PhieuNhap> findBySanPham_Ma(String maSanPham, Pageable pageable);
    Page<PhieuNhap> findByNhaCungCap_Ma(String maNhaCungCap, Pageable pageable);

}

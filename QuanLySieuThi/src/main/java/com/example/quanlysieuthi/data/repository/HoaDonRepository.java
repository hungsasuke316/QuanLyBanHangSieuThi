package com.example.quanlysieuthi.data.repository;

import com.example.quanlysieuthi.data.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, String> {
    @Query("SELECT COALESCE(SUM(h.tongTien), 0) FROM HoaDon h WHERE YEAR(h.ngayLapHoaDon) = :year AND MONTH(h.ngayLapHoaDon) = :month")
    int getTongTienHoaDon(@Param("year") int year, @Param("month") int month);
}

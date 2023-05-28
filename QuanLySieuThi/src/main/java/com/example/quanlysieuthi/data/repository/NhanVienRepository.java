package com.example.quanlysieuthi.data.repository;

import com.example.quanlysieuthi.data.entity.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, String> {
    @Query("SELECT n FROM NhanVien n WHERE n.ma = :ma AND n.ten = :ten")
    Page<NhanVien> findByMaAndTen(String ma, String ten, Pageable pageable);
    @Query("SELECT n FROM NhanVien n WHERE n.ma = :ma")
    Page<NhanVien> findByMa(String ma, Pageable pageable);

    @Query("SELECT n FROM NhanVien n WHERE n.ten = :ten")
    Page<NhanVien> findByTen(String ten, Pageable pageable);
}

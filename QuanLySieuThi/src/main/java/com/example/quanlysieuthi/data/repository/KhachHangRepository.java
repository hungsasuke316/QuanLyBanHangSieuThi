package com.example.quanlysieuthi.data.repository;

import com.example.quanlysieuthi.data.entity.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, String> {
    @Query("SELECT k FROM KhachHang k WHERE k.ma = :ma AND k.ten = :ten")
    Page<KhachHang> findByMaAndTen(String ma, String ten, Pageable pageable);
    @Query("SELECT k FROM KhachHang k WHERE k.ma = :ma")
    Page<KhachHang> findByMa(String ma, Pageable pageable);

    @Query("SELECT k FROM KhachHang k WHERE k.ten = :ten")
    Page<KhachHang> findByTen(String ten, Pageable pageable);
}

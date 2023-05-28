package com.example.quanlysieuthi.data.repository;

import com.example.quanlysieuthi.data.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, String> {
    @Query("SELECT s FROM SanPham s WHERE s.ma = :ma AND s.ten = :ten")
    Page<SanPham> findByMaAndTen(String ma, String ten, Pageable pageable);
    @Query("SELECT s FROM SanPham s WHERE s.ma = :ma")
    Page<SanPham> findByMa(String ma, Pageable pageable);

    @Query("SELECT s FROM SanPham s WHERE s.ten = :ten")
    Page<SanPham> findByTen(String ten, Pageable pageable);
}

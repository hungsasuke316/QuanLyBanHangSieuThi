package com.example.quanlysieuthi.data.repository;

import com.example.quanlysieuthi.data.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, String> {
}

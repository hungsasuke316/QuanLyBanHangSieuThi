package com.example.quanlysieuthi.data.repository;

import com.example.quanlysieuthi.data.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, String> {
}

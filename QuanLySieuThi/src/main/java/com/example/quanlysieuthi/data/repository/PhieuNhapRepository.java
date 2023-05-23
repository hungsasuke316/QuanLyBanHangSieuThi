package com.example.quanlysieuthi.data.repository;

import com.example.quanlysieuthi.data.entity.PhieuNhap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuNhapRepository extends JpaRepository<PhieuNhap, String> {
}

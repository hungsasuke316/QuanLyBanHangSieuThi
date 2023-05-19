package com.example.quanlysieuthi.data.repository;

import com.example.quanlysieuthi.data.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, String> {
}

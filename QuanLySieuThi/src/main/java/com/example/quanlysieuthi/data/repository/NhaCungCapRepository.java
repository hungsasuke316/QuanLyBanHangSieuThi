package com.example.quanlysieuthi.data.repository;

import com.example.quanlysieuthi.data.entity.NhaCungCap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhaCungCapRepository extends JpaRepository<NhaCungCap, String> {
}

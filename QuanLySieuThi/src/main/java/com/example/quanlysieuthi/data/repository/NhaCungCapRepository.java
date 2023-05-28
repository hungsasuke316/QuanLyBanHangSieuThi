package com.example.quanlysieuthi.data.repository;

import com.example.quanlysieuthi.data.entity.NhaCungCap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhaCungCapRepository extends JpaRepository<NhaCungCap, String> {
    @Query("SELECT n FROM NhaCungCap n WHERE n.ma = :ma AND n.ten = :ten")
    Page<NhaCungCap> findByMaAndTen(String ma, String ten, Pageable pageable);
    @Query("SELECT n FROM NhaCungCap n WHERE n.ma = :ma")
    Page<NhaCungCap> findByMa(String ma, Pageable pageable);

    @Query("SELECT n FROM NhaCungCap n WHERE n.ten = :ten")
    Page<NhaCungCap> findByTen(String ten, Pageable pageable);
}

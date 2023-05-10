package com.example.quanlysieuthi.service;

import com.example.quanlysieuthi.data.entity.NhaCungCap;
import com.example.quanlysieuthi.dto.request.NhaCungCapRequest;

import java.util.LinkedList;

public interface NhaCungCapService {
    public LinkedList<NhaCungCap> getAllNhaCungCap();
    public void creatNhaCungCap(NhaCungCapRequest dto);
    public void updateNhaCungCap(NhaCungCapRequest dto);
    public LinkedList<NhaCungCap> deleteNhaCungCap(String ma);

    public NhaCungCap getNhaCungCap(String ma);
}

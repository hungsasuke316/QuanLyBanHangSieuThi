package com.example.quanlysieuthi.service;

import com.example.quanlysieuthi.data.entity.NhaCungCap;
import com.example.quanlysieuthi.dto.request.NhaCungCapRequest;
import com.example.quanlysieuthi.service.impl.PagedLinkedList;

import java.util.List;


public interface NhaCungCapService {
    public PagedLinkedList<NhaCungCap> getAllNhaCungCap(Integer pageNumber, Integer pageSize);
    public void creatNhaCungCap(NhaCungCapRequest dto);
    public void updateNhaCungCap(NhaCungCapRequest dto);
    public void deleteNhaCungCap(String ma);
    public PagedLinkedList<NhaCungCap> searchNhaCungCap(String ma, String ten, Integer pageNumber, Integer pageSize);
    public NhaCungCap getNhaCungCap(String ma);
}

package com.example.quanlysieuthi.service.impl;

import com.example.quanlysieuthi.data.entity.NhaCungCap;
import com.example.quanlysieuthi.data.entity.PhieuNhap;
import com.example.quanlysieuthi.data.repository.NhaCungCapRepository;
import com.example.quanlysieuthi.data.repository.PhieuNhapRepository;
import com.example.quanlysieuthi.dto.request.NhaCungCapRequest;
import com.example.quanlysieuthi.exceptions.ResourceNotAcceptException;
import com.example.quanlysieuthi.exceptions.ResourceNotFoundException;
import com.example.quanlysieuthi.service.NhaCungCapService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class NhaCungCapServiceImpl implements NhaCungCapService {
    public final NhaCungCapRepository nhaCungCapRepository;
    public final PhieuNhapRepository phieuNhapRepository;
    public final ModelMapper modelMapper;

    public NhaCungCapServiceImpl(NhaCungCapRepository nhaCungCapRepository, PhieuNhapRepository phieuNhapRepository, ModelMapper modelMapper) {
        this.nhaCungCapRepository = nhaCungCapRepository;
        this.phieuNhapRepository = phieuNhapRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PagedLinkedList<NhaCungCap> getAllNhaCungCap(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<NhaCungCap> nhaCungCapPage = this.nhaCungCapRepository.findAll(pageable);
        List<NhaCungCap> nhaCungCapList = nhaCungCapPage.getContent();
        LinkedList<NhaCungCap> linkedList = convertToLinkedList(nhaCungCapList);

        int totalPages = nhaCungCapPage.getTotalPages();
        int currentPage = pageNumber;
        boolean hasPreviousPage = pageNumber > 0;
        boolean hasNextPage = pageNumber < totalPages - 1;

        return new PagedLinkedList<>(linkedList, currentPage, totalPages, hasPreviousPage, hasNextPage);
    }

    @Override
    public void creatNhaCungCap(NhaCungCapRequest dto) {
        List<NhaCungCap> nhaCungCapList = this.nhaCungCapRepository.findAll();
        LinkedList<NhaCungCap> linkedList = convertToLinkedList(nhaCungCapList);
        if (linkedList.isEmpty()){
            NhaCungCap nhaCungCap = modelMapper.map(dto, NhaCungCap.class);
            linkedList.addFirst(nhaCungCap);
            nhaCungCapRepository.saveAll(linkedList);
        }
        else {
            for (NhaCungCap nhaCungCap : linkedList){
                if (dto.getMa().equals(nhaCungCap.getMa())){
                    throw new ResourceNotAcceptException("Mã nhà cung cấp đã tồn tại");
                }
            }
            NhaCungCap nhaCungCap = modelMapper.map(dto, NhaCungCap.class);
            linkedList.addFirst(nhaCungCap);
            nhaCungCapRepository.saveAll(linkedList);
        }
    }

    @Override
    public NhaCungCap getNhaCungCap(String ma) {
        List<NhaCungCap> nhaCungCapList = this.nhaCungCapRepository.findAll();
        LinkedList<NhaCungCap> linkedList = convertToLinkedList(nhaCungCapList);
        NhaCungCap nhaCungCap = new NhaCungCap();
        if (linkedList.isEmpty()){
            throw new ResourceNotFoundException("Danh sach trong!!!");
        }
        else {
            for (NhaCungCap item : linkedList){
                if (ma.equals(item.getMa())){
                    nhaCungCap = item;
                }
            }
            return  nhaCungCap;
        }
    }

    @Override
    public void updateNhaCungCap(NhaCungCapRequest dto) {
        NhaCungCap nhaCungCap = modelMapper.map(dto, NhaCungCap.class);
        nhaCungCapRepository.save(nhaCungCap);
    }

    @Override
    public void deleteNhaCungCap(String ma) {
        NhaCungCap nhaCungCap =nhaCungCapRepository.findById(ma).orElse(null);
        if (nhaCungCap == null) {
            throw new ResourceNotFoundException("Không tìm thấy nhà cung cấp có mã " + ma);
        }

        List<PhieuNhap> phieuNhapList = this.phieuNhapRepository.findAll();
        boolean hasPhieuNhap = false;
        for (PhieuNhap phieuNhap : phieuNhapList) {
            if (ma.equals(phieuNhap.getSanPham().getMa())) {
                hasPhieuNhap = true;
                break;
            }
        }

        if (hasPhieuNhap) {
            throw new ResourceNotAcceptException("Nhà cung cấp có trong phiếu nhập. Không thể xóa!!!");
        }

        nhaCungCapRepository.delete(nhaCungCap);
    }

    @Override
    public PagedLinkedList<NhaCungCap> searchNhaCungCap(String ma, String ten, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<NhaCungCap> nhaCungCapPage;

        if (ma != null && !ma.isEmpty() && ten != null && !ten.isEmpty()) {
            nhaCungCapPage = nhaCungCapRepository.findByMaAndTen(ma, ten, pageable);
        } else if (ma != null && !ma.isEmpty()) {
            nhaCungCapPage = nhaCungCapRepository.findByMa(ma, pageable);
        } else if (ten != null && !ten.isEmpty()) {
            nhaCungCapPage = nhaCungCapRepository.findByTen(ten, pageable);
        } else {
            nhaCungCapPage = nhaCungCapRepository.findAll(pageable);
        }

        List<NhaCungCap> nhaCungCapList = nhaCungCapPage.getContent();
        LinkedList<NhaCungCap> linkedList = convertToLinkedList(nhaCungCapList);

        int totalPages = nhaCungCapPage.getTotalPages();
        int currentPage = pageNumber;
        boolean hasPreviousPage = pageNumber > 0;
        boolean hasNextPage = pageNumber < totalPages - 1;

        return new PagedLinkedList<>(linkedList, currentPage, totalPages, hasPreviousPage, hasNextPage);
    }

    public LinkedList<NhaCungCap> convertToLinkedList(List<NhaCungCap> nhaCungCapList){
        LinkedList<NhaCungCap> linkedList = new LinkedList<>(nhaCungCapList);
        return linkedList;
    }
}

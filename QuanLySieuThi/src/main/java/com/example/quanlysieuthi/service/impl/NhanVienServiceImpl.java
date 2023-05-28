package com.example.quanlysieuthi.service.impl;

import com.example.quanlysieuthi.data.entity.HoaDon;
import com.example.quanlysieuthi.data.entity.NhanVien;
import com.example.quanlysieuthi.data.entity.PhieuNhap;
import com.example.quanlysieuthi.data.repository.HoaDonRepository;
import com.example.quanlysieuthi.data.repository.NhanVienRepository;
import com.example.quanlysieuthi.data.repository.PhieuNhapRepository;
import com.example.quanlysieuthi.dto.request.NhanVienRequest;
import com.example.quanlysieuthi.exceptions.ResourceNotAcceptException;
import com.example.quanlysieuthi.exceptions.ResourceNotFoundException;
import com.example.quanlysieuthi.service.NhanVienService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class NhanVienServiceImpl implements NhanVienService {

    public final NhanVienRepository nhanVienRepository;
    public final PhieuNhapRepository phieuNhapRepository;
    public final HoaDonRepository hoaDonRepository;
    public final ModelMapper modelMapper;

    public NhanVienServiceImpl(NhanVienRepository nhanVienRepository, PhieuNhapRepository phieuNhapRepository, HoaDonRepository hoaDonRepository, ModelMapper modelMapper) {
        this.nhanVienRepository = nhanVienRepository;
        this.phieuNhapRepository = phieuNhapRepository;
        this.hoaDonRepository = hoaDonRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PagedLinkedList<NhanVien> getAllNhanVien(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<NhanVien> nhanVienPage = this.nhanVienRepository.findAll(pageable);
        List<NhanVien> nhanVienList = nhanVienPage.getContent();
        LinkedList<NhanVien> linkedList = convertToLinkedList(nhanVienList);

        int totalPages = nhanVienPage.getTotalPages();
        int currentPage = pageNumber;
        boolean hasPreviousPage = pageNumber > 0;
        boolean hasNextPage = pageNumber < totalPages - 1;

        return new PagedLinkedList<>(linkedList, currentPage, totalPages, hasPreviousPage, hasNextPage);
    }

    @Override
    public void creatNhanVien(NhanVienRequest dto) {
        List<NhanVien> nhanVienList = this.nhanVienRepository.findAll();
        LinkedList<NhanVien> linkedList = convertToLinkedList(nhanVienList);
        if (dto.getCccd().length() != 9 || dto.getCccd().length() != 13){
            throw new ResourceNotAcceptException("Số căn cước công dân không hợp lệ!!");
        }
        if (linkedList.isEmpty()){
            NhanVien nhanVien = modelMapper.map(dto, NhanVien.class);
            linkedList.addFirst(nhanVien);
            nhanVienRepository.saveAll(linkedList);
        }
        else {
            for (NhanVien nhanVien : linkedList){
                if (dto.getMa().equals(nhanVien.getMa())){
                    throw new ResourceNotAcceptException("Mã nhân viên đã tồn tại!!");
                }
            }
            NhanVien nhanVien = modelMapper.map(dto, NhanVien.class);
            linkedList.addFirst(nhanVien);
            nhanVienRepository.saveAll(linkedList);
        }

    }

    @Override
    public void updateNhanVien(NhanVienRequest dto) {
        NhanVien nhanVien = modelMapper.map(dto, NhanVien.class);
        nhanVienRepository.save(nhanVien);

    }

    @Override
    public void deleteNhanVien(String ma) {
        NhanVien nhanVien = nhanVienRepository.findById(ma).orElse(null);
        if (nhanVien == null) {
            throw new ResourceNotFoundException("Không tìm thấy nhân viên có mã " + ma);
        }

        List<PhieuNhap> phieuNhapList = this.phieuNhapRepository.findAll();
        boolean hasPhieuNhap = false;
        for (PhieuNhap phieuNhap : phieuNhapList) {
            if (ma.equals(phieuNhap.getSanPham().getMa())) {
                hasPhieuNhap = true;
                break;
            }
        }

        List<HoaDon> hoaDonList = this.hoaDonRepository.findAll();
        boolean hasHoaDon = false;
        for (HoaDon hoaDon : hoaDonList) {
            if (ma.equals(hoaDon.getSanPham().getMa())) {
                hasHoaDon = true;
                break;
            }
        }

        if (hasPhieuNhap) {
            throw new ResourceNotAcceptException("Nhân viên có trong phiếu nhập. Không thể xóa!!!");
        }

        if (hasHoaDon) {
            throw new ResourceNotAcceptException("Nhân viên có trong hóa đơn. Không thể xóa!!!");
        }

        nhanVienRepository.delete(nhanVien);
    }

    @Override
    public NhanVien getNhanVien(String ma) {
        List<NhanVien> nhanVienList = this.nhanVienRepository.findAll();
        LinkedList<NhanVien> linkedList = convertToLinkedList(nhanVienList);
        NhanVien nhanVien = new NhanVien();
        if (linkedList.isEmpty()){
            throw new ResourceNotFoundException("Danh sach trong!!!");
        }
        else {
            for (NhanVien item : linkedList){
                if (ma.equals(item.getMa())){
                    nhanVien = item;
                }
            }
            return  nhanVien;
        }
    }

    @Override
    public PagedLinkedList<NhanVien> searchNhanVien(String ma, String ten, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<NhanVien> nhanVienPage;

        if (ma != null && !ma.isEmpty() && ten != null && !ten.isEmpty()) {
            nhanVienPage = nhanVienRepository.findByMaAndTen(ma, ten, pageable);
        } else if (ma != null && !ma.isEmpty()) {
            nhanVienPage = nhanVienRepository.findByMa(ma, pageable);
        } else if (ten != null && !ten.isEmpty()) {
            nhanVienPage = nhanVienRepository.findByTen(ten, pageable);
        } else {
            nhanVienPage = nhanVienRepository.findAll(pageable);
        }

        List<NhanVien> nhanVienList = nhanVienPage.getContent();
        LinkedList<NhanVien> linkedList = convertToLinkedList(nhanVienList);

        int totalPages = nhanVienPage.getTotalPages();
        int currentPage = pageNumber;
        boolean hasPreviousPage = pageNumber > 0;
        boolean hasNextPage = pageNumber < totalPages - 1;

        return new PagedLinkedList<>(linkedList, currentPage, totalPages, hasPreviousPage, hasNextPage);
    }

    public LinkedList<NhanVien> convertToLinkedList(List<NhanVien> nhanVienList){
        LinkedList<NhanVien> linkedList = new LinkedList<>(nhanVienList);
        return linkedList;
    }
}

package com.example.quanlysieuthi.service.impl;

import com.example.quanlysieuthi.data.entity.*;
import com.example.quanlysieuthi.data.repository.*;
import com.example.quanlysieuthi.dto.request.PhieuNhapRequest;
import com.example.quanlysieuthi.exceptions.ResourceNotAcceptException;
import com.example.quanlysieuthi.exceptions.ResourceNotFoundException;
import com.example.quanlysieuthi.service.PhieuNhapService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class PhieuNhapServiceImpl implements PhieuNhapService {

    private final PhieuNhapRepository phieuNhapRepository;
    private final NhanVienRepository nhanVienRepository;
    private final NhaCungCapRepository nhaCungCapRepository;
    private final SanPhamRepository sanPhamRepository;
    private final ModelMapper modelMapper;

    public PhieuNhapServiceImpl(PhieuNhapRepository phieuNhapRepository, NhanVienRepository nhanVienRepository,
                                NhaCungCapRepository nhaCungCapRepository, SanPhamRepository sanPhamRepository,
                                ModelMapper modelMapper) {
        this.phieuNhapRepository = phieuNhapRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.nhaCungCapRepository = nhaCungCapRepository;
        this.sanPhamRepository = sanPhamRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PagedLinkedList<PhieuNhap> getAllPhieuNhap(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<PhieuNhap> phieuNhapPage = this.phieuNhapRepository.findAll(pageable);
        List<PhieuNhap> phieuNhapList = phieuNhapPage.getContent();
        LinkedList<PhieuNhap> linkedList = convertToLinkedList(phieuNhapList);

        int totalPages = phieuNhapPage.getTotalPages();
        int currentPage = pageNumber;
        boolean hasPreviousPage = pageNumber > 0;
        boolean hasNextPage = pageNumber < totalPages - 1;

        return new PagedLinkedList<>(linkedList, currentPage, totalPages, hasPreviousPage, hasNextPage);
    }

    @Override
    public void createPhieuNhap(PhieuNhapRequest dto) {
        List<PhieuNhap> phieuNhapList = this.phieuNhapRepository.findAll();
        LinkedList<PhieuNhap> linkedList = convertToLinkedList(phieuNhapList);
        PhieuNhap phieuNhap = modelMapper.map(dto, PhieuNhap.class);

        NhanVien nhanVien = this.nhanVienRepository.findById(dto.getNhanVien())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy nhân viên" ));
        NhaCungCap nhaCungCap = this.nhaCungCapRepository.findById(dto.getNhaCungCap())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy nhà cung cấp" ));
        SanPham sanPham = this.sanPhamRepository.findById(dto.getSanPham())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm" ));

        phieuNhap.setNhanVien(nhanVien);
        phieuNhap.setNhaCungCap(nhaCungCap);
        phieuNhap.setSanPham(sanPham);

        Date ngayGioLap = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String ngayGioLapFormatted = sdf.format(ngayGioLap);
        try {
            Date ngayGioLapParsed = sdf.parse(ngayGioLapFormatted);
            phieuNhap.setNgayLapPhieuNhap(ngayGioLapParsed);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        phieuNhap.setTongTien(sanPham.getDonGia() * dto.getSoLuong());

        if (linkedList.isEmpty()){
            linkedList.addFirst(phieuNhap);
            phieuNhapRepository.saveAll(linkedList);
            sanPham.setSoLuongTon(sanPham.getSoLuongTon() + phieuNhap.getSoLuong());
            sanPhamRepository.save(sanPham);
        }
        else {
            for (PhieuNhap item : linkedList){
                if (dto.getMa().equals(item.getMa())){
                    throw new ResourceNotAcceptException("Mã phiếu nhập đã tồn tại!");
                }
            }

            linkedList.addFirst(phieuNhap);
            phieuNhapRepository.saveAll(linkedList);
            sanPham.setSoLuongTon(sanPham.getSoLuongTon() + phieuNhap.getSoLuong());
            sanPhamRepository.save(sanPham);
        }

    }

    @Override
    public PhieuNhap getPhieuNhap(String ma) {
        List<PhieuNhap> phieuNhapList = this.phieuNhapRepository.findAll();
        LinkedList<PhieuNhap> linkedList = convertToLinkedList(phieuNhapList);
        PhieuNhap phieuNhap = new PhieuNhap();
        if (linkedList.isEmpty()){
            throw new ResourceNotFoundException("Danh sach trong!!!");
        }
        else {
            for (PhieuNhap item : linkedList){
                if (ma.equals(item.getMa())){
                    phieuNhap = item;
                }
            }
            return  phieuNhap;
        }
    }

    @Override
    public PagedLinkedList<PhieuNhap> searchPhieuNhap(String maPN, String maNV, String maSP, String maNCC, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<PhieuNhap> pagedPhieuNhap;

        if (maPN != null && !maPN.isEmpty()) {
            pagedPhieuNhap = phieuNhapRepository.findByMa(maPN, pageable);
        } else if (maNV != null && !maNV.isEmpty()) {
            pagedPhieuNhap = phieuNhapRepository.findByNhanVien_Ma(maNV, pageable);
        } else if (maSP != null && !maSP.isEmpty()) {
            pagedPhieuNhap = phieuNhapRepository.findBySanPham_Ma(maSP, pageable);
        } else if (maNCC != null && !maNCC.isEmpty()) {
            pagedPhieuNhap = phieuNhapRepository.findByNhaCungCap_Ma(maNCC, pageable);
        } else {
            pagedPhieuNhap = phieuNhapRepository.findAll(pageable);
        }

        List<PhieuNhap> phieuNhapList = pagedPhieuNhap.getContent();
        LinkedList<PhieuNhap> linkedList = new LinkedList<>(phieuNhapList);

        int totalPages = pagedPhieuNhap.getTotalPages();
        int currentPage = pageNumber;
        boolean hasPreviousPage = pageNumber > 0;
        boolean hasNextPage = pageNumber < totalPages - 1;

        return new PagedLinkedList<>(linkedList, currentPage, totalPages, hasPreviousPage, hasNextPage);
    }

    public LinkedList<PhieuNhap> convertToLinkedList(List<PhieuNhap> phieuNhapList){
        LinkedList<PhieuNhap> linkedList = new LinkedList<>(phieuNhapList);
        return linkedList;
    }
}

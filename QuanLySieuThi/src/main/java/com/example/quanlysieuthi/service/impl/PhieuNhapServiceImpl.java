package com.example.quanlysieuthi.service.impl;

import com.example.quanlysieuthi.data.entity.*;
import com.example.quanlysieuthi.data.repository.*;
import com.example.quanlysieuthi.dto.request.PhieuNhapRequest;
import com.example.quanlysieuthi.exceptions.ResourceNotAcceptException;
import com.example.quanlysieuthi.exceptions.ResourceNotFoundException;
import com.example.quanlysieuthi.service.PhieuNhapService;
import org.modelmapper.ModelMapper;
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
    public LinkedList<PhieuNhap> getAllPhieuNhap() {
        List<PhieuNhap> phieuNhapList = this.phieuNhapRepository.findAll();
        LinkedList<PhieuNhap> linkedList = convertToLinkedList(phieuNhapList);
        return linkedList;
    }

    @Override
    public void createPhieuNhap(PhieuNhapRequest dto) {
        List<PhieuNhap> phieuNhapList = this.phieuNhapRepository.findAll();
        LinkedList<PhieuNhap> linkedList = convertToLinkedList(phieuNhapList);
        PhieuNhap phieuNhap = modelMapper.map(dto, PhieuNhap.class);

        NhanVien nhanVien = this.nhanVienRepository.findById(dto.getNhanVien())
                .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay nhan vien" ));
        NhaCungCap nhaCungCap = this.nhaCungCapRepository.findById(dto.getNhaCungCap())
                .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay nha cung cap" ));
        SanPham sanPham = this.sanPhamRepository.findById(dto.getSanPham())
                .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay san pham" ));

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
        }
        else {
            for (PhieuNhap item : linkedList){
                if (dto.getMa().equals(item.getMa())){
                    throw new ResourceNotAcceptException("Ma phieu nhap da ton tai!");
                }
            }

            linkedList.addFirst(phieuNhap);
            phieuNhapRepository.saveAll(linkedList);
        }

    }

    public LinkedList<PhieuNhap> convertToLinkedList(List<PhieuNhap> phieuNhapList){
        LinkedList<PhieuNhap> linkedList = new LinkedList<>(phieuNhapList);
        return linkedList;
    }
}

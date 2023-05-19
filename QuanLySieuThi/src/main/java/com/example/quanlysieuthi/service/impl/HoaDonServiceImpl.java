package com.example.quanlysieuthi.service.impl;

import com.example.quanlysieuthi.data.entity.HoaDon;
import com.example.quanlysieuthi.data.entity.KhachHang;
import com.example.quanlysieuthi.data.entity.NhanVien;
import com.example.quanlysieuthi.data.entity.SanPham;
import com.example.quanlysieuthi.data.repository.HoaDonRepository;
import com.example.quanlysieuthi.data.repository.KhachHangRepository;
import com.example.quanlysieuthi.data.repository.NhanVienRepository;
import com.example.quanlysieuthi.data.repository.SanPhamRepository;
import com.example.quanlysieuthi.dto.request.HoaDonRequest;
import com.example.quanlysieuthi.exceptions.ResourceNotAcceptException;
import com.example.quanlysieuthi.exceptions.ResourceNotFoundException;
import com.example.quanlysieuthi.service.HoaDonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class HoaDonServiceImpl implements HoaDonService {
    private final HoaDonRepository hoaDonRepository;
    private final NhanVienRepository nhanVienRepository;
    private final KhachHangRepository khachHangRepository;
    private final SanPhamRepository sanPhamRepository;
    private final ModelMapper modelMapper;

    public HoaDonServiceImpl(HoaDonRepository hoaDonRepository, NhanVienRepository nhanVienRepository,
                             KhachHangRepository khachHangRepository, SanPhamRepository sanPhamRepository,
                             ModelMapper modelMapper) {
        this.hoaDonRepository = hoaDonRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.khachHangRepository = khachHangRepository;
        this.sanPhamRepository = sanPhamRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LinkedList<HoaDon> getAllHoaDon() {
        List<HoaDon> hoaDonList = this.hoaDonRepository.findAll();
        LinkedList<HoaDon> linkedList = convertToLinkedList(hoaDonList);
        return linkedList;
    }

    @Override
    public void createHoaDon(HoaDonRequest dto) {
        List<HoaDon> hoaDonList = this.hoaDonRepository.findAll();
        LinkedList<HoaDon> linkedList = convertToLinkedList(hoaDonList);
        HoaDon hoaDon = modelMapper.map(dto, HoaDon.class);

        NhanVien nhanVien = this.nhanVienRepository.findById(dto.getNhanVien())
                .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay nhan vien" ));
        KhachHang khachHang = this.khachHangRepository.findById(dto.getKhachHang())
                .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay khach hang" ));
        SanPham sanPham = this.sanPhamRepository.findById(dto.getSanPham())
                .orElseThrow(() -> new ResourceNotFoundException("Khong tim thay san pham" ));

        hoaDon.setNhanVien(nhanVien);
        hoaDon.setKhachHang(khachHang);
        hoaDon.setSanPham(sanPham);

        Date ngayGioLap = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String ngayGioLapFormatted = sdf.format(ngayGioLap);
        try {
            Date ngayGioLapParsed = sdf.parse(ngayGioLapFormatted);
            hoaDon.setNgayLapHoaDon(ngayGioLapParsed);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        hoaDon.setTongTien(sanPham.getDonGia() * dto.getSoLuong());

        if (linkedList.isEmpty()){
            linkedList.addFirst(hoaDon);
            hoaDonRepository.saveAll(linkedList);
        }
        else {
            for (HoaDon item : linkedList){
                if (dto.getMa().equals(item.getMa())){
                    throw new ResourceNotAcceptException("Ma hoa don da ton tai!");
                }
            }

            linkedList.addFirst(hoaDon);
            hoaDonRepository.saveAll(linkedList);
        }

    }

    public LinkedList<HoaDon> convertToLinkedList(List<HoaDon> hoaDonList){
        LinkedList<HoaDon> linkedList = new LinkedList<>(hoaDonList);
        return linkedList;
    }
}

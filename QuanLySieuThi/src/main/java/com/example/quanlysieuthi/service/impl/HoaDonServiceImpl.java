package com.example.quanlysieuthi.service.impl;

import com.example.quanlysieuthi.data.entity.*;
import com.example.quanlysieuthi.data.repository.HoaDonRepository;
import com.example.quanlysieuthi.data.repository.KhachHangRepository;
import com.example.quanlysieuthi.data.repository.NhanVienRepository;
import com.example.quanlysieuthi.data.repository.SanPhamRepository;
import com.example.quanlysieuthi.dto.request.HoaDonRequest;
import com.example.quanlysieuthi.exceptions.ResourceNotAcceptException;
import com.example.quanlysieuthi.exceptions.ResourceNotFoundException;
import com.example.quanlysieuthi.service.HoaDonService;
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
    public PagedLinkedList<HoaDon> getAllHoaDon(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<HoaDon> hoaDonPage = this.hoaDonRepository.findAll(pageable);
        List<HoaDon> hoaDonList = hoaDonPage.getContent();
        LinkedList<HoaDon> linkedList = convertToLinkedList(hoaDonList);

        int totalPages = hoaDonPage.getTotalPages();
        int currentPage = pageNumber;
        boolean hasPreviousPage = pageNumber > 0;
        boolean hasNextPage = pageNumber < totalPages - 1;

        return new PagedLinkedList<>(linkedList, currentPage, totalPages, hasPreviousPage, hasNextPage);
    }

    @Override
    public void createHoaDon(HoaDonRequest dto) {
        List<HoaDon> hoaDonList = this.hoaDonRepository.findAll();
        LinkedList<HoaDon> linkedList = convertToLinkedList(hoaDonList);
        HoaDon hoaDon = modelMapper.map(dto, HoaDon.class);

        NhanVien nhanVien = this.nhanVienRepository.findById(dto.getNhanVien())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy nhân viên" ));
        KhachHang khachHang = this.khachHangRepository.findById(dto.getKhachHang())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy khách hàng" ));
        SanPham sanPham = this.sanPhamRepository.findById(dto.getSanPham())
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm" ));

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
            sanPham.setSoLuongTon(sanPham.getSoLuongTon() - hoaDon.getSoLuong());
            sanPhamRepository.save(sanPham);
        }
        else {
            for (HoaDon item : linkedList){
                if (dto.getMa().equals(item.getMa())){
                    throw new ResourceNotAcceptException("Mã hóa đơn đã tồn tại!");
                }
            }

            linkedList.addFirst(hoaDon);
            hoaDonRepository.saveAll(linkedList);
            sanPham.setSoLuongTon(sanPham.getSoLuongTon() - hoaDon.getSoLuong());
            sanPhamRepository.save(sanPham);
        }

    }

    @Override
    public HoaDon getHoaDon(String ma) {
        List<HoaDon> hoaDonList = this.hoaDonRepository.findAll();
        LinkedList<HoaDon> linkedList = convertToLinkedList(hoaDonList);
        HoaDon hoaDon = new HoaDon();
        if (linkedList.isEmpty()){
            throw new ResourceNotFoundException("Danh sach trong!!!");
        }
        else {
            for (HoaDon item : linkedList){
                if (ma.equals(item.getMa())){
                    hoaDon = item;
                }
            }
            return  hoaDon;
        }
    }

    public LinkedList<HoaDon> convertToLinkedList(List<HoaDon> hoaDonList){
        LinkedList<HoaDon> linkedList = new LinkedList<>(hoaDonList);
        return linkedList;
    }
}

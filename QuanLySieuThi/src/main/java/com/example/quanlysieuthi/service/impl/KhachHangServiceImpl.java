package com.example.quanlysieuthi.service.impl;

import com.example.quanlysieuthi.data.entity.HoaDon;
import com.example.quanlysieuthi.data.entity.KhachHang;
import com.example.quanlysieuthi.data.repository.HoaDonRepository;
import com.example.quanlysieuthi.data.repository.KhachHangRepository;
import com.example.quanlysieuthi.dto.request.KhachHangRequest;
import com.example.quanlysieuthi.exceptions.ResourceNotAcceptException;
import com.example.quanlysieuthi.exceptions.ResourceNotFoundException;
import com.example.quanlysieuthi.service.KhachHangService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    public final KhachHangRepository khachHangRepository;
    public final HoaDonRepository hoaDonRepository;
    public final ModelMapper modelMapper;

    public KhachHangServiceImpl(KhachHangRepository khachHangRepository, HoaDonRepository hoaDonRepository, ModelMapper modelMapper) {
        this.khachHangRepository = khachHangRepository;
        this.hoaDonRepository = hoaDonRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PagedLinkedList<KhachHang> getAllKhachHang(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<KhachHang> khachHangPage = this.khachHangRepository.findAll(pageable);
        List<KhachHang> khachHangList = khachHangPage.getContent();
        LinkedList<KhachHang> linkedList = convertToLinkedList(khachHangList);

        int totalPages = khachHangPage.getTotalPages();
        int currentPage = pageNumber;
        boolean hasPreviousPage = pageNumber > 0;
        boolean hasNextPage = pageNumber < totalPages - 1;

        return new PagedLinkedList<>(linkedList, currentPage, totalPages, hasPreviousPage, hasNextPage);
    }

    @Override
    public void creatKhachHang(KhachHangRequest dto) {
        List<KhachHang> khachHangList = this.khachHangRepository.findAll();
        LinkedList<KhachHang> linkedList = convertToLinkedList(khachHangList);
        if (dto.getCccd().length() != 9 && dto.getCccd().length() != 12){
            throw new ResourceNotAcceptException("Số căn cước công dân không hợp lệ!!. CMND/CCCD có 9 hoặc 12 số.");
        }

        if (dto.getSdt().length() != 10){
            throw new ResourceNotAcceptException("Số điện thoại không hợp lệ!!. Số điện thoại có 10 số.");
        }

        if (linkedList.isEmpty()){
            KhachHang khachHang = modelMapper.map(dto, KhachHang.class);
            linkedList.addFirst(khachHang);
            khachHangRepository.saveAll(linkedList);
        }
        else {
            for (KhachHang khachHang : linkedList){
                if (dto.getMa().equals(khachHang.getMa())){
                    throw new ResourceNotAcceptException("Mã khách hàng đã tồn tại!!");
                }
            }
            KhachHang khachHang = modelMapper.map(dto, KhachHang.class);
            linkedList.addFirst(khachHang);
            khachHangRepository.saveAll(linkedList);
        }

    }

    @Override
    public void updateKhachHang(KhachHangRequest dto) {
        if (dto.getCccd().length() != 9 && dto.getCccd().length() != 12){
            throw new ResourceNotAcceptException("Số căn cước công dân không hợp lệ!!. CMND/CCCD có 9 hoặc 12 số.");
        }

        if (dto.getSdt().length() != 10){
            throw new ResourceNotAcceptException("Số điện thoại không hợp lệ!!. Số điện thoại có 10 số.");
        }
        KhachHang khachHang = modelMapper.map(dto, KhachHang.class);
        khachHangRepository.save(khachHang);

    }

    @Override
    public void deleteKhachHang(String ma) {
        KhachHang khachHang =khachHangRepository.findById(ma).orElse(null);
        if (khachHang == null) {
            throw new ResourceNotFoundException("Không tìm thấy khách hàng có mã " + ma);
        }

        List<HoaDon> hoaDonList = this.hoaDonRepository.findAll();
        boolean hasHoaDon = false;
        for (HoaDon hoaDon : hoaDonList) {
            if (ma.equals(hoaDon.getKhachHang().getMa())) {
                hasHoaDon = true;
                break;
            }
        }

        if (hasHoaDon) {
            throw new ResourceNotAcceptException("Khách hàng có trong hóa đơn. Không thể xóa!!!");
        }

        khachHangRepository.delete(khachHang);
    }

    @Override
    public KhachHang getKhachHang(String ma) {
        List<KhachHang> khachHangList = this.khachHangRepository.findAll();
        LinkedList<KhachHang> linkedList = convertToLinkedList(khachHangList);
        KhachHang khachHang = new KhachHang();
        if (linkedList.isEmpty()){
            throw new ResourceNotFoundException("Danh sach trong!!!");
        }
        else {
            for (KhachHang item : linkedList){
                if (ma.equals(item.getMa())){
                    khachHang = item;
                }
            }
            return  khachHang;
        }
    }

    @Override
    public PagedLinkedList<KhachHang> searchKhachHang(String ma, String ten, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<KhachHang> khachHangPage;

        if (ma != null && !ma.isEmpty() && ten != null && !ten.isEmpty()) {
            khachHangPage = khachHangRepository.findByMaAndTen(ma, ten, pageable);
        } else if (ma != null && !ma.isEmpty()) {
            khachHangPage = khachHangRepository.findByMa(ma, pageable);
        } else if (ten != null && !ten.isEmpty()) {
            khachHangPage = khachHangRepository.findByTen(ten, pageable);
        } else {
            khachHangPage = khachHangRepository.findAll(pageable);
        }

        List<KhachHang> khachHangList = khachHangPage.getContent();
        LinkedList<KhachHang> linkedList = convertToLinkedList(khachHangList);

        int totalPages = khachHangPage.getTotalPages();
        int currentPage = pageNumber;
        boolean hasPreviousPage = pageNumber > 0;
        boolean hasNextPage = pageNumber < totalPages - 1;

        return new PagedLinkedList<>(linkedList, currentPage, totalPages, hasPreviousPage, hasNextPage);
    }

    public LinkedList<KhachHang> convertToLinkedList(List<KhachHang> khachHangList){
        LinkedList<KhachHang> linkedList = new LinkedList<>(khachHangList);
        return linkedList;
    }
}

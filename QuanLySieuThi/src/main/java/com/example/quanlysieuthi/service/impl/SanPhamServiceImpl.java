package com.example.quanlysieuthi.service.impl;

import com.example.quanlysieuthi.data.entity.HoaDon;
import com.example.quanlysieuthi.data.entity.KhachHang;
import com.example.quanlysieuthi.data.entity.PhieuNhap;
import com.example.quanlysieuthi.data.entity.SanPham;
import com.example.quanlysieuthi.data.repository.HoaDonRepository;
import com.example.quanlysieuthi.data.repository.PhieuNhapRepository;
import com.example.quanlysieuthi.data.repository.SanPhamRepository;
import com.example.quanlysieuthi.dto.request.SanPhamRequest;
import com.example.quanlysieuthi.exceptions.ResourceNotAcceptException;
import com.example.quanlysieuthi.exceptions.ResourceNotFoundException;
import com.example.quanlysieuthi.service.SanPhamService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SanPhamServiceImpl implements SanPhamService {
    private final SanPhamRepository sanPhamRepository;
    private final PhieuNhapRepository phieuNhapRepository;
    private final HoaDonRepository hoaDonRepository;
    private final ModelMapper modelMapper;

    public SanPhamServiceImpl(SanPhamRepository sanPhamRepository, PhieuNhapRepository phieuNhapRepository, HoaDonRepository hoaDonRepository, ModelMapper modelMapper) {
        this.sanPhamRepository = sanPhamRepository;
        this.phieuNhapRepository = phieuNhapRepository;
        this.hoaDonRepository = hoaDonRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PagedLinkedList<SanPham> getAllSanPham(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<SanPham> sanPhamPage = this.sanPhamRepository.findAll(pageable);
        List<SanPham> sanPhamList = sanPhamPage.getContent();
        LinkedList<SanPham> linkedList = convertToLinkedList(sanPhamList);

        int totalPages = sanPhamPage.getTotalPages();
        int currentPage = pageNumber;
        boolean hasPreviousPage = pageNumber > 0;
        boolean hasNextPage = pageNumber < totalPages - 1;
        return new PagedLinkedList<>(linkedList, currentPage, totalPages, hasPreviousPage, hasNextPage);
    }

    @Override
    public void createSanPham(SanPhamRequest dto) {
        List<SanPham> sanPhamList = this.sanPhamRepository.findAll();
        LinkedList<SanPham> linkedList = convertToLinkedList(sanPhamList);
        if (linkedList.isEmpty()){
            SanPham sanPham = modelMapper.map(dto, SanPham.class);
            sanPham.setSoLuongTon(0);
            linkedList.addFirst(sanPham);
            sanPhamRepository.saveAll(linkedList);
        }
        else {
            for (SanPham sanPham : linkedList){
                if (dto.getMa().equals(sanPham.getMa())){
                    throw new ResourceNotAcceptException("Mã sản phẩm đã tồn tại. Vui lòng nhập lại!!!");
                }
            }
            SanPham sanPham = modelMapper.map(dto, SanPham.class);
            sanPham.setSoLuongTon(0);
            linkedList.addFirst(sanPham);
            sanPhamRepository.saveAll(linkedList);
        }

    }

    @Override
    public void updateSanPham(SanPhamRequest dto) {
        SanPham sanPham = modelMapper.map(dto, SanPham.class);
        sanPhamRepository.save(sanPham);

    }

    @Override
    public void deleteSanPham(String ma) {
        SanPham sanPham = sanPhamRepository.findById(ma).orElse(null);
        if (sanPham == null) {
            throw new ResourceNotFoundException("Không tìm thấy sản phẩm có mã " + ma);
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
            throw new ResourceNotAcceptException("Sản phẩm có trong phiếu nhập. Không thể xóa!!!");
        }

        if (hasHoaDon) {
            throw new ResourceNotAcceptException("Sản phẩm có trong hóa đơn. Không thể xóa!!!");
        }

        sanPhamRepository.delete(sanPham);
    }

    @Override
    public SanPham getSanPham(String ma) {
        List<SanPham> sanPhamList = this.sanPhamRepository.findAll();
        LinkedList<SanPham> linkedList = convertToLinkedList(sanPhamList);
        SanPham sanPham = new SanPham();
        if (linkedList.isEmpty()){
            throw new ResourceNotFoundException("Danh sach trong!!!");
        }
        else {
            for (SanPham item : linkedList){
                if (ma.equals(item.getMa())){
                    sanPham = item;
                }
            }
            return  sanPham;
        }
    }

    @Override
    public PagedLinkedList<SanPham> searchSanPham(String ma, String ten, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<SanPham> sanPhamPage;

        if (ma != null && !ma.isEmpty() && ten != null && !ten.isEmpty()) {
            sanPhamPage = sanPhamRepository.findByMaAndTen(ma, ten, pageable);
        } else if (ma != null && !ma.isEmpty()) {
            sanPhamPage = sanPhamRepository.findByMa(ma, pageable);
        } else if (ten != null && !ten.isEmpty()) {
            sanPhamPage = sanPhamRepository.findByTen(ten, pageable);
        } else {
            sanPhamPage = sanPhamRepository.findAll(pageable);
        }

        List<SanPham> sanPhamList = sanPhamPage.getContent();
        LinkedList<SanPham> linkedList = convertToLinkedList(sanPhamList);

        int totalPages = sanPhamPage.getTotalPages();
        int currentPage = pageNumber;
        boolean hasPreviousPage = pageNumber > 0;
        boolean hasNextPage = pageNumber < totalPages - 1;

        return new PagedLinkedList<>(linkedList, currentPage, totalPages, hasPreviousPage, hasNextPage);
    }

    public LinkedList<SanPham> convertToLinkedList(List<SanPham> sanPhamList){
        LinkedList<SanPham> linkedList = new LinkedList<>(sanPhamList);
        return linkedList;
    }
}

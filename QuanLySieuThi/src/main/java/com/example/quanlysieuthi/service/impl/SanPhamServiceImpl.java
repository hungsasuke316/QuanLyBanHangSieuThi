package com.example.quanlysieuthi.service.impl;

import com.example.quanlysieuthi.data.entity.SanPham;
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
    private final ModelMapper modelMapper;

    public SanPhamServiceImpl(SanPhamRepository sanPhamRepository, ModelMapper modelMapper) {
        this.sanPhamRepository = sanPhamRepository;
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
            linkedList.addFirst(sanPham);
            sanPhamRepository.saveAll(linkedList);
        }
        else {
            for (SanPham sanPham : linkedList){
                if (dto.getMa().equals(sanPham.getMa())){
                    throw new ResourceNotAcceptException("San Pham already exist");
                }
            }
            SanPham sanPham = modelMapper.map(dto, SanPham.class);
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
    public LinkedList<SanPham> deleteSanPham(String ma) {
        SanPham sanPham =sanPhamRepository.findById(ma).get();
        sanPhamRepository.delete(sanPham);

        List<SanPham> sanPhamList = this.sanPhamRepository.findAll();
        LinkedList<SanPham> linkedList = convertToLinkedList(sanPhamList);

        return linkedList;
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

    public LinkedList<SanPham> convertToLinkedList(List<SanPham> sanPhamList){
        LinkedList<SanPham> linkedList = new LinkedList<>(sanPhamList);
        return linkedList;
    }
}

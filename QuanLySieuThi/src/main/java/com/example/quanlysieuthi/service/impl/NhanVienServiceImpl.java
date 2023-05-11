package com.example.quanlysieuthi.service.impl;

import com.example.quanlysieuthi.data.entity.NhanVien;
import com.example.quanlysieuthi.data.repository.NhanVienRepository;
import com.example.quanlysieuthi.dto.request.NhanVienRequest;
import com.example.quanlysieuthi.exceptions.ResourceNotAcceptException;
import com.example.quanlysieuthi.exceptions.ResourceNotFoundException;
import com.example.quanlysieuthi.service.NhanVienService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class NhanVienServiceImpl implements NhanVienService {

    public final NhanVienRepository nhanVienRepository;
    public final ModelMapper modelMapper;

    public NhanVienServiceImpl(NhanVienRepository nhanVienRepository, ModelMapper modelMapper) {
        this.nhanVienRepository = nhanVienRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LinkedList<NhanVien> getAllNhanVien() {
        List<NhanVien> nhanVienList = this.nhanVienRepository.findAll();
        LinkedList<NhanVien> linkedList = convertToLinkedList(nhanVienList);
        return linkedList;
    }

    @Override
    public void creatNhanVien(NhanVienRequest dto) {
        List<NhanVien> nhanVienList = this.nhanVienRepository.findAll();
        LinkedList<NhanVien> linkedList = convertToLinkedList(nhanVienList);
        if (linkedList.isEmpty()){
            NhanVien nhanVien = modelMapper.map(dto, NhanVien.class);
            linkedList.addFirst(nhanVien);
            nhanVienRepository.saveAll(linkedList);
        }
        else {
            for (NhanVien nhanVien : linkedList){
                if (dto.getMa().equals(nhanVien.getMa())){
                    throw new ResourceNotAcceptException("Nhan Vien already exist");
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
    public LinkedList<NhanVien> deleteNhanVien(String ma) {
        NhanVien nhanVien =nhanVienRepository.findById(ma).get();
        nhanVienRepository.delete(nhanVien);

        List<NhanVien> nhanVienList = this.nhanVienRepository.findAll();
        LinkedList<NhanVien> linkedList = convertToLinkedList(nhanVienList);

        return linkedList;
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

    public LinkedList<NhanVien> convertToLinkedList(List<NhanVien> nhanVienList){
        LinkedList<NhanVien> linkedList = new LinkedList<>(nhanVienList);
        return linkedList;
    }
}

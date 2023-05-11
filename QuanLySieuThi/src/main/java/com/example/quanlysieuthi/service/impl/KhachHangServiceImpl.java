package com.example.quanlysieuthi.service.impl;

import com.example.quanlysieuthi.data.entity.KhachHang;
import com.example.quanlysieuthi.data.repository.KhachHangRepository;
import com.example.quanlysieuthi.dto.request.KhachHangRequest;
import com.example.quanlysieuthi.exceptions.ResourceNotAcceptException;
import com.example.quanlysieuthi.exceptions.ResourceNotFoundException;
import com.example.quanlysieuthi.service.KhachHangService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class KhachHangServiceImpl implements KhachHangService {

    public final KhachHangRepository khachHangRepository;
    public final ModelMapper modelMapper;

    public KhachHangServiceImpl(KhachHangRepository khachHangRepository, ModelMapper modelMapper) {
        this.khachHangRepository = khachHangRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LinkedList<KhachHang> getAllKhachHang() {
        List<KhachHang> khachHangList = this.khachHangRepository.findAll();
        LinkedList<KhachHang> linkedList = convertToLinkedList(khachHangList);
        return linkedList;
    }

    @Override
    public void creatKhachHang(KhachHangRequest dto) {
        List<KhachHang> khachHangList = this.khachHangRepository.findAll();
        LinkedList<KhachHang> linkedList = convertToLinkedList(khachHangList);
        if (linkedList.isEmpty()){
            KhachHang khachHang = modelMapper.map(dto, KhachHang.class);
            linkedList.addFirst(khachHang);
            khachHangRepository.saveAll(linkedList);
        }
        else {
            for (KhachHang khachHang : linkedList){
                if (dto.getMa().equals(khachHang.getMa())){
                    throw new ResourceNotAcceptException("Khach Hang already exist");
                }
            }
            KhachHang khachHang = modelMapper.map(dto, KhachHang.class);
            linkedList.addFirst(khachHang);
            khachHangRepository.saveAll(linkedList);
        }

    }

    @Override
    public void updateKhachHang(KhachHangRequest dto) {
        KhachHang khachHang = modelMapper.map(dto, KhachHang.class);
        khachHangRepository.save(khachHang);

    }

    @Override
    public LinkedList<KhachHang> deleteKhachHang(String ma) {
        KhachHang khachHang =khachHangRepository.findById(ma).get();
        khachHangRepository.delete(khachHang);

        List<KhachHang> khachHangList = this.khachHangRepository.findAll();
        LinkedList<KhachHang> linkedList = convertToLinkedList(khachHangList);

        return linkedList;
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

    public LinkedList<KhachHang> convertToLinkedList(List<KhachHang> khachHangList){
        LinkedList<KhachHang> linkedList = new LinkedList<>(khachHangList);
        return linkedList;
    }
}

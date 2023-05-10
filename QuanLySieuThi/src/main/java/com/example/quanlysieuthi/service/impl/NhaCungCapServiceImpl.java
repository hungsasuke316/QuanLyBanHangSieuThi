package com.example.quanlysieuthi.service.impl;

import com.example.quanlysieuthi.data.entity.NhaCungCap;
import com.example.quanlysieuthi.data.repository.NhaCungCapRepository;
import com.example.quanlysieuthi.dto.request.NhaCungCapRequest;
import com.example.quanlysieuthi.exceptions.ResourceNotAcceptException;
import com.example.quanlysieuthi.exceptions.ResourceNotFoundException;
import com.example.quanlysieuthi.service.NhaCungCapService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class NhaCungCapServiceImpl implements NhaCungCapService {
    public final NhaCungCapRepository nhaCungCapRepository;
    public final ModelMapper modelMapper;

    public NhaCungCapServiceImpl(NhaCungCapRepository nhaCungCapRepository, ModelMapper modelMapper) {
        this.nhaCungCapRepository = nhaCungCapRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public LinkedList<NhaCungCap> getAllNhaCungCap() {
        List<NhaCungCap> nhaCungCapList = this.nhaCungCapRepository.findAll();
        LinkedList<NhaCungCap> linkedList = convertToLinkedList(nhaCungCapList);
        return linkedList;
    }

    @Override
    public void creatNhaCungCap(NhaCungCapRequest dto) {
        List<NhaCungCap> nhaCungCapList = this.nhaCungCapRepository.findAll();
        LinkedList<NhaCungCap> linkedList = convertToLinkedList(nhaCungCapList);
        if (linkedList.isEmpty()){
            NhaCungCap nhaCungCap = modelMapper.map(dto, NhaCungCap.class);
            linkedList.addFirst(nhaCungCap);
            nhaCungCapRepository.saveAll(linkedList);
        }
        else {
            for (NhaCungCap nhaCungCap : linkedList){
                if (dto.getMa().equals(nhaCungCap.getMa())){
                    throw new ResourceNotAcceptException("Nha Cung Cap already exist");
                }
            }
            NhaCungCap nhaCungCap = modelMapper.map(dto, NhaCungCap.class);
            linkedList.addFirst(nhaCungCap);
            nhaCungCapRepository.saveAll(linkedList);
        }
    }

    @Override
    public NhaCungCap getNhaCungCap(String ma) {
        List<NhaCungCap> nhaCungCapList = this.nhaCungCapRepository.findAll();
        LinkedList<NhaCungCap> linkedList = convertToLinkedList(nhaCungCapList);
        NhaCungCap nhaCungCap = new NhaCungCap();
        if (linkedList.isEmpty()){
            throw new ResourceNotFoundException("Danh sach trong!!!");
        }
        else {
            for (NhaCungCap item : linkedList){
                if (ma.equals(item.getMa())){
                    nhaCungCap = item;
                }
            }
            return  nhaCungCap;
        }
    }

    @Override
    public void updateNhaCungCap(NhaCungCapRequest dto) {
        NhaCungCap nhaCungCap = modelMapper.map(dto, NhaCungCap.class);
        nhaCungCapRepository.save(nhaCungCap);
    }

    @Override
    public LinkedList<NhaCungCap> deleteNhaCungCap(String ma) {
        NhaCungCap nhaCungCap =nhaCungCapRepository.findById(ma).get();
        nhaCungCapRepository.delete(nhaCungCap);

        List<NhaCungCap> nhaCungCapList = this.nhaCungCapRepository.findAll();
        LinkedList<NhaCungCap> linkedList = convertToLinkedList(nhaCungCapList);

        return linkedList;
    }

    public LinkedList<NhaCungCap> convertToLinkedList(List<NhaCungCap> nhaCungCapList){
        LinkedList<NhaCungCap> linkedList = new LinkedList<>(nhaCungCapList);
        return linkedList;
    }
}

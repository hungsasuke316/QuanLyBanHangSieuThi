package com.example.quanlysieuthi.service.impl;

import com.example.quanlysieuthi.data.repository.HoaDonRepository;
import com.example.quanlysieuthi.data.repository.PhieuNhapRepository;
import com.example.quanlysieuthi.dto.response.DoanhThuResponse;
import com.example.quanlysieuthi.service.DoanhThuService;
import org.springframework.stereotype.Service;

@Service
public class DoanhThuServiceImpl implements DoanhThuService {

    private HoaDonRepository hoaDonRepository;
    private PhieuNhapRepository phieuNhapRepository;

    public DoanhThuServiceImpl(HoaDonRepository hoaDonRepository, PhieuNhapRepository phieuNhapRepository) {
        this.hoaDonRepository = hoaDonRepository;
        this.phieuNhapRepository = phieuNhapRepository;
    }

    @Override
    public DoanhThuResponse getDoanhThuReport(int year, int month) {
        DoanhThuResponse report = new DoanhThuResponse();

        int tongTienHoaDon = hoaDonRepository.getTongTienHoaDon(year, month);
        report.setTongTienHoaDon(tongTienHoaDon);

        int tongTienPhieuNhap = phieuNhapRepository.getTongTienPhieuNhap(year, month);
        report.setTongTienPhieuNhap(tongTienPhieuNhap);

        return report;
    }
}

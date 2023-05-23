package com.example.quanlysieuthi.controller;

import com.example.quanlysieuthi.data.entity.*;
import com.example.quanlysieuthi.data.repository.NhaCungCapRepository;
import com.example.quanlysieuthi.data.repository.NhanVienRepository;
import com.example.quanlysieuthi.data.repository.SanPhamRepository;
import com.example.quanlysieuthi.dto.request.PhieuNhapRequest;
import com.example.quanlysieuthi.exceptions.ResourceNotAcceptException;
import com.example.quanlysieuthi.service.PhieuNhapService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

@Controller
public class PhieuNhapController {
    PhieuNhapService phieuNhapService;
    NhanVienRepository nhanVienRepository;
    NhaCungCapRepository nhaCungCapRepository;
    SanPhamRepository sanPhamRepository;

    public PhieuNhapController(PhieuNhapService phieuNhapService, NhanVienRepository nhanVienRepository,
                               NhaCungCapRepository nhaCungCapRepository, SanPhamRepository sanPhamRepository) {
        this.phieuNhapService = phieuNhapService;
        this.nhanVienRepository = nhanVienRepository;
        this.nhaCungCapRepository = nhaCungCapRepository;
        this.sanPhamRepository = sanPhamRepository;
    }

    @GetMapping("/phieunhap")
    public String getAll(Model model){
        LinkedList<PhieuNhap> linkedList = phieuNhapService.getAllPhieuNhap();
        model.addAttribute("phieuNhap", linkedList);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        model.addAttribute("dateFormat", dateFormat);

        return "phieunhap_list";
    }

    @GetMapping("/phieunhap/form")
    public String showFormSave(Model model){
        List<SanPham> sanPham = sanPhamRepository.findAll();
        model.addAttribute("sanPham", sanPham);

        List<NhanVien> nhanVien = nhanVienRepository.findAll();
        model.addAttribute("nhanVien", nhanVien);

        List<NhaCungCap> nhaCungCap = nhaCungCapRepository.findAll();
        model.addAttribute("nhaCungCap", nhaCungCap);

        model.addAttribute("phieuNhap", new PhieuNhap());

        return "phieunhap_add";
    }

    @PostMapping("/phieunhap")
    public String createPhieuNhap(@ModelAttribute("phieuNhap") PhieuNhapRequest phieuNhap, Model model, RedirectAttributes redirectAttributes){
        try {
            phieuNhapService.createPhieuNhap(phieuNhap);
            LinkedList<PhieuNhap> linkedList = phieuNhapService.getAllPhieuNhap();
            model.addAttribute("phieuNhap", linkedList);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            model.addAttribute("dateFormat", dateFormat);

            return "phieunhap_list";
        }
        catch (ResourceNotAcceptException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/phieunhap/form";
        }
    }
}
package com.example.quanlysieuthi.controller;

import com.example.quanlysieuthi.data.entity.*;
import com.example.quanlysieuthi.data.repository.KhachHangRepository;
import com.example.quanlysieuthi.data.repository.NhanVienRepository;
import com.example.quanlysieuthi.data.repository.SanPhamRepository;
import com.example.quanlysieuthi.dto.request.HoaDonRequest;
import com.example.quanlysieuthi.exceptions.ResourceNotAcceptException;
import com.example.quanlysieuthi.service.HoaDonService;
import com.example.quanlysieuthi.service.impl.PagedLinkedList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class HoaDonController {
    HoaDonService hoaDonService;
    NhanVienRepository nhanVienRepository;
    KhachHangRepository khachHangRepository;
    SanPhamRepository sanPhamRepository;

    public HoaDonController(HoaDonService hoaDonService, NhanVienRepository nhanVienRepository, KhachHangRepository khachHangRepository, SanPhamRepository sanPhamRepository) {
        this.hoaDonService = hoaDonService;
        this.nhanVienRepository = nhanVienRepository;
        this.khachHangRepository = khachHangRepository;
        this.sanPhamRepository = sanPhamRepository;
    }

    @GetMapping("/hoadon")
    public String getAll(Model model,
                         @RequestParam(defaultValue = "0") Integer page,
                         @RequestParam(defaultValue = "10") Integer size){
        PagedLinkedList<HoaDon> pagedHoaDon = hoaDonService.getAllHoaDon(page, size);
        model.addAttribute("pagedHoaDon", pagedHoaDon);
        model.addAttribute("page", page);
        model.addAttribute("size", size);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        model.addAttribute("dateFormat", dateFormat);

        return "hoadon_list";
    }

    @GetMapping("/hoadon/form")
    public String showFormSave(Model model){
        List<SanPham> sanPham = sanPhamRepository.findAll();
        model.addAttribute("sanPham", sanPham);

        List<NhanVien> nhanVien = nhanVienRepository.findAll();
        model.addAttribute("nhanVien", nhanVien);

        List<KhachHang> khachHang = khachHangRepository.findAll();
        model.addAttribute("khachHang", khachHang);

        model.addAttribute("hoaDon", new HoaDon());

        return "hoadon_add";
    }

    @PostMapping("/hoadon")
    public String createHoaDon(@ModelAttribute("hoaDon") HoaDonRequest hoaDon,
                               @RequestParam(defaultValue = "0") Integer page,
                               @RequestParam(defaultValue = "10") Integer size,
                               Model model, RedirectAttributes redirectAttributes){
        try {
            hoaDonService.createHoaDon(hoaDon);
            PagedLinkedList<HoaDon> pagedHoaDon = hoaDonService.getAllHoaDon(page, size);
            model.addAttribute("pagedHoaDon", pagedHoaDon);
            model.addAttribute("page", page);
            model.addAttribute("size", size);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            model.addAttribute("dateFormat", dateFormat);

            return "hoadon_list";
        }
        catch (ResourceNotAcceptException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/hoadon/form";
        }
    }
}

package com.example.quanlysieuthi.controller;

import com.example.quanlysieuthi.data.entity.*;
import com.example.quanlysieuthi.data.repository.NhaCungCapRepository;
import com.example.quanlysieuthi.data.repository.NhanVienRepository;
import com.example.quanlysieuthi.data.repository.SanPhamRepository;
import com.example.quanlysieuthi.dto.request.PhieuNhapRequest;
import com.example.quanlysieuthi.exceptions.ResourceNotAcceptException;
import com.example.quanlysieuthi.exceptions.ResourceNotFoundException;
import com.example.quanlysieuthi.service.PhieuNhapService;
import com.example.quanlysieuthi.service.impl.PagedLinkedList;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
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
    public String getAll(Model model,
                         @RequestParam(defaultValue = "0") Integer page,
                         @RequestParam(defaultValue = "10") Integer size){
        PagedLinkedList<PhieuNhap> pagedPhieuNhap = phieuNhapService.getAllPhieuNhap(page, size);
        model.addAttribute("pagedPhieuNhap", pagedPhieuNhap);
        model.addAttribute("page", page);
        model.addAttribute("size", size);

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
    public String createPhieuNhap(@ModelAttribute("phieuNhap") PhieuNhapRequest phieuNhap,
                                  @RequestParam(defaultValue = "0") Integer page,
                                  @RequestParam(defaultValue = "10") Integer size,
                                  Model model, RedirectAttributes redirectAttributes){
        try {
            phieuNhapService.createPhieuNhap(phieuNhap);
            PagedLinkedList<PhieuNhap> pagedPhieuNhap = phieuNhapService.getAllPhieuNhap(page, size);
            model.addAttribute("pagedPhieuNhap", pagedPhieuNhap);
            model.addAttribute("page", page);
            model.addAttribute("size", size);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            model.addAttribute("dateFormat", dateFormat);
            redirectAttributes.addFlashAttribute("successMessage", "Tạo phiếu nhập thành công");

            return "redirect:/phieunhap";
        }
        catch (ResourceNotAcceptException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

            return "redirect:/phieunhap/form";
        }
    }

    @GetMapping("/phieunhap/chitiet/{ma}")
    public ResponseEntity<PhieuNhap> detailPhieuNhap(@PathVariable("ma") String ma){
        PhieuNhap phieuNhap = this.phieuNhapService.getPhieuNhap(ma);
        if (phieuNhap == null){
            throw new ResourceNotFoundException("Không tìm thấy chi tiết phiếu nhập");
        }
        return ResponseEntity.ok().body(phieuNhap);
    }
}

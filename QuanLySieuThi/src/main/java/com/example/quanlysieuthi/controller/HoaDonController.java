package com.example.quanlysieuthi.controller;

import com.example.quanlysieuthi.data.entity.*;
import com.example.quanlysieuthi.data.repository.KhachHangRepository;
import com.example.quanlysieuthi.data.repository.NhanVienRepository;
import com.example.quanlysieuthi.data.repository.SanPhamRepository;
import com.example.quanlysieuthi.dto.request.HoaDonRequest;
import com.example.quanlysieuthi.exceptions.ResourceNotAcceptException;
import com.example.quanlysieuthi.exceptions.ResourceNotFoundException;
import com.example.quanlysieuthi.service.HoaDonService;
import com.example.quanlysieuthi.service.impl.PagedLinkedList;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
            redirectAttributes.addFlashAttribute("successMessage", "Tạo hóa đơn thành công");

            return "redirect:/hoadon";
        }
        catch (ResourceNotAcceptException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

            return "redirect:/hoadon/form";
        }
    }

    @GetMapping("/hoadon/chitiet/{ma}")
    public ResponseEntity<HoaDon> detailHoaDon(@PathVariable("ma") String ma){
        HoaDon hoaDon = this.hoaDonService.getHoaDon(ma);
        if (hoaDon == null){
            throw new ResourceNotFoundException("Không tìm thấy chi tiết hóa đơn");
        }
        return ResponseEntity.ok().body(hoaDon);
    }

    @GetMapping("/hoadon/timkiem")
    public String searchHoaDon(@RequestParam(name = "maHD", required = false) String maHD,
                               @RequestParam(name = "maNV", required = false) String maNV,
                               @RequestParam(name = "maSP", required = false) String maSP,
                               @RequestParam(name = "maKH", required = false) String maKH,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "10") int size,
                               Model model) {
        PagedLinkedList<HoaDon> pagedHoaDon = hoaDonService.searchHoaDon(maHD, maNV, maSP, maKH, page, size);

        if (!pagedHoaDon.getData().isEmpty()) {
            model.addAttribute("pagedHoaDon", pagedHoaDon);
            model.addAttribute("page", page);
            model.addAttribute("size", size);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            model.addAttribute("dateFormat", dateFormat);
        } else {
            model.addAttribute("noResult", "Không có kết quả.");
        }
        return "hoadon_list";
    }
}

package com.example.quanlysieuthi.controller;

import com.example.quanlysieuthi.data.entity.KhachHang;
import com.example.quanlysieuthi.dto.request.KhachHangRequest;
import com.example.quanlysieuthi.exceptions.ResourceNotAcceptException;
import com.example.quanlysieuthi.service.KhachHangService;
import com.example.quanlysieuthi.service.impl.PagedLinkedList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class KhachHangController {
    KhachHangService khachHangService;

    public KhachHangController(KhachHangService khachHangService) {
        this.khachHangService = khachHangService;
    }

    @GetMapping("/khachhang")
    public String getAll(Model model,
                         @RequestParam(defaultValue = "0") Integer page,
                         @RequestParam(defaultValue = "10") Integer size){
        PagedLinkedList<KhachHang> pagedKhachHang = khachHangService.getAllKhachHang(page, size);
        model.addAttribute("pagedKhachHang", pagedKhachHang);
        model.addAttribute("page", page);
        model.addAttribute("size", size);

        return "khachhang_list";
    }

    @GetMapping("/khachhang/form")
    public String showFormSave(Model model){
        model.addAttribute("khachHang", new KhachHang());

        return "khachhang_add";
    }

    @PostMapping("/khachhang")
    public String createKhachHang(@ModelAttribute("khachHang") KhachHangRequest khachHang,
                                  @RequestParam(defaultValue = "0") Integer page,
                                  @RequestParam(defaultValue = "10") Integer size,
                                  Model model, RedirectAttributes redirectAttributes){
        try {
            khachHangService.creatKhachHang(khachHang);
            PagedLinkedList<KhachHang> pagedKhachHang = khachHangService.getAllKhachHang(page, size);
            model.addAttribute("pagedKhachHang", pagedKhachHang);
            model.addAttribute("page", page);
            model.addAttribute("size", size);
            redirectAttributes.addFlashAttribute("successMessage", "Tạo khách hàng thành công");

            return "redirect:/khachhang";
        }
        catch (ResourceNotAcceptException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

            return "redirect:/khachhang/form";
        }
    }

    @GetMapping("/khachhang/update/{ma}")
    public String updateKhachHang(@PathVariable("ma") String ma, Model model, RedirectAttributes redirectAttributes){
        try {
            KhachHang nhaCungCap = khachHangService.getKhachHang(ma);
            model.addAttribute("khachHang", nhaCungCap);

            return "khachhang_update";
        }
        catch (ResourceNotAcceptException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

            return "redirect:/khachhang/form";
        }
    }

    @PostMapping("/khachhang/update")
    public String updateKhachHang(@ModelAttribute("khachHang") KhachHangRequest khachHang,
                                  @RequestParam(defaultValue = "0") Integer page,
                                  @RequestParam(defaultValue = "10") Integer size,
                                  Model model, RedirectAttributes redirectAttributes){
        khachHangService.updateKhachHang(khachHang);
        PagedLinkedList<KhachHang> pagedKhachHang = khachHangService.getAllKhachHang(page, size);
        model.addAttribute("pagedKhachHang", pagedKhachHang);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        redirectAttributes.addFlashAttribute("successMessage", "Sửa khách hàng thành công");

        return "redirect:/khachhang/?page=" + page + "&size=" + size;
    }

    @GetMapping("/khachhang/delete/{ma}")
    public String deleteKhachHang(@PathVariable("ma") String ma,
                                  @RequestParam(defaultValue = "0") Integer page,
                                  @RequestParam(defaultValue = "10") Integer size,
                                  Model model, RedirectAttributes redirectAttributes){
        try{
            khachHangService.deleteKhachHang(ma);
            PagedLinkedList<KhachHang> pagedKhachHang = khachHangService.getAllKhachHang(page, size);
            model.addAttribute("pagedKhachHang", pagedKhachHang);
            model.addAttribute("page", page);
            model.addAttribute("size", size);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa khách hàng thành công");

            return "redirect:/khachhang/?page=" + page + "&size=" + size;
        }
        catch (ResourceNotAcceptException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

            return "redirect:/khachhang/?page=" + page + "&size=" + size;
        }
    }

    @GetMapping("/khachhang/timkiem")
    public String searchKhachHang(@RequestParam(name = "ma", required = false) String ma,
                                   @RequestParam(name = "ten", required = false) String ten,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "10") int size,
                                   Model model) {
        PagedLinkedList<KhachHang> pagedKhachHang = khachHangService.searchKhachHang(ma, ten, page, size);

        if (!pagedKhachHang.getData().isEmpty()) {
            model.addAttribute("pagedKhachHang", pagedKhachHang);
            model.addAttribute("page", page);
            model.addAttribute("size", size);
        } else {
            model.addAttribute("noResult", "Không có kết quả.");
        }
        return "khachhang_list";
    }
}

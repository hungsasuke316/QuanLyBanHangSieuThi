package com.example.quanlysieuthi.controller;

import com.example.quanlysieuthi.data.entity.KhachHang;
import com.example.quanlysieuthi.dto.request.KhachHangRequest;
import com.example.quanlysieuthi.exceptions.ResourceNotAcceptException;
import com.example.quanlysieuthi.service.KhachHangService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedList;

@Controller
public class KhachHangController {
    KhachHangService khachHangService;

    public KhachHangController(KhachHangService khachHangService) {
        this.khachHangService = khachHangService;
    }

    @GetMapping("/khachhang")
    public String getAll(Model model){
        LinkedList<KhachHang> linkedList = khachHangService.getAllKhachHang();
        model.addAttribute("khachHang", linkedList);

        return "khachhang_list";
    }

    @GetMapping("/khachhang/form")
    public String showFormSave(Model model){
        model.addAttribute("khachHang", new KhachHang());

        return "khachhang_add";
    }

    @PostMapping("/khachhang")
    public String createKhachHang(@ModelAttribute("khachHang") KhachHangRequest khachHang, Model model, RedirectAttributes redirectAttributes){
        try {
            khachHangService.creatKhachHang(khachHang);
            LinkedList<KhachHang> linkedList = khachHangService.getAllKhachHang();
            model.addAttribute("khachHang", linkedList);

            return "khachhang_list";
        }
        catch (ResourceNotAcceptException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());

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
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/khachhang/form";
        }
    }

    @PostMapping("/khachhang/update")
    public String updateKhachHang(@ModelAttribute("khachHang") KhachHangRequest khachHang, Model model){
        khachHangService.updateKhachHang(khachHang);
        LinkedList<KhachHang> linkedList = khachHangService.getAllKhachHang();
        model.addAttribute("khachHang", linkedList);

        return "khachhang_list";
    }

    @GetMapping("/khachhang/delete/{ma}")
    public String deleteKhachHang(@PathVariable("ma") String ma, Model model){
        LinkedList<KhachHang> linkedList = khachHangService.deleteKhachHang(ma);
        model.addAttribute("khachHang", linkedList);

        return "/khachhang_list";
    }
}

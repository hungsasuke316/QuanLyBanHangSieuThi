package com.example.quanlysieuthi.controller;

import com.example.quanlysieuthi.data.entity.NhanVien;
import com.example.quanlysieuthi.dto.request.NhanVienRequest;
import com.example.quanlysieuthi.exceptions.ResourceNotAcceptException;
import com.example.quanlysieuthi.service.NhanVienService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedList;

@Controller
public class NhanVienController {
    NhanVienService nhanVienService;

    public NhanVienController(NhanVienService nhanVienService) {
        this.nhanVienService = nhanVienService;
    }

    @GetMapping("/nhanvien")
    public String getAll(Model model){
        LinkedList<NhanVien> linkedList = nhanVienService.getAllNhanVien();
        model.addAttribute("nhanVien", linkedList);

        return "nhanvien_list";
    }

    @GetMapping("/nhanvien/form")
    public String showFormSave(Model model){
        model.addAttribute("nhanVien", new NhanVien());

        return "nhanvien_add";
    }

    @PostMapping("/nhanvien")
    public String createKhachHang(@ModelAttribute("nhanVien") NhanVienRequest nhanVien, Model model, RedirectAttributes redirectAttributes){
        try {
            nhanVienService.creatNhanVien(nhanVien);
            LinkedList<NhanVien> linkedList = nhanVienService.getAllNhanVien();
            model.addAttribute("nhanVien", linkedList);

            return "nhanvien_list";
        }
        catch (ResourceNotAcceptException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/nhanvien/form";
        }
    }

    @GetMapping("/nhanvien/update/{ma}")
    public String updateNhanVien(@PathVariable("ma") String ma, Model model, RedirectAttributes redirectAttributes){
        try {
            NhanVien nhanVien = nhanVienService.getNhanVien(ma);
            model.addAttribute("nhanVien", nhanVien);

            return "nhanvien_update";
        }
        catch (ResourceNotAcceptException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/nhanvien/form";
        }
    }

    @PostMapping("/nhanvien/update")
    public String updateNhanVien(@ModelAttribute("nhanVien") NhanVienRequest nhanVien, Model model){
        nhanVienService.updateNhanVien(nhanVien);
        LinkedList<NhanVien> linkedList = nhanVienService.getAllNhanVien();
        model.addAttribute("nhanVien", linkedList);

        return "nhanvien_list";
    }

    @GetMapping("/nhanvien/delete/{ma}")
    public String deleteNhanVien(@PathVariable("ma") String ma, Model model){
        LinkedList<NhanVien> linkedList = nhanVienService.deleteNhanVien(ma);
        model.addAttribute("nhanVien", linkedList);

        return "/nhanvien_list";
    }
}

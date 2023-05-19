package com.example.quanlysieuthi.controller;

import com.example.quanlysieuthi.data.entity.SanPham;
import com.example.quanlysieuthi.dto.request.SanPhamRequest;
import com.example.quanlysieuthi.exceptions.ResourceNotAcceptException;
import com.example.quanlysieuthi.service.SanPhamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedList;

@Controller
public class SanPhamController {
    SanPhamService sanPhamService;

    public SanPhamController(SanPhamService sanPhamService) {
        this.sanPhamService = sanPhamService;
    }

    @GetMapping("/sanpham")
    public String getAll(Model model){
        LinkedList<SanPham> linkedList = sanPhamService.getAllSanPham();
        model.addAttribute("sanPham", linkedList);

        return "sanpham_list";
    }

    @GetMapping("/sanpham/form")
    public String showFormSave(Model model){
        model.addAttribute("sanPham", new SanPham());

        return "sanpham_add";
    }

    @PostMapping("/sanpham")
    public String createSanPham(@ModelAttribute("sanPham") SanPhamRequest sanPham, Model model, RedirectAttributes redirectAttributes){
        try {
            sanPhamService.createSanPham(sanPham);
            LinkedList<SanPham> linkedList = sanPhamService.getAllSanPham();
            model.addAttribute("sanPham", linkedList);

            return "sanpham_list";
        }
        catch (ResourceNotAcceptException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/sanpham/form";
        }
    }

    @GetMapping("/sanpham/update/{ma}")
    public String updateSanPham(@PathVariable("ma") String ma, Model model, RedirectAttributes redirectAttributes){
        try {
            SanPham sanPham = sanPhamService.getSanPham(ma);
            model.addAttribute("sanPham", sanPham);

            return "sanpham_update";
        }
        catch (ResourceNotAcceptException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/sanpham/form";
        }
    }

    @PostMapping("/sanpham/update")
    public String updateSanPham(@ModelAttribute("sanPham") SanPhamRequest sanPham, Model model){
        sanPhamService.updateSanPham(sanPham);
        LinkedList<SanPham> linkedList = sanPhamService.getAllSanPham();
        model.addAttribute("sanPham", linkedList);

        return "sanpham_list";
    }

    @GetMapping("/sanpham/delete/{ma}")
    public String deleteSanPham(@PathVariable("ma") String ma, Model model){
        LinkedList<SanPham> linkedList = sanPhamService.deleteSanPham(ma);
        model.addAttribute("sanPham", linkedList);

        return "/sanpham_list";
    }
}

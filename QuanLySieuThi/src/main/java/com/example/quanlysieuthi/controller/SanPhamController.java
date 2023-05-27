package com.example.quanlysieuthi.controller;

import com.example.quanlysieuthi.data.entity.SanPham;
import com.example.quanlysieuthi.dto.request.SanPhamRequest;
import com.example.quanlysieuthi.exceptions.ResourceNotAcceptException;
import com.example.quanlysieuthi.service.SanPhamService;
import com.example.quanlysieuthi.service.impl.PagedLinkedList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class SanPhamController {
    SanPhamService sanPhamService;

    public SanPhamController(SanPhamService sanPhamService) {
        this.sanPhamService = sanPhamService;
    }

    @GetMapping("/sanpham")
    public String getAll(Model model,
                         @RequestParam(defaultValue = "0") Integer page,
                         @RequestParam(defaultValue = "10") Integer size){
        PagedLinkedList<SanPham> pagedSanPham = sanPhamService.getAllSanPham(page, size);
        model.addAttribute("pagedSanPham", pagedSanPham);
        model.addAttribute("page", page);
        model.addAttribute("size", size);

        return "sanpham_list";
    }

    @GetMapping("/sanpham/form")
    public String showFormSave(Model model){
        model.addAttribute("sanPham", new SanPham());

        return "sanpham_add";
    }

    @PostMapping("/sanpham")
    public String createSanPham(@ModelAttribute("sanPham") SanPhamRequest sanPham,
                                @RequestParam(defaultValue = "0") Integer page,
                                @RequestParam(defaultValue = "10") Integer size,
                                Model model, RedirectAttributes redirectAttributes){
        try {
            sanPhamService.createSanPham(sanPham);
            PagedLinkedList<SanPham> pagedSanPham = sanPhamService.getAllSanPham(page, size);
            model.addAttribute("pagedSanPham", pagedSanPham);
            model.addAttribute("page", page);
            model.addAttribute("size", size);
            redirectAttributes.addFlashAttribute("successMessage", "Tạo sản phẩm thành công");

            return "redirect:/sanpham";
        }
        catch (ResourceNotAcceptException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

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
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

            return "redirect:/sanpham/form";
        }
    }

    @PostMapping("/sanpham/update")
    public String updateSanPham(@ModelAttribute("sanPham") SanPhamRequest sanPham,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                Model model, RedirectAttributes redirectAttributes){
        sanPhamService.updateSanPham(sanPham);
        PagedLinkedList<SanPham> pagedSanPham = sanPhamService.getAllSanPham(page, size);
        model.addAttribute("pagedSanPham", pagedSanPham);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        redirectAttributes.addFlashAttribute("successMessage", "Sửa sản phẩm thành công");

        return "redirect:/sanpham";
    }

    @GetMapping("/sanpham/delete/{ma}")
    public String deleteSanPham(@PathVariable("ma") String ma,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                Model model, RedirectAttributes redirectAttributes){
        try{
            sanPhamService.deleteSanPham(ma);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa sản phẩm thành công!");
            PagedLinkedList<SanPham> pagedSanPham = sanPhamService.getAllSanPham(page, size);
            model.addAttribute("pagedSanPham", pagedSanPham);
            model.addAttribute("page", page);
            model.addAttribute("size", size);


            return "redirect:/sanpham";
        }
        catch (ResourceNotAcceptException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

            return "redirect:/sanpham/?page=" + page + "&size=" + size;
        }
    }
}

package com.example.quanlysieuthi.controller;

import com.example.quanlysieuthi.data.entity.NhanVien;
import com.example.quanlysieuthi.dto.request.NhanVienRequest;
import com.example.quanlysieuthi.exceptions.ResourceNotAcceptException;
import com.example.quanlysieuthi.service.NhanVienService;
import com.example.quanlysieuthi.service.impl.PagedLinkedList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class NhanVienController {
    NhanVienService nhanVienService;

    public NhanVienController(NhanVienService nhanVienService) {
        this.nhanVienService = nhanVienService;
    }

    @GetMapping("/nhanvien")
    public String getAll(Model model,
                         @RequestParam(defaultValue = "0") Integer page,
                         @RequestParam(defaultValue = "10") Integer size){
        PagedLinkedList<NhanVien> pagedNhanVien = nhanVienService.getAllNhanVien(page, size);
        model.addAttribute("pagedNhanVien", pagedNhanVien);
        model.addAttribute("page", page);
        model.addAttribute("size", size);

        return "nhanvien_list";
    }

    @GetMapping("/nhanvien/form")
    public String showFormSave(Model model){
        model.addAttribute("nhanVien", new NhanVien());

        return "nhanvien_add";
    }

    @PostMapping("/nhanvien")
    public String createKhachHang(@ModelAttribute("nhanVien") NhanVienRequest nhanVien,
                                  @RequestParam(defaultValue = "0") Integer page,
                                  @RequestParam(defaultValue = "10") Integer size,
                                  Model model, RedirectAttributes redirectAttributes){
        try {
            nhanVienService.creatNhanVien(nhanVien);
            PagedLinkedList<NhanVien> pagedNhanVien = nhanVienService.getAllNhanVien(page, size);
            model.addAttribute("pagedNhanVien", pagedNhanVien);
            model.addAttribute("page", page);
            model.addAttribute("size", size);
            redirectAttributes.addFlashAttribute("successMessage", "Tạo nhân viên thành công!");

            return "redirect:/nhanvien";
        }
        catch (ResourceNotAcceptException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

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
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

            return "redirect:/nhanvien/form";
        }
    }

    @PostMapping("/nhanvien/update")
    public String updateNhanVien(@ModelAttribute("nhanVien") NhanVienRequest nhanVien,
                                 @RequestParam(defaultValue = "0") Integer page,
                                 @RequestParam(defaultValue = "10") Integer size,
                                 Model model, RedirectAttributes redirectAttributes){
        nhanVienService.updateNhanVien(nhanVien);
        PagedLinkedList<NhanVien> pagedNhanVien = nhanVienService.getAllNhanVien(page, size);
        model.addAttribute("pagedNhanVien", pagedNhanVien);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        redirectAttributes.addFlashAttribute("successMessage", "Sửa nhân viên thành công!");

        return "redirect:/nhanvien/?page=" + page + "&size=" + size;
    }

    @GetMapping("/nhanvien/delete/{ma}")
    public String deleteNhanVien(@PathVariable("ma") String ma,
                                 @RequestParam(defaultValue = "0") Integer page,
                                 @RequestParam(defaultValue = "10") Integer size,
                                 Model model, RedirectAttributes redirectAttributes){
        try{
            nhanVienService.deleteNhanVien(ma);
            PagedLinkedList<NhanVien> pagedNhanVien = nhanVienService.getAllNhanVien(page, size);
            model.addAttribute("pagedNhanVien", pagedNhanVien);
            model.addAttribute("page", page);
            model.addAttribute("size", size);
            redirectAttributes.addFlashAttribute("successMessage", "Xóa nhân viên thành công!");

            return "redirect:/nhanvien/?page=" + page + "&size=" + size;
        }
        catch (ResourceNotAcceptException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

            return "redirect:/nhanvien/?page=" + page + "&size=" + size;
        }
    }

    @GetMapping("/nhanvien/timkiem")
    public String searchNhanVien(@RequestParam(name = "ma", required = false) String ma,
                                   @RequestParam(name = "ten", required = false) String ten,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "10") int size,
                                   Model model) {
        PagedLinkedList<NhanVien> pagedNhanVien = nhanVienService.searchNhanVien(ma, ten, page, size);

        if (!pagedNhanVien.getData().isEmpty()) {
            model.addAttribute("pagedNhanVien", pagedNhanVien);
            model.addAttribute("page", page);
            model.addAttribute("size", size);
        } else {
            model.addAttribute("noResult", "Không có kết quả.");
        }
        return "nhanvien_list";
    }
}

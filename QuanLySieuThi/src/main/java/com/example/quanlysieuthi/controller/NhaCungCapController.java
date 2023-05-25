package com.example.quanlysieuthi.controller;

import com.example.quanlysieuthi.data.entity.NhaCungCap;
import com.example.quanlysieuthi.data.repository.NhaCungCapRepository;
import com.example.quanlysieuthi.dto.request.NhaCungCapRequest;
import com.example.quanlysieuthi.exceptions.ResourceNotAcceptException;
import com.example.quanlysieuthi.service.NhaCungCapService;
import com.example.quanlysieuthi.service.impl.PagedLinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class NhaCungCapController {
    NhaCungCapService nhaCungCapService;
    @Autowired
    NhaCungCapRepository nhaCungCapRepository;

    public NhaCungCapController(NhaCungCapService nhaCungCapService) {
        this.nhaCungCapService = nhaCungCapService;
    }

    @GetMapping("/nhacungcap")
    public String getAll(Model model,
                         @RequestParam(defaultValue = "0") Integer page,
                         @RequestParam(defaultValue = "10") Integer size){
        PagedLinkedList<NhaCungCap> pagedNhaCungCap = nhaCungCapService.getAllNhaCungCap(page, size);
        model.addAttribute("pagedNhaCungCap", pagedNhaCungCap);
        model.addAttribute("page", page);
        model.addAttribute("size", size);

        return "nhacungcap_list";
    }

    @GetMapping("/nhacungcap/form")
    public String showFormSave(Model model){
        model.addAttribute("nhaCungCap", new NhaCungCap());

        return "nhacungcap_add";
    }

    @PostMapping("/nhacungcap")
    public String createNhaCungCap(@ModelAttribute("nhaCungCap") NhaCungCapRequest nhaCungCap,
                                   @RequestParam(defaultValue = "0") Integer page,
                                   @RequestParam(defaultValue = "10") Integer size,
                                   Model model, RedirectAttributes redirectAttributes){
        try {
            nhaCungCapService.creatNhaCungCap(nhaCungCap);
            PagedLinkedList<NhaCungCap> pagedNhaCungCap = nhaCungCapService.getAllNhaCungCap(page, size);
            model.addAttribute("pagedNhaCungCap", pagedNhaCungCap);
            model.addAttribute("page", page);
            model.addAttribute("size", size);

            return "nhacungcap_list";
        }
        catch (ResourceNotAcceptException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/nhacungcap/form";
        }
    }

    @GetMapping("/nhacungcap/update/{ma}")
    public String updateNhaCungCap(@PathVariable("ma") String ma,
                                   Model model, RedirectAttributes redirectAttributes){
        try {
            NhaCungCap nhaCungCap = nhaCungCapService.getNhaCungCap(ma);
            model.addAttribute("nhaCungCap", nhaCungCap);

            return "nhacungcap_update";
        }
        catch (ResourceNotAcceptException e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());

            return "redirect:/nhacungcap/form";
        }
    }

    @PostMapping("/nhacungcap/update")
    public String updateNhaCungCap(@ModelAttribute("nhaCungCap") NhaCungCapRequest nhaCungCap,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   Model model){
        nhaCungCapService.updateNhaCungCap(nhaCungCap);
        PagedLinkedList<NhaCungCap> pagedNhaCungCap = nhaCungCapService.getAllNhaCungCap(page, size);
        model.addAttribute("pagedNhaCungCap", pagedNhaCungCap);
        model.addAttribute("page", page);
        model.addAttribute("size", size);

        return "nhacungcap_list";
    }

    @GetMapping("/nhacungcap/delete/{ma}")
    public String deleteNhaCungCap(@PathVariable("ma") String ma,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "10") int size,
                                   Model model){
        nhaCungCapService.deleteNhaCungCap(ma);
        PagedLinkedList<NhaCungCap> pagedNhaCungCap = nhaCungCapService.getAllNhaCungCap(page, size);
        model.addAttribute("pagedNhaCungCap", pagedNhaCungCap);
        model.addAttribute("page", page);
        model.addAttribute("size", size);

        return "/nhacungcap_list";
    }
}

package com.example.quanlysieuthi.controller;

import com.example.quanlysieuthi.dto.response.DoanhThuResponse;
import com.example.quanlysieuthi.service.DoanhThuService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Year;

@Controller
public class DoanhThuController {
    DoanhThuService doanhThuService;

    public DoanhThuController(DoanhThuService doanhThuService) {
        this.doanhThuService = doanhThuService;
    }

    @GetMapping("/baocao")
    public String showBaoCao(Model model) {
        int currentYear = Year.now().getValue();
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();

        DoanhThuResponse report = doanhThuService.getDoanhThuReport(currentYear, currentMonth);
        model.addAttribute("year", currentYear);
        model.addAttribute("month", currentMonth);
        model.addAttribute("report", report);

        return "baocao";
    }

    @PostMapping("/baocao")
    public String generateBaoCao(@RequestParam("year") int year, @RequestParam("month") int month, Model model) {
        DoanhThuResponse report = doanhThuService.getDoanhThuReport(year, month);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("report", report);

        return "baocao";
    }
}

package com.springweb.controller;

import com.springweb.entity.ThanhVien;
import com.springweb.service.ThanhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/thanhvien")
public class ThanhVienController {
    @Autowired
    private ThanhVienService thanhVienService;

    @GetMapping
    public String viewHomePage(Model model) {
        return viewPage(model, 1);
    }

    @GetMapping("/page/{pageNum}")
    public String viewPage(Model model, @PathVariable("pageNum") int pageNum) {
        Page<ThanhVien> page = thanhVienService.listAll(pageNum);
        List<ThanhVien> list = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("listThanhVien", list);

        return "/thanhvien/view_all_thanhvien";
    }

    @GetMapping("/delete/{id}")
    public String DeleteThanhVien(@PathVariable Integer id) {
        thanhVienService.DelteThanhVien(id);
        return "redirect:/thanhvien";
    }

    @GetMapping("/search")
    public String SearchThanhVien(Model model, @Param("keyword") String keyword) {
        List<ThanhVien> list= thanhVienService.searchList(keyword);

        model.addAttribute("keyword", keyword);
        model.addAttribute("listThanhVien", list);

        return "/thanhvien/view_all_thanhvien";
    }
}

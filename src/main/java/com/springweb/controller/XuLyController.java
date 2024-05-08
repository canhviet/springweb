package com.springweb.controller;

import com.springweb.entity.XuLy;
import com.springweb.entity.ThanhVien;
import java.time.LocalDateTime;
import com.springweb.service.XuLyService;
import com.springweb.repository.ThanhVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
@RequestMapping("/xuly")
public class XuLyController {
    @Autowired
    private XuLyService xuLyServiceservice;
    @Autowired
    private ThanhVienRepository thanhVienRepository;




    @GetMapping
    public String viewHomePage(Model model) {
        return viewPage(model, 1);
    }

    @GetMapping("/page/{pageNum}")
    public String viewPage(Model model, @PathVariable("pageNum") int pageNum) {
        Page<XuLy> page = xuLyServiceservice.getList(pageNum);
        List<XuLy> list = page.getContent();
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("listXuLy", list);

        return "/xuly/view_all_xuly";
    }

    @GetMapping("/add")
    public String addXuLy(Model model){
        List<ThanhVien> listThanhVien = thanhVienRepository.findAll();

        model.addAttribute("listThanhVien", listThanhVien);
        return "xuly/add_xuly";
    }

    @PostMapping("/add")
    public String addThietBi(@Valid @ModelAttribute("xuLy") XuLy xuLy, BindingResult bindingResult){
        LocalDateTime ngayXlValue = LocalDateTime.parse(xuLy.getNgayXl() + ":00");
        xuLy.setNgayXl(ngayXlValue);
        xuLyServiceservice.createXuLy(xuLy);
        return "redirect:/xuly";
    }
}

package com.springweb.controller;

import com.springweb.entity.ThongTinSD;
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

import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
@RequestMapping("/admin/xuly")
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
    public String addXuLy(Model model) {
        List<ThanhVien> listThanhVien = thanhVienRepository.findAll();

        model.addAttribute("listThanhVien", listThanhVien);
        return "xuly/add_xuly";
    }

    @PostMapping("/add")
    public String addXuLy(@Valid @ModelAttribute("xuLy") XuLy xuLy, BindingResult bindingResult) {
        LocalDateTime ngayXlValue = LocalDateTime.parse(xuLy.getNgayXl() + ":00");
        xuLy.setNgayXl(ngayXlValue);
        xuLyServiceservice.createXuLy(xuLy);
        return "redirect:/admin/xuly";
    }

    @GetMapping("/update/{id}")
    public String updateXuLy(@PathVariable("id") Integer maXl, Model model) {
        XuLy xl = xuLyServiceservice.getById(maXl);
        List<ThanhVien> listThanhVien = thanhVienRepository.findAll();

        model.addAttribute("listThanhVien", listThanhVien);
        model.addAttribute("maXl", xl.getMaXl());
        model.addAttribute("soTien", xl.getSoTien());
        model.addAttribute("trangThai", xl.getTrangThai());
        model.addAttribute("ngayXl", xl.getNgayXl());
        model.addAttribute("maTv", xl.getMaTv());
        model.addAttribute("hinhThucXl", xl.getHinhThucXl());

        return "xuly/update_xuly";
    }

    @PostMapping("/update")
    public String updateXuLy(@ModelAttribute("xuLy") XuLy xuLy, BindingResult bindingResult) {
        LocalDateTime ngayXlValue = LocalDateTime.parse(xuLy.getNgayXl() + ":00");
        xuLy.setNgayXl(ngayXlValue);
        xuLyServiceservice.updateXuLy(xuLy);
        return "redirect:/admin/xuly";
    }

    @GetMapping("/delete/{id}")
    public String deleteXuLy(@PathVariable Integer id) {
        xuLyServiceservice.deleteXuLyById(id);
        return "redirect:/admin/xuly";
    }

    @GetMapping("/search")
    public String searchThietBi(Model model, @RequestParam("keyword") String keyword) {
        List<XuLy> list = xuLyServiceservice.getList();
        List<XuLy> filteredList = new ArrayList<>();
        for (XuLy xl : list) {
            if(xl.getHinhThucXl().toLowerCase().contains(keyword.toLowerCase()) ||
            xl.getTenTV().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(xl);
            }
        }
        model.addAttribute("keyword", keyword);
        model.addAttribute("listXuLy", filteredList);

        return "/xuly/view_all_xuly";
    }
}


package com.springweb.controller;

import com.springweb.entity.XuLy;
import com.springweb.service.XuLyService;
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
@RequestMapping("/admin/xulyvipham")
public class XuLyController {
    @Autowired
    private XuLyService xuLyServiceservice;

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

        return "/xulyvipham/view_all_xulyvipham";
    }
}

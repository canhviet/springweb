package com.springweb.controller;

import com.springweb.entity.ThietBi;
import com.springweb.service.ThietBiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/thietbi")
public class ThietBiController {

    @Autowired
    private ThietBiService thietBiService;

    @GetMapping
    public String viewHomePage(Model model) {
        return viewPage(model, 1);
    }

    @GetMapping("/page/{pageNum}")
    public String viewPage(Model model, @PathVariable("pageNum") int pageNum) {
        Page<ThietBi> page = thietBiService.listAll(pageNum);
        List<ThietBi> list = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("listThietBi", list);

        return "thietbi/view_all_thietbi";
    }

    @GetMapping("/delete/{id}")
    public String deleteThietBi(@PathVariable Integer id) {
        thietBiService.DelteThietBi(id);
        return "redirect:/admin/thietbi";
    }

    @GetMapping("/search")
    public String searchThietBi(Model model, @RequestParam("keyword") String keyword) {
        List<ThietBi> list = thietBiService.searchList(keyword);

        model.addAttribute("keyword", keyword);
        model.addAttribute("listThietBi", list);

        return "/thietbi/view_all_thietbi";
    }

    @GetMapping("/add")
    public String add_ThietBi(Model model) {
        return "thietbi/add_thietbi";
    }

    @GetMapping("/update/{id}")
    public String updateThietBi(@PathVariable("id") Integer maTB, Model model) {
        ThietBi thietBi = thietBiService.getByMaTB(maTB);

        model.addAttribute("maTB", thietBi.getMaTB());
        model.addAttribute("tenTB", thietBi.getTenTB());
        model.addAttribute("moTa", thietBi.getMoTa());

        return "thietbi/update_thietbi";
    }

    @PostMapping("/add")
    public String addThietBi(@Valid @ModelAttribute("ThietBi") ThietBi thietBi, BindingResult bindingResult) {
        thietBiService.SaveThietBi(thietBi);
        return "redirect:/admin/thietbi";
    }

    @PostMapping("/update")
    public String updateThietBi(@ModelAttribute("ThietBi") ThietBi thietBi, BindingResult bindingResult) {

        thietBiService.SaveThietBi(thietBi);
        return "redirect:/admin/thietbi";
    }
}

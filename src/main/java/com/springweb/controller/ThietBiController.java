package com.springweb.controller;

import com.springweb.entity.ThanhVien;
import com.springweb.entity.ThietBi;
import com.springweb.service.ThietBiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ThietBiController {
    @Autowired
    private ThietBiService thietBiService;

    @GetMapping("admin/qlythietbi")
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

        return "/admin/qlythietbi";
    }

    @GetMapping("thietbi/delete/{id}")
    public String deleteThietBi(@PathVariable Integer id) {
        thietBiService.DelteThietBi(id);
        return "redirect:/thietbi";
    }

    @GetMapping("/thietbi/search")
    public String searchThietBi(Model model, @RequestParam("keyword") String keyword) {
        List<ThietBi> list = thietBiService.searchList(keyword);

        model.addAttribute("keyword", keyword);
        model.addAttribute("listThietBi", list);

        return "thietbi/view_all_thietbi";
    }
    @GetMapping("/add_thietbi")
    public String add_ThietBi(Model model){
        return "thietbi/add_thietbi";
    }
    @GetMapping("/update_thietbi/{id}")
    public String updateThietBi(Model model, @PathVariable("id") Integer maTB) {
        ThietBi thietBi = thietBiService.getByMaTB(maTB);

        model.addAttribute("maTB", thietBi.getMaTB());
        model.addAttribute("tenTB", thietBi.getTenTB());
        model.addAttribute("moTa", thietBi.getMoTa());

        return "thietbi/update_thietbi";
    }

    @PostMapping("thietbi/add")
    public String addThietBi(@Valid @ModelAttribute("ThietBi") ThietBi thietBi, BindingResult bindingResult){
        thietBiService.SaveThietBi(thietBi);
        return "redirect:/admin/qlythietbi";
    }

    @PostMapping("/thietbi/update")
    public String updateThietBi(@ModelAttribute("ThietBi") ThietBi thietBi, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Xử lý lỗi nếu cần
        }

        thietBiService.SaveThietBi(thietBi);
        return "redirect:/admin/qlythietbi";
    }


}

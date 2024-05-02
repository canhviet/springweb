package com.springweb.controller;

import com.springweb.entity.ThanhVien;
import com.springweb.entity.ThietBi;
import com.springweb.entity.ThongTinSD;
import com.springweb.service.TTSDService;
import com.springweb.service.ThanhVienService;
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
@RequestMapping("/")
public class AppController {
    int id = -1;

    @Autowired
    private ThanhVienService thanhVienService;

    @Autowired
    private ThietBiService thietBiService;

    @Autowired
    private TTSDService ttsdService;

    @GetMapping
    public String HomePage() {
        return "login";
    }

    @GetMapping("/register")
    public String RegisterPage(Model model) {
        model.addAttribute("ThanhVien", new ThanhVien());
        return "register";
    }

    @GetMapping("/login")
    public String LoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String Login(@RequestParam String username, @RequestParam String password) {
        id = Integer.parseInt(username);
        if(thanhVienService.existsByMaTVAndPassword(id, password)) {
            return "redirect:/user";
        }
        else {
            return "redirect:/login";
        }
    }

    @PostMapping("/register")
    public String Register(@Valid @ModelAttribute("ThanhVien") ThanhVien tv, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "register";
        }
        thanhVienService.SaveThanhVien(tv);
        return "redirect:/login";
    }

    @GetMapping("/user")
    public String viewHomePage(Model model) {
        model.addAttribute("MaTV", id);
        return viewPage(model, 1);
    }

    @GetMapping("/user/page/{pageNum}")
    public String viewPage(Model model, @PathVariable("pageNum") int pageNum) {
        Page<ThietBi> page = ttsdService.listAll(pageNum);
        List<ThietBi> list = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("listTB", list);

        return "user";
    }

//    @GetMapping("/user/search")
//    public String Search(Model model, @Param("keyword") String keyword) {
//        List<ThietBi> list = thietBiService.getAllSearch(keyword);
//
//        model.addAttribute("keyword", keyword);
//        model.addAttribute("listTT", list);
//
//        return "user";
//    }

    @GetMapping("/admin")
    public String AdminPage() {
        return "admin";
    }

    @GetMapping("/user/datcho/{maTB}/{maTV}")
    public String DatCho(@PathVariable("maTB") int maTB, @PathVariable("maTV") int maTV, Model model) {
        model.addAttribute("MaTV", maTV);
        model.addAttribute("MaTB", maTB);
        return "datcho";
    }

}

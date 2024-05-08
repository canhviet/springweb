package com.springweb.controller;

import com.springweb.entity.ThanhVien;
import com.springweb.entity.ThietBi;
import com.springweb.entity.ThongTinSD;
import com.springweb.service.TTSDService;
import com.springweb.service.ThanhVienService;
import com.springweb.service.ThietBiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin/muontra")
public class ThongTinSDController {
    @Autowired
    private TTSDService ttsdService;

    @Autowired
    private ThanhVienService thanhVienService;

    @Autowired
    private ThietBiService thietBiService;

    @GetMapping
    public String viewHomePage(Model model) {
        return viewPage(model, 1);
    }

    @GetMapping("/page/{pageNum}")
    public String viewPage(Model model, @PathVariable("pageNum") int pageNum) {
        Page<ThongTinSD> page = ttsdService.listAll(pageNum);
        List<ThongTinSD> list = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("listTTSD", list);

        return "xulymuontra/view_all_xulymuontra";
    }

    @GetMapping("/update/{id}")
    public String updateMuonTra(Model model, @PathVariable("id") Integer maTT) {
        ThongTinSD thongTinSD = ttsdService.getByMaTT(maTT);

        model.addAttribute("maTV", thongTinSD.getMaTV());
        model.addAttribute("maTB", thongTinSD.getMaTB());
        model.addAttribute("tgVao", thongTinSD.getTgVao());
        model.addAttribute("tgMuon", thongTinSD.getTgMuon());
        model.addAttribute("tgTra", thongTinSD.getTgTra());
        model.addAttribute("tgDatCho", thongTinSD.getTgDatCho());
        model.addAttribute("trang_thai", thongTinSD.getTrang_thai());

        return "xulymuontra/update_muontra";
    }

    @PostMapping("/update")
    public String update_MuonTra(@ModelAttribute("ThongTinSD") ThongTinSD thongTinSD, BindingResult bindingResult, @RequestParam("trang_thai") String trang_thai) {
        thongTinSD.setTrang_thai(trang_thai);
        ttsdService.Save(thongTinSD);
        return "redirect:/admin/muontra";
    }
}

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

import java.time.LocalDateTime;
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

    boolean muon = false;

    boolean tra = false;

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
        model.addAttribute("tgMuon", thongTinSD.getTgMuon());
        model.addAttribute("tgDatCho", thongTinSD.getTgDatCho());
        model.addAttribute("trang_thai", thongTinSD.getTrang_thai());

        if(LocalDateTime.now().isBefore(thongTinSD.getTgDatCho())) {
            model.addAttribute("chuatoingay", "chuatoingay");
        }
        if(thongTinSD.getTrang_thai().equalsIgnoreCase("dang cho muon")) {
            tra = true;
            muon = false;
            model.addAttribute("dangchomuon", "dangchomuon");
        }
        if(thongTinSD.getTrang_thai().equalsIgnoreCase("dang dat cho")) {
            tra = false;
            muon = true;
            model.addAttribute("dangdatcho", "dangdatcho");
        }
        return "xulymuontra/update_muontra";
    }

    @PostMapping("/update")
    public String update_MuonTra(@ModelAttribute("ThongTinSD") ThongTinSD thongTinSD, @RequestParam("trang_thai") String trang_thai) {
        ThongTinSD tt = ttsdService.getByMaTVAndMaTB(thongTinSD.getMaTV(), thongTinSD.getMaTB());
        tt.setTrang_thai(trang_thai);
        tt.setTgVao(LocalDateTime.now());
        if(muon) {
            tt.setTgMuon(LocalDateTime.now());
        }
        if(tra) {
            tt.setTgTra(LocalDateTime.now());
        }
        ttsdService.Save(tt);
        return "redirect:/admin/muontra";
    }
}

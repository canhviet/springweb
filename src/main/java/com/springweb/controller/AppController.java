package com.springweb.controller;

import com.springweb.entity.ThanhVien;
import com.springweb.entity.ThietBi;
import com.springweb.entity.ThongTinSD;
import com.springweb.service.TTSDService;
import com.springweb.service.ThanhVienService;
import com.springweb.service.ThietBiService;
import com.springweb.service.XuLyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static java.time.LocalTime.now;

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

    @Autowired
    private XuLyService xuLyService;

    boolean loginfail = false;

    boolean dangvipham = false;

    public boolean isCurrentTimeAfterOneHour(LocalDateTime localDateTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(localDateTime, now);
        return duration.toHours() > 1;
    }

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
    public String LoginPage(Model model) {
        if(dangvipham) {
            model.addAttribute("dangvipham", "vipham");
            return "login";
        }
        if(loginfail) {
            model.addAttribute("loginfail", "fail");
            return "login";
        }
        return "login";
    }

    @GetMapping("/profile")
    public String viewProfile(Model model) {
        ThanhVien thanhVien = thanhVienService.getByMaTV(id);
        model.addAttribute("thanhVien", thanhVien);

        return "profile";
    }

    @GetMapping("/editprofile")
    public String editProfile(Model model) {
        model.addAttribute("thanhVien", thanhVienService.getByMaTV(id));
        return "editprofile";
    }

    @PostMapping("/editproflie")
    public String updateProfile(@ModelAttribute("ThanhVien") ThanhVien thanhVien, Model model) {
        thanhVienService.SaveThanhVien(thanhVien);
        return "redirect:/profile";
    }

    @PostMapping("/login")
    public String Login(@RequestParam String username, @RequestParam String password, Model model) {
        id = Integer.parseInt(username);
        if(thanhVienService.existsByMaTVAndPassword(id, password)) {

            List<ThongTinSD> list = ttsdService.getDatCho();
            for(int i = 0; i < list.size(); i++) {
                if(isCurrentTimeAfterOneHour(list.get(i).getTgDatCho())) { //sau 1 tieng khong dat cho
                    ThongTinSD thongTinSD = ttsdService.getByMaTT(list.get(i).getMaTT());
                    thongTinSD.setTrang_thai("huy dat cho");
                    ttsdService.Save(thongTinSD);
                }
            }

            if (xuLyService.DangViPham(1234567)) {
                loginfail = false;
                dangvipham = true;
                return "redirect:/login";
            }

            return "redirect:/user";
        }

        loginfail = true;
        dangvipham = false;
        return "redirect:/login";

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
        Page<ThietBi> page = thietBiService.listAll(pageNum);
        List<ThietBi> list = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("listTB", list);

        return "user";
    }

    @GetMapping("/user/search")
    public String Search(Model model, @Param("keyword") String keyword, @Param("MaTV") int MaTV) {
        List<ThietBi> list = thietBiService.getAllSearch(keyword);

        model.addAttribute("keyword", keyword);
        model.addAttribute("listTB", list);
        model.addAttribute("MaTV", MaTV);
        return "user";
    }

    @GetMapping("/admin")
    public String AdminPage() {
        return "admin/admin";
    }

    @GetMapping("/user/datcho/{maTB}/{maTV}")
    public String pageDatCho(@PathVariable("maTB") int maTB, @PathVariable("maTV") int maTV, Model model) {
        model.addAttribute("MaTV", maTV);
        model.addAttribute("MaTB", maTB);
        model.addAttribute("TenTV", thanhVienService.getByMaTV(maTV).getTen());
        model.addAttribute("TenTB", thietBiService.getByMaTB(maTB).getTenTB());
        return "datcho";
    }

    @GetMapping("/user/datchotheongay/{maTB}/{maTV}")
    public String pageDatChoTheoNgay(@PathVariable("maTB") int maTB, @PathVariable("maTV") int maTV, Model model) {
        model.addAttribute("MaTV", maTV);
        model.addAttribute("MaTB", maTB);
        model.addAttribute("TenTV", thanhVienService.getByMaTV(maTV).getTen());
        model.addAttribute("TenTB", thietBiService.getByMaTB(maTB).getTenTB());
        return "datchotheongay";
    }

    @PostMapping("/datcho")
    public String DatCho(@RequestParam("MaTV") Integer MaTV, @RequestParam("MaTB") Integer MaTB, Model model) {
        ThongTinSD thongTinSD = new ThongTinSD();
        if(!ttsdService.KiemTraTonTai(MaTB)) {

            thongTinSD.setMaTB(MaTB);
            thongTinSD.setMaTV(MaTV);
            thongTinSD.setTrang_thai("dang dat cho");
            thongTinSD.setTgDatCho(LocalDateTime.now());
            thongTinSD.setTgVao(LocalDateTime.now());
            ttsdService.Save(thongTinSD);
            return "redirect:/user";
        }
        if (ttsdService.MuonLai(MaTB, MaTV) && !ttsdService.KiemTraTrangThai("dang dat cho", MaTB) && !ttsdService.KiemTraTrangThai("dang cho muon", MaTB)) {
            thongTinSD = ttsdService.getByMaTVAndMaTB(MaTV, MaTB);
            thongTinSD.setTrang_thai("dang dat cho");
            thongTinSD.setTgDatCho(LocalDateTime.now());
            ttsdService.Save(thongTinSD);
            return "redirect:/user";
        }
        if (!ttsdService.KiemTraTrangThai("dang dat cho", MaTB) && !ttsdService.KiemTraTrangThai("dang cho muon", MaTB)) {
            thongTinSD.setMaTB(MaTB);
            thongTinSD.setMaTV(MaTV);
            thongTinSD.setTrang_thai("dang dat cho");
            thongTinSD.setTgDatCho(LocalDateTime.now());
            ttsdService.Save(thongTinSD);
            return "redirect:/user";
        }

        model.addAttribute("error", "Hien tai khong the dat thiet bi nay");
        return "datcho";

    }

    @GetMapping("/processing")
    public String ProcessingPage(){
        return "processing";
    }
}

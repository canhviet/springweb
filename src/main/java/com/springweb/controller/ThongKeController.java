package com.springweb.controller;

import com.springweb.entity.ThongTinSD;
import com.springweb.entity.XuLy;
import com.springweb.service.TTSDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/admin/thongke")
public class ThongKeController {

    @Autowired
    private TTSDService ttsdService;

    @GetMapping("/TTSD")
    public String viewThongKeTTSD(Model model) {
        return viewPageThongKeTTSD(model, 1);
    }

    @GetMapping("/page/{pageNum}")
    public String viewPageThongKeTTSD(Model model, @PathVariable("pageNum") int pageNum) {
        Page<ThongTinSD> page = ttsdService.getThanhVienVao(pageNum);
        List<ThongTinSD> list = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("listTTSD", list);

        return "thongke/view_all_thongkeTTSD";
    }

    @GetMapping("/TTSD/search")
    public String searchThongKeTTSD(Model model,
                                    @RequestParam(value = "ngay", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ngay,
                                    @RequestParam(value = "thang", required = false) Integer thang,
                                    @RequestParam(value = "nam", required = false) Integer nam
                                    ) {
        if (ngay != null) {
            LocalDateTime startDateTime = ngay.atStartOfDay();
            LocalDateTime endDateTime = ngay.atTime(23, 59, 59, 999999999);

            List<ThongTinSD> list = ttsdService.findByNgay(startDateTime,endDateTime);
            model.addAttribute("listTTSD", list);
            model.addAttribute("search", true);
            return "thongke/view_all_thongkeTTSD";
        }
        if (thang != 0) {
            int namHienTai = Year.now().getValue();
            List<ThongTinSD> list = ttsdService.findByThangNam(thang,namHienTai);
            model.addAttribute("listTTSD", list);
            model.addAttribute("search", true);
            return "thongke/view_all_thongkeTTSD";
        }
        if (nam != 0) {
            List<ThongTinSD> list = ttsdService.findByNam(nam);
            model.addAttribute("listTTSD", list);
            model.addAttribute("search", true);
            return "thongke/view_all_thongkeTTSD";
        }
        return "redirect:/admin/thongke/TTSD";
    }
}

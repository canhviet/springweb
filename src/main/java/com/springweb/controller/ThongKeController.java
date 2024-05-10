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

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    @GetMapping("/pageTTSD/{pageNum}")
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
            model.addAttribute("ngay", true);
            model.addAttribute("day_start", startDateTime);
            model.addAttribute("day_end", endDateTime);
            return "thongke/view_all_thongkeTTSD";
        }
        if (thang != 0) {
            int namHienTai = Year.now().getValue();
            LocalDateTime startDate = LocalDateTime.of(namHienTai, thang, 1, 0, 0);
            LocalDateTime endDate = LocalDateTime.of(namHienTai, thang, YearMonth.of(namHienTai, thang).lengthOfMonth(), 23, 59, 59);
            List<ThongTinSD> list = ttsdService.findByNgay(startDate,endDate);
            model.addAttribute("listTTSD", list);
            model.addAttribute("search", true);
            model.addAttribute("thang", true);
            model.addAttribute("thang_value", thang);
            model.addAttribute("mon_start", startDate);
            model.addAttribute("mon_end", endDate);

            return "thongke/view_all_thongkeTTSD";
        }
        if (nam != 0) {
            LocalDateTime startDate = LocalDateTime.of(nam, 1, 1, 0, 0);
            LocalDateTime endDate = LocalDateTime.of(nam, 12, 31, 23, 59, 59);
            List<ThongTinSD> list = ttsdService.findByNgay(startDate,endDate);
            model.addAttribute("listTTSD", list);
            model.addAttribute("search", true);
            model.addAttribute("nam", true);
            model.addAttribute("nam_value", nam);
            model.addAttribute("year_start", startDate);
            model.addAttribute("year_end", endDate);
            return "thongke/view_all_thongkeTTSD";
        }
        return "redirect:/admin/thongke/TTSD";
    }

    @GetMapping("/TTSD/timkiemtheoten")
    public String searchTTSDTheoTen(Model model,
                                    @RequestParam(value = "txt", required = false) String searchText,
                                    @RequestParam(value = "startDay", required = false) LocalDateTime startDay,
                                    @RequestParam(value = "endDay", required = false) LocalDateTime endDay) {

        List<ThongTinSD> filteredList = new ArrayList<>();

        if (startDay != null && endDay != null && searchText != null) {
            System.out.println("---------------------- co start day");
            List<ThongTinSD> list = ttsdService.findByNgay(startDay, endDay);
            for (ThongTinSD ttsd : list) {
                if (ttsd.getTenTV().toLowerCase().contains(searchText.toLowerCase()) ||
                        ttsd.getKhoa().toLowerCase().contains(searchText.toLowerCase()) ||
                        ttsd.getNganh().toLowerCase().contains(searchText.toLowerCase())
                        ) {
                    filteredList.add(ttsd);
                }
            }
            model.addAttribute("listTTSD", filteredList);
            model.addAttribute("search", true);
            model.addAttribute("ten", true);

            return "thongke/view_all_thongkeTTSD";
        }
        if(startDay == null && endDay == null && searchText != null) {
            System.out.println("--------------------kooooooo-- co start day");

            List<ThongTinSD> list = ttsdService.findBytgVaoNotNull();
            for (ThongTinSD ttsd : list) {
                if (ttsd.getTenTV().toLowerCase().contains(searchText.toLowerCase()) ||
                        ttsd.getKhoa().toLowerCase().contains(searchText.toLowerCase()) ||
                        ttsd.getNganh().toLowerCase().contains(searchText.toLowerCase())) {
                    filteredList.add(ttsd);
                }
            }
            model.addAttribute("listTTSD", filteredList);
            model.addAttribute("search", true);
            model.addAttribute("ten", true);

            return "thongke/view_all_thongkeTTSD";
        }
        model.addAttribute("listTTSD", filteredList);
        model.addAttribute("search", true);
        model.addAttribute("ten", true);
        return "redirect:/admin/thongke/TTSD";
    }

//    *********************************************************************************
    // thong ke tb dang muon
    @GetMapping("/TBDangMuon")
    public String viewThongKeTBDangMuon(Model model) {
        return viewPageThongKeThietBiDangMuon(model, 1);
    }

    @GetMapping("/pageTBDangMuon/{pageNum}")
    public String viewPageThongKeThietBiDangMuon(Model model, @PathVariable("pageNum") int pageNum) {
        Page<ThongTinSD> page = ttsdService.getThanhVienDangMuon(pageNum);
        List<ThongTinSD> list = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("listTTSD", list);

        return "thongke/view_all_thongkeTBDangMuon";
    }

    @GetMapping("/TBDangMuon/search")
    public String searchTBDangMuon(Model model,
                                    @RequestParam(value = "startDay", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDay,
                                   @RequestParam(value = "endDay", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDay
    ) {
        if (startDay != null && endDay != null) {
            LocalDateTime startDateTime = LocalDateTime.of(startDay, LocalTime.MIN);
            LocalDateTime endDateTime = LocalDateTime.of(endDay, LocalTime.MAX);
            List<ThongTinSD> list = ttsdService.getTbDangMuonTheoTime(startDateTime, endDateTime);
            model.addAttribute("listTTSD", list);
            model.addAttribute("search", true);
            model.addAttribute("thoigian", true);
            model.addAttribute("start", startDateTime);
            model.addAttribute("end", endDateTime);

            return "thongke/view_all_thongkeTBDangMuon";
        }
        return "redirect:/admin/thongke/TBDangMuon";
    }

    @GetMapping("/TBDangMuon/homnay")
    public String GetThietBiDuocMuonHomNay(Model model) {
        LocalDateTime startOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        List<ThongTinSD> list = ttsdService.getTbDangMuonTheoTime(startOfDay,endOfDay);
        model.addAttribute("listTTSD", list);
        model.addAttribute("search", true);
        model.addAttribute("homnay", true);
        model.addAttribute("today_start", startOfDay);
        model.addAttribute("today_end", endOfDay);
        return "thongke/view_all_thongkeTBDangMuon";
    }

    @GetMapping("/TBDangMuon/trongtuan")
    public String GetThietBiDuocMuonTrongTuan(Model model) {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);

        LocalDateTime startOfDay = LocalDateTime.of(startOfWeek, LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.of(endOfWeek, LocalTime.MAX);

        List<ThongTinSD> list = ttsdService.getTbDangMuonTheoTime(startOfDay, endOfDay);
        model.addAttribute("listTTSD", list);
        model.addAttribute("search", true);
        model.addAttribute("trongtuan", true);
        model.addAttribute("week_start", startOfDay);
        model.addAttribute("week_end", endOfDay);

        return "thongke/view_all_thongkeTBDangMuon";
    }



    @GetMapping("/TBDangMuon/timkiemtheoten")
    public String searchTBDangDcMuonTheoTen(Model model,
                                            @RequestParam(value = "txt", required = false) String searchText,
                                            @RequestParam(value = "startDay", required = false) LocalDateTime startDay,
                                            @RequestParam(value = "endDay", required = false) LocalDateTime endDay) {

        List<ThongTinSD> filteredList = new ArrayList<>();

        if (startDay != null && endDay != null && searchText != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

            List<ThongTinSD> list = ttsdService.getTbDangMuonTheoTime(startDay, endDay);
            for (ThongTinSD ttsd : list) {
                if (ttsd.getTenTV().toLowerCase().contains(searchText.toLowerCase()) ||
                        ttsd.getTenTB().toLowerCase().contains(searchText.toLowerCase())) {
                    filteredList.add(ttsd);
                }
            }
            model.addAttribute("listTTSD", filteredList);
            model.addAttribute("search", true);
            model.addAttribute("ten", true);

            return "thongke/view_all_thongkeTBDangMuon";
        }
        if(startDay == null && endDay == null && searchText != null) {
            List<ThongTinSD> list = ttsdService.getThanhVienDangMuon();
            for (ThongTinSD ttsd : list) {
                if (ttsd.getTenTV().toLowerCase().contains(searchText.toLowerCase()) ||
                        ttsd.getTenTB().toLowerCase().contains(searchText.toLowerCase())) {
                    filteredList.add(ttsd);
                }
            }
            model.addAttribute("listTTSD", filteredList);
            model.addAttribute("search", true);
            model.addAttribute("ten", true);

            return "thongke/view_all_thongkeTBDangMuon";
        }
        model.addAttribute("listTTSD", filteredList);
        model.addAttribute("search", true);
        model.addAttribute("ten", true);
        return "redirect:/admin/thongke/TBDangMuon";
    }
}

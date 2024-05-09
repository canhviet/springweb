package com.springweb.service;

import com.springweb.entity.ThietBi;
import com.springweb.entity.ThongTinSD;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface TTSDService {
    Page<ThongTinSD> listAll(int pageNum);
    boolean KiemTraTrangThai(String tt, int MaTB);
    boolean KiemTraTonTai(int MaTB);

    ThongTinSD getByMaTT(int MaTT);
    List<ThongTinSD> getDatCho();
    void Save(ThongTinSD thongTinSD);

    List<ThongTinSD> getByMaTB(int MaTB);
    boolean MuonLai(int MaTB, int MaTV);
    ThongTinSD getByMaTVAndMaTB(int MaTV, int MaTB);

    Page<ThongTinSD> getThanhVienVao(int pageNum);

    List<ThongTinSD> findByThangNam(int thang, int nam);
    List<ThongTinSD> findByNam(int nam);

    List<ThongTinSD> findByNgay(LocalDateTime s, LocalDateTime e);

    Page<ThongTinSD> getThanhVienDangMuon(int pageNum);

    List<ThongTinSD> getTbDangMuonTheoTime(LocalDateTime s, LocalDateTime e);

    List<ThongTinSD> getThanhVienDangMuon();
}

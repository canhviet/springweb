package com.springweb.service;

import com.springweb.entity.ThietBi;
import com.springweb.entity.ThongTinSD;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TTSDService {
    Page<ThongTinSD> listAll(int pageNum);
    boolean KiemTraTrangThai(String tt, int MaTB);
    boolean KiemTraTonTai(int MaTB);

    void Save(ThongTinSD thongTinSD);

    ThongTinSD getByMaTT(int maTT);

    ThongTinSD getByMaTVAndMaTB(int MaTV, int MaTB);
}

package com.springweb.service;

import com.springweb.entity.ThietBi;
import com.springweb.entity.ThongTinSD;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TTSDService {
    Page<ThongTinSD> listAll(int pageNum);
    boolean KiemTraTrangThai(String tt, int MaTB);
    boolean KiemTraTonTai(int MaTB);

    ThongTinSD getByMaTT(int MaTT);
    List<ThongTinSD> getDatCho();
    void Save(ThongTinSD thongTinSD);

}

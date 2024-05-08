package com.springweb.service;

import com.springweb.entity.ThanhVien;
import com.springweb.entity.ThietBi;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ThietBiService {
    Page<ThietBi> listAll(int numPage);

    List<ThietBi> searchList(String keyword);

    void SaveThietBi(ThietBi tb);

    void DelteThietBi(Integer id);

    List<ThietBi> getAllSearch(String keyword);

    ThietBi getByMaTB(Integer MaTB);
}

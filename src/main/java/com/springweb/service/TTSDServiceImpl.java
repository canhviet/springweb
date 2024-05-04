package com.springweb.service;

import com.springweb.entity.ThietBi;
import com.springweb.entity.ThongTinSD;
import com.springweb.repository.TTSDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TTSDServiceImpl implements TTSDService{
    @Autowired
    private TTSDRepository ttsdRepository;

    @Override
    public boolean KiemTraTrangThai(String tt, int MaTB) {
        return ttsdRepository.KiemTraTrangThai(tt, MaTB);
    }

    @Override
    public boolean KiemTraTonTai(int MaTB) {
        return ttsdRepository.KiemTraTonTai(MaTB);
    }

    @Override
    public void Save(ThongTinSD thongTinSD) {
        ttsdRepository.save(thongTinSD);
    }
}

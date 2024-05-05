package com.springweb.service;

import com.springweb.entity.ThanhVien;
import com.springweb.entity.ThietBi;
import com.springweb.entity.ThongTinSD;
import com.springweb.repository.TTSDRepository;
import com.springweb.repository.ThanhVienRepository;
import com.springweb.repository.ThietBiRepository;
import org.hibernate.Hibernate;
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
    @Autowired
    private ThanhVienRepository thanhVienRepository;
    @Autowired
    private ThietBiRepository thietBiRepository;

    @Override
    public Page<ThongTinSD> listAll(int pageNum) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<ThongTinSD> thongTinSDPage = ttsdRepository.findAll(pageable);

        for (ThongTinSD thongTinSD : thongTinSDPage) {
            Hibernate.initialize(thongTinSD.getThanhVien());
            Hibernate.initialize(thongTinSD.getThietBi());
        }

        return thongTinSDPage;
    }
    @Override
    public ThanhVien getByMaTV(Integer id) {
        return thanhVienRepository.getByMaTV(id);
    }
    @Override
    public ThietBi getByMaTB(Integer id) {
        return thietBiRepository.getByMaTB(id);
    }
}

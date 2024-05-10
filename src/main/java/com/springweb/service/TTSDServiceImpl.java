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

import java.time.*;
import java.util.List;

@Service
public class TTSDServiceImpl implements TTSDService{
    @Autowired
    private TTSDRepository ttsdRepository;

    @Override
    public Page<ThongTinSD> listAll(int pageNum) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<ThongTinSD> thongTinSDPage = ttsdRepository.findAllTrangThai(pageable);

        return thongTinSDPage;
    }

    @Override
    public boolean KiemTraTonTai(int MaTB) {
        return ttsdRepository.KiemTraTonTai(MaTB);
    }

    @Override
    public List<ThongTinSD> getDatCho() {
        return ttsdRepository.getThietBiDangDat();
    }


    @Override
    public void Save(ThongTinSD thongTinSD) {
        ttsdRepository.save(thongTinSD);
    }

    @Override
    public List<ThongTinSD> getByMaTB(int MaTB) {
        return ttsdRepository.getByMaTB(MaTB);
    }

    @Override
    public boolean MuonLai(int MaTB, int MaTV) {
        return ttsdRepository.MuonLai(MaTB, MaTV);
    }

    @Override
    public ThongTinSD getByMaTT(int MaTT) {
        return ttsdRepository.getByMaTT(MaTT);
    }

    @Override
    public ThongTinSD getByMaTVAndMaTB(int MaTV, int MaTB) {
        return ttsdRepository.getByMaTVAndMaTB(MaTV, MaTB);
    }



    @Override
    public Page<ThongTinSD> getThanhVienVao(int pageNum) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return ttsdRepository.findBytgVaoNotNull(pageable);
    }

    @Override // tg vao
    public List<ThongTinSD> findByNgay(LocalDateTime s, LocalDateTime e) {
        return ttsdRepository.findByThangNam(s,e);
    }


    // tg muon
    @Override
    public Page<ThongTinSD> getThanhVienDangMuon(int pageNum) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return ttsdRepository.findByTgMuonNotNull(pageable);
    }

    @Override
    public List<ThongTinSD> getThanhVienDangMuon() {
        return ttsdRepository.findByTgMuonNotNull();
    }

    @Override
    public List<ThongTinSD> findBytgVaoNotNull() {
        return ttsdRepository.findBytgVaoNotNull();
    }

    @Override
    public List<ThongTinSD> getTbDangMuonTheoTime(LocalDateTime s, LocalDateTime e) {
        return ttsdRepository.getTbDangMuonTheoTime(s,e);
    }


}

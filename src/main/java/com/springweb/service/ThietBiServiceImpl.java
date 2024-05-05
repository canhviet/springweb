package com.springweb.service;

import com.springweb.entity.ThietBi;
import com.springweb.repository.ThanhVienRepository;
import com.springweb.repository.ThietBiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThietBiServiceImpl implements ThietBiService{

    @Autowired
    private ThietBiRepository thietBiRepository;
    @Override
    public Page<ThietBi> listAll(int pageNum) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        return thietBiRepository.findAll(pageable);
    }

    @Override
    public List<ThietBi> searchList(String keyword) {
        return thietBiRepository.findByTenTBContaining(keyword);
    }

    @Override
    public ThietBi getByMaTB(Integer MaTB) {
        return thietBiRepository.getByMaTB(MaTB);
    }

    @Override
    public void SaveThietBi(ThietBi tb) {
        thietBiRepository.save(tb);
    }

    @Override
    public void DelteThietBi(Integer id) {
        thietBiRepository.deleteById(id);
    }

}

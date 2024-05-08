package com.springweb.service;

import com.springweb.entity.XuLy;
import com.springweb.repository.XuLyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XuLyServiceImpl implements XuLyService{

    @Autowired
    private XuLyRepository xuLyRepository;
    @Override
    public Page<XuLy> getList(int pageNum) {

        int pageSize = 5;

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);

        return xuLyRepository.findAll(pageable);
    }

    @Override
    public XuLy getById(int id) {
        return  xuLyRepository.getById(id);
    }

    @Override
    public void createXuLy(XuLy obj) {
        xuLyRepository.save(obj);
    }

    @Override
    public void updateXuLy(XuLy obj) {
        xuLyRepository.save(obj);
    }

    @Override
    public void deleteXuLy(XuLy obj) {
        xuLyRepository.delete(obj);
    }

    @Override
    public void deleteXuLyById(int id) {
        xuLyRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {

    }
    @Override
    public List<XuLy> searchList(String keyword) {
        return xuLyRepository.findByHinhThucXlContains(keyword);
    }
}

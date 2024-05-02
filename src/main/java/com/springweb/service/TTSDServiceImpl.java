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
    public Page<ThietBi> listAll(int PageNum) {
        int pageSize = 5;

        Pageable pageable = PageRequest.of(PageNum - 1, pageSize);

        return ttsdRepository.getAll(pageable);
    }
}

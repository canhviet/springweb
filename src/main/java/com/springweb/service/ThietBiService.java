package com.springweb.service;

import com.springweb.entity.ThietBi;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ThietBiService {
    Page<ThietBi> listAll(int pageNum);

    List<ThietBi> getAllSearch(String keyword);
}

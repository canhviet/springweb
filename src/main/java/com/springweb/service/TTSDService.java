package com.springweb.service;

import com.springweb.entity.ThietBi;
import com.springweb.entity.ThongTinSD;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TTSDService {
    Page<ThietBi> listAll(int PageNum);
}

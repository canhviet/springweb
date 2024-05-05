package com.springweb.service;

import com.springweb.entity.ThongTinSD;
import org.springframework.data.domain.Page;

public interface TTSDService {
    Page<ThongTinSD> listAll(int pageNum);
}

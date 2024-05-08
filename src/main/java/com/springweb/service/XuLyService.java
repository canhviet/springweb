package com.springweb.service;

import com.springweb.entity.XuLy;
import org.springframework.data.domain.Page;

public interface XuLyService {
    Page<XuLy> getList(int pageNum);
    void createXuLy(XuLy obj);
    void updateXuLy(XuLy obj);
    void deleteXuLy(XuLy obj);
    void deleteXuLyById(int id);

    void deleteAll();

}

package com.springweb.service;

import com.springweb.entity.XuLy;
import org.springframework.data.domain.Page;

import java.util.List;

public interface XuLyService {
    Page<XuLy> getList(int pageNum);
    XuLy getById(int id);
    void createXuLy(XuLy obj);
    void updateXuLy(XuLy obj);
    void deleteXuLy(XuLy obj);
    void deleteXuLyById(int id);

    void deleteAll();
    List<XuLy> searchList(String keyword);
}

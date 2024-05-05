package com.springweb.repository;

import com.springweb.entity.ThanhVien;
import com.springweb.entity.ThietBi;
import com.springweb.entity.ThongTinSD;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TTSDRepository extends JpaRepository<ThongTinSD, Integer> {

    List<ThongTinSD> getByThanhVien(ThanhVien thanhVien);
    List<ThongTinSD> getByThietBi(ThietBi thietBi);
}

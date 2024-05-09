package com.springweb.repository;

import com.springweb.entity.XuLy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface XuLyRepository extends JpaRepository<XuLy, Integer>{
    List<XuLy> findByHinhThucXlContains(String key);

    @Query("select case when count(xl) > 0 then true else false end from XuLy xl where xl.thanhVien.maTV =:maTV and xl.trangThai = 0 ")
    boolean DangViPham(@Param("maTV") int MaTV);
}

package com.springweb.repository;

import com.springweb.entity.ThietBi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThietBiRepository extends JpaRepository<ThietBi, Integer> {


    List<ThietBi> findByTenTBContaining(String keyword);
    ThietBi getByMaTB(Integer MaTB);
}

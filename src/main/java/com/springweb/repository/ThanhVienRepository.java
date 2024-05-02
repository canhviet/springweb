package com.springweb.repository;

import com.springweb.entity.ThanhVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ThanhVienRepository extends JpaRepository<ThanhVien, Integer> {
    Boolean existsByMaTVAndPassword(Integer MaTV, String password);

    ThanhVien getByMaTV(Integer id);

    List<ThanhVien> findByTenContaining(String keyword);
}

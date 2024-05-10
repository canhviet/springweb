package com.springweb.repository;

import com.springweb.entity.ThongTinSD;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TTSDRepository extends JpaRepository<ThongTinSD, Integer> {

    @Query("select case when count(ttsd) > 0 then true else false end from ThongTinSD ttsd where ttsd.thietBi.maTB =:maTB")
    boolean KiemTraTonTai(@Param("maTB") int MaTB);

    @Query("select case when count(ttsd) > 0 then true else false end from ThongTinSD ttsd where ttsd.thietBi.maTB =:maTB and ttsd.thanhVien.maTV =:maTV")
    boolean MuonLai(@Param("maTB") int MaTB, @Param("maTV") int MaTV);

    @Query("select distinct tt from ThongTinSD tt where tt.trang_thai = 'dang dat cho'")
    List<ThongTinSD> getThietBiDangDat();

    Page<ThongTinSD> findBytgVaoNotNull(Pageable pageable);

    List<ThongTinSD> findBytgVaoNotNull();


    @Query("SELECT tt FROM ThongTinSD tt WHERE tt.tgVao >= :startDate AND tt.tgVao < :endDate")
    List<ThongTinSD> findByThangNam(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("select distinct tt from ThongTinSD tt where tt.trang_thai = 'dang cho muon' or tt.trang_thai = 'dang dat cho'")
    Page<ThongTinSD> findAllTrangThai(Pageable pageable);


    ThongTinSD getByMaTT(int MaTT);

    List<ThongTinSD> getByMaTB(int MaTB);

    ThongTinSD getByMaTVAndMaTB(int MaTV, int MaTB);

    Page<ThongTinSD> findByTgMuonNotNull(Pageable pageable);

    List<ThongTinSD> findByTgMuonNotNull();




    @Query("SELECT tt FROM ThongTinSD tt WHERE tt.tgMuon >= :startDate AND tt.tgMuon < :endDate")
    List<ThongTinSD> getTbDangMuonTheoTime(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);


}

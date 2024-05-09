package com.springweb.repository;

import com.springweb.entity.ThanhVien;
import com.springweb.entity.ThietBi;
import com.springweb.entity.ThongTinSD;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TTSDRepository extends JpaRepository<ThongTinSD, Integer> {
    @Query("select case when count(tb) > 0 then true else false end from ThietBi tb join tb.listTTSD ttsd where ttsd.trang_thai =:trangthai and ttsd.thietBi.maTB =:maTB")
    boolean KiemTraTrangThai(@Param("trangthai") String tt, @Param("maTB") int MaTB);

    @Query("select case when count(ttsd) > 0 then true else false end from ThongTinSD ttsd where ttsd.thietBi.maTB =:maTB")
    boolean KiemTraTonTai(@Param("maTB") int MaTB);
    ThongTinSD getByMaTT(int MaTT);

    ThongTinSD getByMaTVAndMaTB(int MaTV, int MaTB);
}

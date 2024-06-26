package com.springweb.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.util.Date;

@Entity
@Data
@Table(name = "xuly")
public class XuLy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaXL")
    private int maXl;

    @Column(name = "hinh_thucxl")
    @NotNull(message = "Không được để trống hình thức xử lý")
    private String hinhThucXl;

    @Column(name = "so_tien")
    private int soTien;

    @Column(name = "NgayXL")
    @NotNull(message = "Vui lòng chọn ngày xử lý")
    private java.time.LocalDateTime ngayXl;

    @Column(name = "trang_thaixl")
    private int trangThai;

    @Column(name="MaTV")
    @NotNull(message = "Vui lòng chọn thành viên")
    private int maTv;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaTV", insertable = false, updatable = false)
    private ThanhVien thanhVien;

    public String getTenTV() {
        return thanhVien.getTen();
    }
    public String getKhoa() {
        return thanhVien.getKhoa();
    }

    public String getNganh() {
        return thanhVien.getNganh();
    }
}

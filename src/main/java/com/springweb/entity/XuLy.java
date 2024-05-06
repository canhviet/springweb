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

    @Column(name = "HinhThucXL")
    @NotNull(message = "Không được để trống hình thức xử lý")
    private String hinhThucXl;

    @Column(name = "SoTien")
    private int soTien;

    @Column(name = "NgayXL")
    private Date ngayXl;

    @Column(name = "TrangThai")
    private int trangThai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaTV", insertable = false, updatable = false)
    private ThanhVien thanhVien;
}

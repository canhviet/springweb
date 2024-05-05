package com.springweb.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "thongtinsd")
public class ThongTinSD {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MaTT")
    private int maTT;

    @Column(name = "tgvao")
    private LocalDateTime tgVao;

    @Column(name = "tgmuon")
    private LocalDateTime tgMuon;

    @Column(name = "tgtra")
    private LocalDateTime tgTra;

    @Column(name = "tgdat_cho")
    private LocalDateTime tgDatCho;

    @Column(name = "trang_thai")
    private String trangThai;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaTV")
    private ThanhVien thanhVien;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MaTB")
    private ThietBi thietBi;
}


package model;

import java.util.Date;

public class Product {
    private String idSanPham;
    private String tenSanPham;
    private String idDuAn;
    private String loaiHinh;   // căn hộ chung cư, nhà ở, lô đất
    private String diaChi;
    private Double dienTich;
    private Double giaTien;
    private String moTa;      // phòng ở tầng bao nhiêu, vị thế, thuận lợi…
    private Date ngayTao;
    private Date ngayBan;
    private String chiTiet;   // đang xây dựng , đã hoàn thành..
    private String trangThai; // đã bán, chưa bán
    private String kyGui; // yes or no
    private String idNguoiDung;

    public Product() {
    }

    public Product(String idSanPham, String tenSanPham, String idDuAn, String loaiHinh, String diaChi, Double dienTich, Double giaTien, String moTa, Date ngayTao, Date ngayBan, String chiTiet, String trangThai, String kyGui, String idNguoiDung) {
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.idDuAn = idDuAn;
        this.loaiHinh = loaiHinh;
        this.diaChi = diaChi;
        this.dienTich = dienTich;
        this.giaTien = giaTien;
        this.moTa = moTa;
        this.ngayTao = ngayTao;
        this.ngayBan = ngayBan;
        this.chiTiet = chiTiet;
        this.trangThai = trangThai;
        this.kyGui = kyGui;
        this.idNguoiDung = idNguoiDung;
    }
}

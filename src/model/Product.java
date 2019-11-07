package model;

import java.util.Date;

public class Product {
    private String idSanPham;
    private String tenSanPham;
    private String idDuAn;
    private String tenDuAn;
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

    public Product(String idSanPham, String tenSanPham, String idDuAn, String tenDuAn, String loaiHinh, String diaChi, Double dienTich, Double giaTien, String moTa, Date ngayTao, Date ngayBan, String chiTiet, String trangThai, String kyGui, String idNguoiDung) {
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.idDuAn = idDuAn;
        this.tenDuAn = tenDuAn;
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

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getIdDuAn() {
        return idDuAn;
    }

    public void setIdDuAn(String idDuAn) {
        this.idDuAn = idDuAn;
    }

    public String getTenDuAn() {
        return tenDuAn;
    }

    public void setTenDuAn(String tenDuAn) {
        this.tenDuAn = tenDuAn;
    }

    public String getLoaiHinh() {
        return loaiHinh;
    }

    public void setLoaiHinh(String loaiHinh) {
        this.loaiHinh = loaiHinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Double getDienTich() {
        return dienTich;
    }

    public void setDienTich(Double dienTich) {
        this.dienTich = dienTich;
    }

    public Double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(Double giaTien) {
        this.giaTien = giaTien;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(Date ngayBan) {
        this.ngayBan = ngayBan;
    }

    public String getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getKyGui() {
        return kyGui;
    }

    public void setKyGui(String kyGui) {
        this.kyGui = kyGui;
    }

    public String getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(String idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }
}

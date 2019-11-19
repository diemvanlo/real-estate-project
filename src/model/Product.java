package model;

import java.util.Date;

public class Product {
    private int idSanPham;
    private String tenSanPham;
    private int idDuAn;
    // private String tenDuAn;
    private String diaChi;
    private Double dienTich;
    private Double giaTien;
    private String moTa;      // phòng ở tầng bao nhiêu, vị thế, thuận lợi…
    private String ngayTao;
    private String ngayBan;
    private String tienDo;   // 10 %, 20% , 30% .....
    private String trangThai; // đã bán, chưa bán
    private int idKhachHang; // nguoi mua sp

    public Product() {
    }

    public Product(int idSanPham, String tenSanPham, int idDuAn, String diaChi, Double dienTich, Double giaTien, String moTa, String ngayTao, String ngayBan, String tienDo, String trangThai) {
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.idDuAn = idDuAn;
        this.diaChi = diaChi;
        this.dienTich = dienTich;
        this.giaTien = giaTien;
        this.moTa = moTa;
        this.ngayTao = ngayTao;
        this.ngayBan = ngayBan;
        this.tienDo = tienDo;
        this.trangThai = trangThai;
    }

    public Product(int idSanPham, String tenSanPham, int idDuAn, String diaChi, Double dienTich, Double giaTien, String moTa, String ngayTao, String ngayBan, String tienDo, String trangThai, int idKhachHang) {
        this.idSanPham = idSanPham;
        this.tenSanPham = tenSanPham;
        this.idDuAn = idDuAn;
        this.diaChi = diaChi;
        this.dienTich = dienTich;
        this.giaTien = giaTien;
        this.moTa = moTa;
        this.ngayTao = ngayTao;
        this.ngayBan = ngayBan;
        this.tienDo = tienDo;
        this.trangThai = trangThai;
        this.idKhachHang = idKhachHang;
    }


    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getIdDuAn() {
        return idDuAn;
    }

    public void setIdDuAn(int idDuAn) {
        this.idDuAn = idDuAn;
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

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(String ngayBan) {
        this.ngayBan = ngayBan;
    }

    public String getTienDo() {
        return tienDo;
    }

    public void setTienDo(String tienDo) {
        this.tienDo = tienDo;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }
}

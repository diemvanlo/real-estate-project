package model;

import java.io.InputStream;
import java.util.Date;

public class KiGuiSp {
    private String idSanPham;
    private String tenKH;      // tên khách hàng kí gửi SP
    private String tenSP;      // tên SP kí gửi
    private String loaiHinh;   // căn hộ chung cư, nhà ở, lô đất
    private double dienTich;
    private double giaSP;      // giá sản phẩm
    private String diaChi;
    private String moTa;
    private InputStream hinhAnh;  // hình ảnh sp
    private Date ngayKiGui;
    private String sdt;
    private String email;

    public KiGuiSp() {
    }

    public KiGuiSp(String idSanPham, String tenKH, String tenSP, String loaiHinh, double dienTich, double giaSP, String diaChi, String moTa, InputStream hinhAnh, Date ngayKiGui, String sdt, String email) {
        this.idSanPham = idSanPham;
        this.tenKH = tenKH;
        this.tenSP = tenSP;
        this.loaiHinh = loaiHinh;
        this.dienTich = dienTich;
        this.giaSP = giaSP;
        this.diaChi = diaChi;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
        this.ngayKiGui = ngayKiGui;
        this.sdt = sdt;
        this.email = email;
    }

    public String getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getLoaiHinh() {
        return loaiHinh;
    }

    public void setLoaiHinh(String loaiHinh) {
        this.loaiHinh = loaiHinh;
    }

    public double getDienTich() {
        return dienTich;
    }

    public void setDienTich(double dienTich) {
        this.dienTich = dienTich;
    }

    public double getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(double giaSP) {
        this.giaSP = giaSP;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public InputStream getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(InputStream hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Date getNgayKiGui() {
        return ngayKiGui;
    }

    public void setNgayKiGui(Date ngayKiGui) {
        this.ngayKiGui = ngayKiGui;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

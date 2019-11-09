package model;

import java.util.Date;

public class Project {         // dự án
    private int idDuAn;
    private String tenDuAn;
    private String loaiHinh;        // lô đất, chung cư, nhà ở, biệt thự, khu du lịch ..
    private String diaChi;
    private Double dienTich;
    private Double chiPhiDuAn;
    private String mucTieu;         // hoàn thành trong 2 năm,....
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String hinhThucQuanLi;  // chung cư, tgian sở hữu 10 năm ...
    private String hinhThucDauTu;   // 5% giá trị dự án. hoặc số tiền cụ thể
    private int idDoiTac;
    // private String TenDoiTac;       // tên đối tác đầu tư
    private String trangThai;       // đã đủ ( số tiền / chủ ) đầu tư, chưa đủ …
    private String mapX;            // tọa độ vị trí theo vĩ tuyến
    private String mapY;            // tọa độ vị trí theo kinh tuyến
    private Double banKinh;

    public Project() {
    }

    public Project(int idDuAn, String tenDuAn, String loaiHinh, String diaChi, Double dienTich, Double chiPhiDuAn, String mucTieu, Date ngayBatDau, Date ngayKetThuc, String hinhThucQuanLi, String hinhThucDauTu, int idDoiTac, String trangThai, String mapX, String mapY, Double banKinh) {
        this.idDuAn = idDuAn;
        this.tenDuAn = tenDuAn;
        this.loaiHinh = loaiHinh;
        this.diaChi = diaChi;
        this.dienTich = dienTich;
        this.chiPhiDuAn = chiPhiDuAn;
        this.mucTieu = mucTieu;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.hinhThucQuanLi = hinhThucQuanLi;
        this.hinhThucDauTu = hinhThucDauTu;
        this.idDoiTac = idDoiTac;
        this.trangThai = trangThai;
        this.mapX = mapX;
        this.mapY = mapY;
        this.banKinh = banKinh;
    }

    public int getIdDuAn() {
        return idDuAn;
    }

    public void setIdDuAn(int idDuAn) {
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

    public Double getChiPhiDuAn() {
        return chiPhiDuAn;
    }

    public void setChiPhiDuAn(Double chiPhiDuAn) {
        this.chiPhiDuAn = chiPhiDuAn;
    }

    public String getMucTieu() {
        return mucTieu;
    }

    public void setMucTieu(String mucTieu) {
        this.mucTieu = mucTieu;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getHinhThucQuanLi() {
        return hinhThucQuanLi;
    }

    public void setHinhThucQuanLi(String hinhThucQuanLi) {
        this.hinhThucQuanLi = hinhThucQuanLi;
    }

    public String getHinhThucDauTu() {
        return hinhThucDauTu;
    }

    public void setHinhThucDauTu(String hinhThucDauTu) {
        this.hinhThucDauTu = hinhThucDauTu;
    }

    public int getIdDoiTac() {
        return idDoiTac;
    }

    public void setIdDoiTac(int idDoiTac) {
        this.idDoiTac = idDoiTac;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMapX() {
        return mapX;
    }

    public void setMapX(String mapX) {
        this.mapX = mapX;
    }

    public String getMapY() {
        return mapY;
    }

    public void setMapY(String mapY) {
        this.mapY = mapY;
    }

    public Double getBanKinh() {
        return banKinh;
    }

    public void setBanKinh(Double banKinh) {
        this.banKinh = banKinh;
    }
}

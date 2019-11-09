package model;

public class DanhGiaKhachHang {
    private String idKhachHang;
    private int soSaoBinhChon;

    public DanhGiaKhachHang() {
    }

    public DanhGiaKhachHang(String idKhachHang, int soSaoBinhChon) {
        this.idKhachHang = idKhachHang;
        this.soSaoBinhChon = soSaoBinhChon;
    }

    public String getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(String idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public int getSoSaoBinhChon() {
        return soSaoBinhChon;
    }

    public void setSoSaoBinhChon(int soSaoBinhChon) {
        this.soSaoBinhChon = soSaoBinhChon;
    }
}

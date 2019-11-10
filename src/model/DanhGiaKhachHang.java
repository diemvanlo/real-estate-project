package model;

public class DanhGiaKhachHang {
    private int idKhachHang;
    private int soSaoBinhChon;

    public DanhGiaKhachHang() {
    }

    public DanhGiaKhachHang(int idKhachHang, int soSaoBinhChon) {
        this.idKhachHang = idKhachHang;
        this.soSaoBinhChon = soSaoBinhChon;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public int getSoSaoBinhChon() {
        return soSaoBinhChon;
    }

    public void setSoSaoBinhChon(int soSaoBinhChon) {
        this.soSaoBinhChon = soSaoBinhChon;
    }
}

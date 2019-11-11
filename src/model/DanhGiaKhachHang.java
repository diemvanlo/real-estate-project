package model;

public class DanhGiaKhachHang {
    private int idKhachHang;
    private int soSaoBinhChon;
    private String nhanXet;

    public DanhGiaKhachHang() {
    }

    public DanhGiaKhachHang(int idKhachHang, int soSaoBinhChon, String nhanXet) {
        this.idKhachHang = idKhachHang;
        this.soSaoBinhChon = soSaoBinhChon;
        this.nhanXet = nhanXet;
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

    public String getNhanXet() {
        return nhanXet;
    }

    public void setNhanXet(String nhanXet) {
        this.nhanXet = nhanXet;
    }
}

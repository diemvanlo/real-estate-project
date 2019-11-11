package model;

public class DanhGiaKhachHang {
    private int idKhachHang;
    private int soSaoBinhChon;
    private String nhanXet;
    private int idSanPham;

    public DanhGiaKhachHang() {
    }

    public DanhGiaKhachHang(int idKhachHang, int soSaoBinhChon, String nhanXet, int idSanPham) {
        this.idKhachHang = idKhachHang;
        this.soSaoBinhChon = soSaoBinhChon;
        this.nhanXet = nhanXet;
        this.idSanPham = idSanPham;
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

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }
}

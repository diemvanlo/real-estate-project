package model;

public class DanhGiaKhachHang {
    private String idNguoiDung;
    private int soSaoBinhChon;

    public String getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(String idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public int getSoSaoBinhChon() {
        return soSaoBinhChon;
    }

    public void setSoSaoBinhChon(int soSaoBinhChon) {
        this.soSaoBinhChon = soSaoBinhChon;
    }

    public DanhGiaKhachHang(String idNguoiDung, int soSaoBinhChon) {
        this.idNguoiDung = idNguoiDung;
        this.soSaoBinhChon = soSaoBinhChon;
    }

    public DanhGiaKhachHang() {
    }

}

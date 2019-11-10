package model;

public class DanhGiaKhachHang {
    private int idKhachHang;
    private String name;
    private String email;
    private int soSaoBinhChon;
    private String sdt;
    private String diaChi;

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSoSaoBinhChon() {
        return soSaoBinhChon;
    }

    public void setSoSaoBinhChon(int soSaoBinhChon) {
        this.soSaoBinhChon = soSaoBinhChon;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public DanhGiaKhachHang() {
    }

    public DanhGiaKhachHang(int idKhachHang, String name, String email, int soSaoBinhChon, String sdt, String diaChi) {
        this.idKhachHang = idKhachHang;
        this.name = name;
        this.email = email;
        this.soSaoBinhChon = soSaoBinhChon;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }
}

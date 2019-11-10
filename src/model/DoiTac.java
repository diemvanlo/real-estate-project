package model;

import java.io.InputStream;

public class DoiTac {
    private int idDoiTac;
    private String tenDoitac;
    private String linhVuc;
    private String diaChi;
    private String sdt;
    private String email;
    private InputStream logo;
    private double soVonDaDauTu;  // số vốn đã đầu tư

    public DoiTac() {
    }

    public DoiTac(int idDoiTac, String tenDoitac, String linhVuc, String diaChi, String sdt, String email, InputStream logo, double soVonDaDauTu) {
        this.idDoiTac = idDoiTac;
        this.tenDoitac = tenDoitac;
        this.linhVuc = linhVuc;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.logo = logo;
        soVonDaDauTu = soVonDaDauTu;
    }

    public int getIdDoiTac() {
        return idDoiTac;
    }

    public void setIdDoiTac(int idDoiTac) {
        this.idDoiTac = idDoiTac;
    }

    public String getTenDoitac() {
        return tenDoitac;
    }

    public void setTenDoitac(String tenDoitac) {
        this.tenDoitac = tenDoitac;
    }

    public String getLinhVuc() {
        return linhVuc;
    }

    public void setLinhVuc(String linhVuc) {
        this.linhVuc = linhVuc;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
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

    public InputStream getLogo() {
        return logo;
    }

    public void setLogo(InputStream logo) {
        this.logo = logo;
    }

    public double getsoVonDaDauTu() {
        return soVonDaDauTu;
    }

    public void setsoVonDaDauTu(double soVonDaDauTu) {
        soVonDaDauTu = soVonDaDauTu;
    }
}

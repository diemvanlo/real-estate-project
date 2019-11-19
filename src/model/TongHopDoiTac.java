package model;

public class TongHopDoiTac {
    private String tenDoitac;
    private int SoDuAnDaDauTu;
    private double SoVonDaDauTu;

    public TongHopDoiTac() {
    }

    public TongHopDoiTac(String tenDoitac, int soDuAnDaDauTu, double soVonDaDauTu) {
        this.tenDoitac = tenDoitac;
        SoDuAnDaDauTu = soDuAnDaDauTu;
        SoVonDaDauTu = soVonDaDauTu;
    }

    public String getTenDoitac() {
        return tenDoitac;
    }

    public void setTenDoitac(String tenDoitac) {
        this.tenDoitac = tenDoitac;
    }

    public int getSoDuAnDaDauTu() {
        return SoDuAnDaDauTu;
    }

    public void setSoDuAnDaDauTu(int soDuAnDaDauTu) {
        SoDuAnDaDauTu = soDuAnDaDauTu;
    }

    public double getSoVonDaDauTu() {
        return SoVonDaDauTu;
    }

    public void setSoVonDaDauTu(double soVonDaDauTu) {
        SoVonDaDauTu = soVonDaDauTu;
    }
}

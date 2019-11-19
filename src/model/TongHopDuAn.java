package model;

public class TongHopDuAn {
    private int Nam;
    private String tenDuAn;
    private int SoSP;
    private int SoNhaDauTu;
    private int SoKH;
    private Double DoanhThu;

    public TongHopDuAn() {
    }

    public TongHopDuAn(int nam, String tenDuAn, int soSP, int soNhaDauTu, int soKH, Double doanhThu) {
        Nam = nam;
        this.tenDuAn = tenDuAn;
        SoSP = soSP;
        SoNhaDauTu = soNhaDauTu;
        SoKH = soKH;
        DoanhThu = doanhThu;
    }

    public int getNam() {
        return Nam;
    }

    public void setNam(int nam) {
        Nam = nam;
    }

    public String getTenDuAn() {
        return tenDuAn;
    }

    public void setTenDuAn(String tenDuAn) {
        this.tenDuAn = tenDuAn;
    }

    public int getSoSP() {
        return SoSP;
    }

    public void setSoSP(int soSP) {
        SoSP = soSP;
    }

    public int getSoNhaDauTu() {
        return SoNhaDauTu;
    }

    public void setSoNhaDauTu(int soNhaDauTu) {
        SoNhaDauTu = soNhaDauTu;
    }

    public int getSoKH() {
        return SoKH;
    }

    public void setSoKH(int soKH) {
        SoKH = soKH;
    }

    public Double getDoanhThu() {
        return DoanhThu;
    }

    public void setDoanhThu(Double doanhThu) {
        DoanhThu = doanhThu;
    }
}

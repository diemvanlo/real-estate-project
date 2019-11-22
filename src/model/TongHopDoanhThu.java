package model;

public class TongHopDoanhThu {
    private int idDuAn;
    private int SoSp;
    private double TongDoanhThu;
    private double DoanhThuCaoNhat;
    private double DoanhThuThapNhat;
    private double DoanhThuTB;   // doah thu trung bình năm

    public TongHopDoanhThu() {
    }

    public int getIdDuAn() {
        return idDuAn;
    }

    public void setIdDuAn(int idDuAn) {
        this.idDuAn = idDuAn;
    }

    public int getSoSp() {
        return SoSp;
    }

    public void setSoSp(int soSp) {
        SoSp = soSp;
    }

    public double getTongDoanhThu() {
        return TongDoanhThu;
    }

    public void setTongDoanhThu(double tongDoanhThu) {
        TongDoanhThu = tongDoanhThu;
    }

    public double getDoanhThuCaoNhat() {
        return DoanhThuCaoNhat;
    }

    public void setDoanhThuCaoNhat(double doanhThuCaoNhat) {
        DoanhThuCaoNhat = doanhThuCaoNhat;
    }

    public double getDoanhThuThapNhat() {
        return DoanhThuThapNhat;
    }

    public void setDoanhThuThapNhat(double doanhThuThapNhat) {
        DoanhThuThapNhat = doanhThuThapNhat;
    }

    public double getDoanhThuTB() {
        return DoanhThuTB;
    }

    public void setDoanhThuTB(double doanhThuTB) {
        DoanhThuTB = doanhThuTB;
    }

    public TongHopDoanhThu(int idDuAn, int soSp, double tongDoanhThu, double doanhThuCaoNhat, double doanhThuThapNhat, double doanhThuTB) {
        this.idDuAn = idDuAn;
        SoSp = soSp;
        TongDoanhThu = tongDoanhThu;
        DoanhThuCaoNhat = doanhThuCaoNhat;
        DoanhThuThapNhat = doanhThuThapNhat;
        DoanhThuTB = doanhThuTB;
    }
}

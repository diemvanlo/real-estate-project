package model;

public class TongHopDoanhThu {
    private int Nam;
    private int SoDuAn;
    private int SoSp;
    private double TongDoanhThu;
    private double DoanhThuCaoNhat;
    private double DoanhThuThapNhat;
    private double DoanhThuTB;   // doah thu trung bình năm

    public TongHopDoanhThu() {
    }

    public TongHopDoanhThu(int nam, int soDuAn, int soSp, double tongDoanhThu, double doanhThuCaoNhat, double doanhThuThapNhat, double doanhThuTB) {
        Nam = nam;
        SoDuAn = soDuAn;
        SoSp = soSp;
        TongDoanhThu = tongDoanhThu;
        DoanhThuCaoNhat = doanhThuCaoNhat;
        DoanhThuThapNhat = doanhThuThapNhat;
        DoanhThuTB = doanhThuTB;
    }

    public int getNam() {
        return Nam;
    }

    public void setNam(int nam) {
        Nam = nam;
    }

    public int getSoDuAn() {
        return SoDuAn;
    }

    public void setSoDuAn(int soDuAn) {
        SoDuAn = soDuAn;
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
}

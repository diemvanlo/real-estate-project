package Model;

public class phantichDuAnModel {
    private int soNguoiTiepCan;   // số người tiếp cận dự án ( xem, tìm hiểu về dự án )
    private int soNguoiDauTu;     // số người đã đầu tư vào dự án
    private int soNguoiMuaSP;     // số khách hàng đặt mua sp của dự án ( 1 )
    private float KhaNangSinhLoi;       // KhaNangSinhLoi = soNguoiMuaSP*100/soNguoiTiepCan  ( % cơ hội bán sp )

    public phantichDuAnModel() {
    }

    public phantichDuAnModel(int soNguoiTiepCan, int soNguoiDauTu, int soNguoiMuaSP, float khaNangSinhLoi) {
        this.soNguoiTiepCan = soNguoiTiepCan;
        this.soNguoiDauTu = soNguoiDauTu;
        this.soNguoiMuaSP = soNguoiMuaSP;
        KhaNangSinhLoi = khaNangSinhLoi;
    }

    public int getSoNguoiTiepCan() {
        return soNguoiTiepCan;
    }

    public void setSoNguoiTiepCan(int soNguoiTiepCan) {
        this.soNguoiTiepCan = soNguoiTiepCan;
    }

    public int getSoNguoiDauTu() {
        return soNguoiDauTu;
    }

    public void setSoNguoiDauTu(int soNguoiDauTu) {
        this.soNguoiDauTu = soNguoiDauTu;
    }

    public int getSoNguoiMuaSP() {
        return soNguoiMuaSP;
    }

    public void setSoNguoiMuaSP(int soNguoiMuaSP) {
        this.soNguoiMuaSP = soNguoiMuaSP;
    }

    public float getKhaNangSinhLoi() {
        return KhaNangSinhLoi;
    }

    public void setKhaNangSinhLoi(float khaNangSinhLoi) {
        KhaNangSinhLoi = khaNangSinhLoi;
    }
}

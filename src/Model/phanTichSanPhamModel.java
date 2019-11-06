package Model;

public class phanTichSanPhamModel {
    private int soNguoiTiepCanSP;  // số người tiếp cận sản phẩm
    private int soNguoiMuaSP ;     // sốp người mua sp của dự án ( 1 )
    private float danhGia;         // % đánh giá của khách hàng danhGia = tổng số sao ở ( phanTichSanPhamModel) / 6

    public phanTichSanPhamModel() {
    }

    public phanTichSanPhamModel(int soNguoiTiepCanSP, int soNguoiMuaSP, float danhGia) {
        this.soNguoiTiepCanSP = soNguoiTiepCanSP;
        this.soNguoiMuaSP = soNguoiMuaSP;
        this.danhGia = danhGia;
    }

    public int getSoNguoiTiepCanSP() {
        return soNguoiTiepCanSP;
    }

    public void setSoNguoiTiepCanSP(int soNguoiTiepCanSP) {
        this.soNguoiTiepCanSP = soNguoiTiepCanSP;
    }

    public int getSoNguoiMuaSP() {
        return soNguoiMuaSP;
    }

    public void setSoNguoiMuaSP(int soNguoiMuaSP) {
        this.soNguoiMuaSP = soNguoiMuaSP;
    }

    public float getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(float danhGia) {
        this.danhGia = danhGia;
    }
}

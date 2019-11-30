package model;

public class TongHopDuAn {
    private Integer Nam;
    private Double doanhThu;
    private Double dienTich;
    private Integer soKhachHang;

    public Integer getSoKhachHang() {
        return soKhachHang;
    }

    public void setSoKhachHang(Integer soKhachHang) {
        this.soKhachHang = soKhachHang;
    }

    public TongHopDuAn(Integer nam, Double doanhThu, Double dienTich, Integer soKhachHang) {
        Nam = nam;
        this.doanhThu = doanhThu;
        this.dienTich = dienTich;
        this.soKhachHang = soKhachHang;
    }

    public Integer getNam() {
        return Nam;
    }

    public void setNam(Integer nam) {
        Nam = nam;
    }

    public Double getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(Double doanhThu) {
        this.doanhThu = doanhThu;
    }

    public Double getDienTich() {
        return dienTich;
    }

    public void setDienTich(Double dienTich) {
        this.dienTich = dienTich;
    }

    public TongHopDuAn() {
    }
}

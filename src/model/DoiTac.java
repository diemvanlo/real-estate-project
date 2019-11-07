package model;

import java.util.Date;

public class DoiTac {
    private String logo;        // logo đối tác
    private String idDoiTac;
    private String tenDoiTac;
    private String linhVuc;     // lĩnh vực hoạt động ( nhà ở, đất, …)
    private Date ngayThamGia;   // ngày tham gia
    private String duAnDauTu;   // dự án đã đầu tư
    private  String danhGia;    // đánh giá của đối tác về công ty

    public DoiTac() {
    }

    public DoiTac(String logo, String idDoiTac, String tenDoiTac, String linhVuc, Date ngayThamGia, String duAnDauTu, String danhGia) {
        this.logo = logo;
        this.idDoiTac = idDoiTac;
        this.tenDoiTac = tenDoiTac;
        this.linhVuc = linhVuc;
        this.ngayThamGia = ngayThamGia;
        this.duAnDauTu = duAnDauTu;
        this.danhGia = danhGia;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getIdDoiTac() {
        return idDoiTac;
    }

    public void setIdDoiTac(String idDoiTac) {
        this.idDoiTac = idDoiTac;
    }

    public String getTenDoiTac() {
        return tenDoiTac;
    }

    public void setTenDoiTac(String tenDoiTac) {
        this.tenDoiTac = tenDoiTac;
    }

    public String getLinhVuc() {
        return linhVuc;
    }

    public void setLinhVuc(String linhVuc) {
        this.linhVuc = linhVuc;
    }

    public Date getNgayThamGia() {
        return ngayThamGia;
    }

    public void setNgayThamGia(Date ngayThamGia) {
        this.ngayThamGia = ngayThamGia;
    }

    public String getDuAnDauTu() {
        return duAnDauTu;
    }

    public void setDuAnDauTu(String duAnDauTu) {
        this.duAnDauTu = duAnDauTu;
    }

    public String getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(String danhGia) {
        this.danhGia = danhGia;
    }
}

package model;

import java.io.InputStream;

public class ImageUpload {
    private int idImager;
    private InputStream image;
    private String regimeImager;
    private int idSanPham;

    public ImageUpload() {
    }

    public ImageUpload(int idImager, String regimeImager, int idSanPham) {
        this.idImager = idImager;
        this.regimeImager = regimeImager;
        this.idSanPham = idSanPham;
    }

    public ImageUpload(int idImager, InputStream image, String regimeImager, int idSanPham) {
        this.idImager = idImager;
        this.image = image;
        this.regimeImager = regimeImager;
        this.idSanPham = idSanPham;
    }

    public int getIdImager() {
        return idImager;
    }

    public void setIdImager(int idImager) {
        this.idImager = idImager;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {
        this.image = image;
    }

    public String getRegimeImager() {
        return regimeImager;
    }

    public void setRegimeImager(String regimeImager) {
        this.regimeImager = regimeImager;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }
}

package model;

import java.io.InputStream;

public class Image {
    private String idImager;
    private InputStream image;
    private String regimeImager;
    private int idProduct;

    public String getIdImager() {
        return idImager;
    }

    public void setIdImager(String idImager) {
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

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public Image() {
    }

    public Image(String idImager, InputStream image, String regimeImager, int idProduct) {
        this.idImager = idImager;
        this.image = image;
        this.regimeImager = regimeImager;
        this.idProduct = idProduct;
    }
}

package model;

public class Imager {
    private String idImager;
    private String imager;
    private String regimeImager;
    private int idProduct;


    public String getIdImager() {
        return idImager;
    }

    public void setIdImager(String idImager) {
        this.idImager = idImager;
    }

    public String getImager() {
        return imager;
    }

    public void setImager(String imager) {
        this.imager = imager;
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

    public Imager() {
    }

    public Imager(String idImager, String imager, String regimeImager, int idProduct) {
        this.idImager = idImager;
        this.imager = imager;
        this.regimeImager = regimeImager;
        this.idProduct = idProduct;
    }
}

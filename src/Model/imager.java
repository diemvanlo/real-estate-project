package Model;

public class imager {
    private String idImager;
    private String imager1;
    private String imager2;
    private String imager3;
    private String imager4;
    private String imager5;
    private String regimeImager;
    private int idProduct;

    public imager() {
    }

    public imager(String idImager, String imager1, String imager2, String imager3, String imager4, String imager5, String regimeImager, int idProduct) {
        this.idImager = idImager;
        this.imager1 = imager1;
        this.imager2 = imager2;
        this.imager3 = imager3;
        this.imager4 = imager4;
        this.imager5 = imager5;
        this.regimeImager = regimeImager; // chế độ 360 hoặc chế độ thường
        this.idProduct = idProduct;
    }

    public String getIdImager() {
        return idImager;
    }

    public void setIdImager(String idImager) {
        this.idImager = idImager;
    }

    public String getImager1() {
        return imager1;
    }

    public void setImager1(String imager1) {
        this.imager1 = imager1;
    }

    public String getImager2() {
        return imager2;
    }

    public void setImager2(String imager2) {
        this.imager2 = imager2;
    }

    public String getImager3() {
        return imager3;
    }

    public void setImager3(String imager3) {
        this.imager3 = imager3;
    }

    public String getImager4() {
        return imager4;
    }

    public void setImager4(String imager4) {
        this.imager4 = imager4;
    }

    public String getImager5() {
        return imager5;
    }

    public void setImager5(String imager5) {
        this.imager5 = imager5;
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
}

package service;


import model.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductService {
    private static Connection com;

    static {
        try {
            com = DBConnector.Connection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Notification notification = new Notification();

    public static Product getProductFromResultSet(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setIdSanPham(rs.getInt("IdSanPham"));
        product.setTenSanPham(rs.getString("TenSanPham"));
        product.setIdDuAn(rs.getInt("IdDuAn"));
        product.setDiaChi(rs.getString("DiaChi"));
        product.setDienTich(rs.getDouble("DienTich"));
        product.setGiaTien(rs.getDouble("GiaTien"));
        product.setMoTa(rs.getString("MoTa"));
        product.setNgayTao(rs.getDate("NgayTao"));
        product.setNgayBan(rs.getDate("NgayBan"));
        product.setChiTiet(rs.getString("ChiTiet"));
        product.setTrangThai(rs.getString("trangThai"));
        product.setIdKhachHang(rs.getInt("IdKhachHang"));
        return product;
    }

    public static Product findByMaProduct(int IdProduct) {
        Product product = new Product();
        try {
            ResultSet rs = com.createStatement().executeQuery("select * from SanPham");
            boolean isValid = false;
            while (rs.next()) {
                if (rs.getInt("IdSanPham") == IdProduct) {
                    isValid = true;
                    product = getProductFromResultSet(rs);
                }
            }
            if (!isValid) {
                return product;
            }
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public static List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        try {
            ResultSet rs = com.createStatement().executeQuery("select * from SanPham");
            boolean isValid = false;
            while (rs.next()) {
                Product product;
                product = getProductFromResultSet(rs);
                list.add(product);
                isValid = true;
            }
            if (!isValid) {
                return list;
            }
            rs.getStatement().close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static void deleteByMaProduct(String IdProduct) throws SQLException {
        com.createStatement().executeUpdate("DELETE FROM SanPham WHERE (IdSanPham = '" + IdProduct + "')");
    }
    public static void deleteByMaProject(String IDDuAn) throws SQLException {
        com.createStatement().executeUpdate("DELETE FROM SanPham WHERE (IdDuAn = '" + IDDuAn + "')");
    }

    public static void save(Product product, File file) throws SQLException, FileNotFoundException {
        Product productExist = findByMaProduct(product.getIdSanPham());
        if (productExist.getIdSanPham() != 0) {
            PreparedStatement pst = com.prepareStatement("UPDATE SanPham SET TenSanPham = '" + product.getTenSanPham() +
                    "', 'IdSanPham' = '" + product.getIdSanPham() +
                    "', 'TenSanPham' = '" + product.getTenSanPham() +
                    "', 'IdDuAn' = '" + product.getIdDuAn() +
                    "', 'DiaChi' = '" + product.getDiaChi() +
                    "', 'DienTich' = '" + product.getDienTich() +
                    "', 'GiaTien' = '" + product.getGiaTien() +
                    "', 'MoTa' = '" + product.getMoTa() +
                    "', 'NgayTao' = '" + product.getNgayTao() +
                    "', 'NgayBan' = '" + product.getNgayBan() +
                    "', 'ChiTiet' = '" + product.getChiTiet() +
                    "', 'trangThai' = '" + product.getTrangThai() +
                    "', 'IdKhachHang' = '" + product.getIdKhachHang() +
                    "')");
            pst.execute();
        } else {
            PreparedStatement pst = com.prepareStatement(
                    "INSERT INTO SanPham ('IdSanPham', 'TenSanPham', 'IdDuAn', 'DiaChi','DienTich', 'GiaTien', " +
                            "'MoTa', 'NgayTao', 'NgayBan', 'ChiTiet', 'trangThai', 'IdKhachHang') VALUES ('" +
                            product.getIdSanPham() + "', '" +
                            product.getTenSanPham() + "',' " +
                            product.getIdDuAn() + "',' " +
                            product.getDiaChi() + "','" +
                            product.getDienTich() + "','" +
                            product.getGiaTien() + "','" +
                            product.getMoTa() + "','" +
                            product.getNgayTao() + "','" +
                            product.getNgayBan() + "','" +
                            product.getChiTiet() + "','" +
                            product.getTrangThai() + "','" +
                            product.getIdKhachHang() + "','" +
                            "')");
            pst.execute();
        }
    }
}

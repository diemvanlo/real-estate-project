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
        product.setNgayTao(rs.getString("NgayTao"));
        product.setNgayBan(rs.getString("NgayBan"));
        product.setTienDo(rs.getString("ChiTiet"));
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
    public static List<String> getAllID() {
        List<String> list = new ArrayList<>();
        try {
            ResultSet rs = com.createStatement().executeQuery("select * from SanPham");
            boolean isValid = false;
            while (rs.next()) {
                String id = rs.getString("IdSanPham");
                list.add(id);
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

    public static void save(Product product) throws SQLException, FileNotFoundException {
        Product productExist = findByMaProduct(product.getIdSanPham());
        if (productExist.getIdSanPham() != 0) {
            String sql ="UPDATE SanPham SET TenSanPham = '" + product.getTenSanPham() +
                    "', IdDuAn = '" + product.getIdDuAn() +
                    "', DiaChi = '" + product.getDiaChi() +
                    "', DienTich = '" + product.getDienTich() +
                    "', GiaTien = '" + product.getGiaTien() +
                    "', MoTa = '" + product.getMoTa() +
                    "', NgayTao = '" + product.getNgayTao() +
                    "', NgayBan = '" + product.getNgayBan() +
                    "', ChiTiet = '" + product.getTienDo() +
                    "', trangThai = '" + product.getTrangThai() +
                    "' where IdSanPham = " + product.getIdSanPham();
            if (product.getIdKhachHang() != 0){
                sql ="UPDATE SanPham SET TenSanPham = '" + product.getTenSanPham() +
                        "', IdDuAn = '" + product.getIdDuAn() +
                        "', DiaChi = '" + product.getDiaChi() +
                        "', DienTich = '" + product.getDienTich() +
                        "', GiaTien = '" + product.getGiaTien() +
                        "', MoTa = '" + product.getMoTa() +
                        "', NgayTao = '" + product.getNgayTao() +
                        "', NgayBan = '" + product.getNgayBan() +
                        "', ChiTiet = '" + product.getTienDo() +
                        "', trangThai = '" + product.getTrangThai() +
                        "', IdKhachHang = '" + product.getIdKhachHang() +
                        "' where IdSanPham = " + product.getIdSanPham();
            }
            PreparedStatement pst = com.prepareStatement(sql);
            pst.execute();
        } else {
            String sql = "INSERT INTO SanPham ( TenSanPham, IdDuAn, DiaChi,DienTich, GiaTien, " +
                    "MoTa, NgayTao, NgayBan, ChiTiet, trangThai) VALUES ('" +
                    product.getTenSanPham() + "',' " +
                    product.getIdDuAn() + "',' " +
                    product.getDiaChi() + "','" +
                    product.getDienTich() + "','" +
                    product.getGiaTien() + "','" +
                    product.getMoTa() + "','" +
                    product.getNgayTao() + "','" +
                    product.getNgayBan() + "','" +
                    product.getTienDo() + "','" +
                    product.getTrangThai()  + "')";
            if (product.getIdKhachHang() != 0) {
                sql = "INSERT INTO SanPham ( TenSanPham, IdDuAn, DiaChi,DienTich, GiaTien, " +
                        "MoTa, NgayTao, NgayBan, ChiTiet, trangThai, IdKhachHang) VALUES ('" +
                        product.getTenSanPham() + "',' " +
                        product.getIdDuAn() + "',' " +
                        product.getDiaChi() + "','" +
                        product.getDienTich() + "','" +
                        product.getGiaTien() + "','" +
                        product.getMoTa() + "','" +
                        product.getNgayTao() + "','" +
                        product.getNgayBan() + "','" +
                        product.getTienDo() + "','" +
                        product.getTrangThai() + "'" + "," +
                        product.getIdKhachHang() + ")";
            }
            PreparedStatement pst = com.prepareStatement(sql);
            pst.execute();
        }
    }
}

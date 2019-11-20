package service;

import model.DanhGiaKhachHang;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DanhGiaKhachHangService {
    private static Connection com;

    static {
        try {
            com = DBConnector.Connection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Notification notification = new Notification();

    public DanhGiaKhachHangService() throws SQLException {
    }

    public static DanhGiaKhachHang getDanhGiaKhachHangFromResultSet(ResultSet rs) throws SQLException {
        DanhGiaKhachHang danhGiaKhachHang = new DanhGiaKhachHang();
        danhGiaKhachHang.setIdKhachHang(rs.getInt("IdKhachHang"));
        danhGiaKhachHang.setNhanXet(rs.getString("NhanXet"));
        danhGiaKhachHang.setSoSaoBinhChon(rs.getInt("SoSao"));
        danhGiaKhachHang.setIdSanPham(rs.getInt("IdSanPham"));
        return danhGiaKhachHang;
    }

    public static DanhGiaKhachHang findByMaDanhGiaKhachHang(int maDanhGiaKhachHang) {
        DanhGiaKhachHang danhGiaKhachHang = new DanhGiaKhachHang();
        try {
            ResultSet rs = com.createStatement().executeQuery("select * from DanhGia");
            boolean isValid = false;
            while (rs.next()) {
                if (rs.getInt("IdKhachHang") == maDanhGiaKhachHang) {
                    isValid = true;
                    danhGiaKhachHang = getDanhGiaKhachHangFromResultSet(rs);
                }
            }
            if (!isValid) {
                return danhGiaKhachHang;
            }
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhGiaKhachHang;
    }

    public static DanhGiaKhachHang findByMaKhachHangAndMaSanPham(int maKhachHang, int maSanPham) {
        DanhGiaKhachHang danhGiaKhachHang = new DanhGiaKhachHang();
        try {
            ResultSet rs = com.createStatement().executeQuery("select * from DanhGia where IdKhachHang = " + maKhachHang +
                    "and IdSanPham = " + maSanPham);
            boolean isValid = false;
            while (rs.next()) {
                danhGiaKhachHang = getDanhGiaKhachHangFromResultSet(rs);
            }
            if (!isValid) {
                return danhGiaKhachHang;
            }
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhGiaKhachHang;
    }

    public static List<DanhGiaKhachHang> getAll() {
        List<DanhGiaKhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = com.createStatement().executeQuery("select * from DanhGia");
            boolean isValid = false;
            while (rs.next()) {
                DanhGiaKhachHang danhGiaKhachHang;
                danhGiaKhachHang = getDanhGiaKhachHangFromResultSet(rs);
                list.add(danhGiaKhachHang);
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

    public static List<String> getAllID() {
        List<String> list = new ArrayList<>();
        try {
            ResultSet rs = com.createStatement().executeQuery("select * from DanhGia");
            boolean isValid = false;
            while (rs.next()) {
                String id = rs.getString("IdKhachHang");
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

    public static void deleteByMaDanhGiaKhachHang(String maDanhGiaKhachHang) throws SQLException {
        System.out.println("DELETE FROM DanhGia WHERE IdKhachHang = " + maDanhGiaKhachHang);
        com.createStatement().executeUpdate("DELETE FROM DanhGia WHERE IdKhachHang = " + maDanhGiaKhachHang);
    }

    public static void save(DanhGiaKhachHang danhGiaKhachHang) throws SQLException, FileNotFoundException {
        DanhGiaKhachHang danhGiaKhachHangExsit = findByMaKhachHangAndMaSanPham(danhGiaKhachHang.getIdKhachHang(), danhGiaKhachHang.getIdSanPham());
        if (danhGiaKhachHangExsit.getIdKhachHang() != 0) {
            PreparedStatement pst = com.prepareStatement("UPDATE DanhGia SET NhanXet = N'" + danhGiaKhachHang.getNhanXet() +
                    "', SoSao = " + danhGiaKhachHang.getSoSaoBinhChon() +
                    ", IdSanPham = " + danhGiaKhachHang.getIdSanPham() +
                    " where IdKhachHang = " + danhGiaKhachHang.getIdKhachHang());
            pst.execute();
        } else {
            System.out.println("INSERT INTO DanhGia ( NhanXet, SoSao, IdKhachHang, IdSanPham) VALUES (N'" +
                    danhGiaKhachHang.getNhanXet() + "', " +
                    danhGiaKhachHang.getSoSaoBinhChon() + "," +
                    danhGiaKhachHang.getIdKhachHang() + "," +
                    danhGiaKhachHang.getIdSanPham() + ");");
            PreparedStatement pst = com.prepareStatement(
                    "INSERT INTO DanhGia ( NhanXet, SoSao, IdKhachHang, IdSanPham) VALUES (N'" +
                            danhGiaKhachHang.getNhanXet() + "', " +
                            danhGiaKhachHang.getSoSaoBinhChon() + "," +
                            danhGiaKhachHang.getIdKhachHang() + "," +
                            danhGiaKhachHang.getIdSanPham() + ");");
            pst.execute();
        }
    }
}

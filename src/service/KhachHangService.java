package service;


import model.KhachHang;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class KhachHangService {
    private static Connection com;

    static {
        try {
            com = DBConnector.Connection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Notification notification = new Notification();

    public static KhachHang getKhachHangFromResultSet(ResultSet rs) throws SQLException {
        KhachHang khachHang = new KhachHang();
        khachHang.setIdKhachHang(rs.getInt("IdKhachHang"));
        khachHang.setTenKhachHang(rs.getString("TenKhachHang"));
        khachHang.setGioiTinh(rs.getBoolean("GioiTinh"));
        khachHang.setDiaChi(rs.getString("DiaChi"));
        khachHang.setSdt(rs.getString("Sdt"));
        khachHang.setEmail(rs.getString("Email"));
        return khachHang;
    }

    public static KhachHang findByMaKhachHang(int IdKhachHang) {
        KhachHang khachHang = new KhachHang();
        try {
            ResultSet rs = com.createStatement().executeQuery("select * from khachHang");
            boolean isValid = false;
            while (rs.next()) {
                if (rs.getInt("IdKhachHang") == IdKhachHang) {
                    isValid = true;
                    khachHang = getKhachHangFromResultSet(rs);
                }
            }
            if (!isValid) {
                return khachHang;
            }
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khachHang;
    }

    public static List<KhachHang> getAll() {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = com.createStatement().executeQuery("select * from khachHang");
            boolean isValid = false;
            while (rs.next()) {
                KhachHang khachHang;
                khachHang = getKhachHangFromResultSet(rs);
                list.add(khachHang);
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
            ResultSet rs = com.createStatement().executeQuery("select * from khachHang");
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

    public static void deleteByMaKhachHang(String IdKhachHang) throws SQLException {
        com.createStatement().executeUpdate("DELETE FROM [javafx].[khachHang] WHERE (IdKhachHang = '" + IdKhachHang + "')");
    }

    public static void save(KhachHang khachHang, File file) throws SQLException, FileNotFoundException {
        KhachHang khachHangExist = findByMaKhachHang(khachHang.getIdKhachHang());
        if (khachHangExist.getIdKhachHang() != 0) {
            PreparedStatement pst = com.prepareStatement("UPDATE [javafx].[khachHang] SET tenKhachHang = '" + khachHang.getTenKhachHang() +
                    "', IdKhachHang = '" + khachHang.getIdKhachHang() +
                    "', TenKhachHang = '" + khachHang.getTenKhachHang() +
                    "', GioiTinh = '" + khachHang.getGioiTinh() +
                    "', DiaChi = '" + khachHang.getDiaChi() +
                    "', Sdt = '" + khachHang.getSdt() +
                    "', Email = '" + khachHang.getEmail() +
                    "')");
            pst.execute();
        } else {
            PreparedStatement pst = com.prepareStatement(
                    "INSERT INTO [javafx].[khachHang] (IdKhachHang, TenKhachHang, LinhVuc, DiaChi,Sdt, Email, " +
                            "Logo, SoVonDaDauTu) VALUES ('" +
                            khachHang.getIdKhachHang() + "', '" +
                            khachHang.getTenKhachHang() + "',' " +
                            khachHang.getGioiTinh() + "',' " +
                            khachHang.getDiaChi() + "','" +
                            khachHang.getSdt() + "','" +
                            khachHang.getEmail() + "','" +
                            "')");
            pst.execute();
        }
    }

    public static void main(String[] args) {
        List<String> ids = getAllID();
        System.out.println(ids.size());
    }
}

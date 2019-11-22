package service;

import model.TongHopDoanhThu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChartService {
    private static Connection com;

    static {
        try {
            com = DBConnector.Connection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<TongHopDoanhThu> getDoanhThu(String year) {
        List<TongHopDoanhThu> list = new ArrayList<>();
        try {
            ResultSet rs = com.createStatement().executeQuery("SELECT\n" +
                    "\t\tsp.IdDuAn ,\n" +
                    "\t\tCOUNT(sp.IdSanPham) SoSP,\n" +
                    "\t\tSUM(sp.GiaTien) DoanhThu,\n" +
                    "\t\tMAX(sp.GiaTien) CaoNhat,\n" +
                    "\t\tMIN(sp.GiaTien) ThapNhat,\n" +
                    "\t\tAVG(sp.GiaTien) TrungBinh\n" +
                    "\tFROM DuAn da\n" +
                    "\tInner JOIN SanPham sp ON da.IdDuAn=sp.IdDuAn\n" +
                    "\tWHERE YEAR(da.NgayBatDau) = " + year + "\n" +
                    "\tGROUP BY sp.IdDuAn");
            boolean isValid = false;
            while (rs.next()) {
                TongHopDoanhThu model = new TongHopDoanhThu();
                model.setIdDuAn(rs.getInt("IdDuAn"));
                model.setSoSp(rs.getInt("SoSP"));
                model.setTongDoanhThu(rs.getDouble("DoanhThu"));
                model.setDoanhThuCaoNhat(rs.getDouble("CaoNhat"));
                model.setDoanhThuThapNhat(rs.getDouble("ThapNhat"));
                model.setDoanhThuTB(rs.getDouble("TrungBinh"));
                list.add(model);
            }
            if (!isValid) {
                return list;
            }
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        List<TongHopDoanhThu> list = getDoanhThu("2020");
        System.out.println();
    }
}

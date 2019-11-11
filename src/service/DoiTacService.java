package service;


import model.DoiTac;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DoiTacService {
    private static Connection com;

    static {
        try {
            com = DBConnector.Connection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Notification notification = new Notification();

    public static DoiTac getDoiTacFromResultSet(ResultSet rs) throws SQLException {
        DoiTac doiTac = new DoiTac();
        doiTac.setIdDoiTac(rs.getInt("IdDoiTac"));
        doiTac.setTenDoitac(rs.getString("TenDoiTac"));
        doiTac.setLinhVuc(rs.getString("LinhVuc"));
        doiTac.setDiaChi(rs.getString("DiaChi"));
        doiTac.setSdt(rs.getString("Sdt"));
        doiTac.setEmail(rs.getString("Email"));
        doiTac.setLogo(rs.getBinaryStream("Logo"));
        doiTac.setsoVonDaDauTu(rs.getDouble("SoVonDaDauTu"));
        return doiTac;
    }

    public static DoiTac findByMaDoiTac(int IdDoiTac) {
        DoiTac doiTac = new DoiTac();
        try {
            ResultSet rs = com.createStatement().executeQuery("select * from doiTac");
            boolean isValid = false;
            while (rs.next()) {
                if (rs.getInt("IdDoiTac") == IdDoiTac) {
                    isValid = true;
                    doiTac = getDoiTacFromResultSet(rs);
                }
            }
            if (!isValid) {
                return doiTac;
            }
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doiTac;
    }

    public static List<DoiTac> getAll() {
        List<DoiTac> list = new ArrayList<>();
        try {
            ResultSet rs = com.createStatement().executeQuery("select * from doiTac");
            boolean isValid = false;
            while (rs.next()) {
                DoiTac doiTac;
                doiTac = getDoiTacFromResultSet(rs);
                list.add(doiTac);
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

    public static void deleteByMaDoiTac(String IdDoiTac) throws SQLException {
        com.createStatement().executeUpdate("DELETE FROM [javafx].[doiTac] WHERE ('IdDoiTac' = '" + IdDoiTac + "')");
    }

    public static void save(DoiTac doiTac, File file) throws SQLException, FileNotFoundException {
        DoiTac doiTacExist = findByMaDoiTac(doiTac.getIdDoiTac());
        if (doiTacExist.getIdDoiTac() != 0) {
            PreparedStatement pst = com.prepareStatement("UPDATE [javafx].[doiTac] SET 'tenDoiTac' = '" + doiTac.getTenDoitac() +
                    "', 'IdDoiTac' = '" + doiTac.getIdDoiTac() +
                    "', 'TenDoiTac' = '" + doiTac.getTenDoitac() +
                    "', 'LinhVuc' = '" + doiTac.getLinhVuc() +
                    "', 'DiaChi' = '" + doiTac.getDiaChi() +
                    "', 'Sdt' = '" + doiTac.getSdt() +
                    "', 'Email' = '" + doiTac.getEmail() +
                    "', 'Logo' = '" + doiTac.getLogo() +
                    "', 'SoVonDaDauTu' = '" + doiTac.getsoVonDaDauTu() +
                    "')");
            pst.execute();
        } else {
            PreparedStatement pst = com.prepareStatement(
                    "INSERT INTO [javafx].[doiTac] ('IdDoiTac', 'TenDoiTac', 'LinhVuc', 'DiaChi','Sdt', 'Email', " +
                            "'Logo', 'SoVonDaDauTu') VALUES ('" +
                            doiTac.getIdDoiTac() + "', '" +
                            doiTac.getTenDoitac() + "',' " +
                            doiTac.getLinhVuc() + "',' " +
                            doiTac.getDiaChi() + "','" +
                            doiTac.getSdt() + "','" +
                            doiTac.getEmail() + "','" +
                            doiTac.getLogo() + "','" +
                            doiTac.getsoVonDaDauTu() + "','" +
                            "')");
            pst.execute();
        }
    }
}

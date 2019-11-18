package service;


import model.DoiTac;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
        byte[] bytes = rs.getBytes("Logo");
        if (bytes != null) {
            InputStream targetStream = new ByteArrayInputStream(bytes);
            doiTac.setLogo(targetStream);
        }
        doiTac.setIdDoiTac(rs.getInt("IdDoiTac"));
        doiTac.setTenDoitac(rs.getString("TenDoiTac"));
        doiTac.setLinhVuc(rs.getString("LinhVuc"));
        doiTac.setDiaChi(rs.getString("DiaChi"));
        doiTac.setSdt(rs.getString("Sdt"));
        doiTac.setEmail(rs.getString("Email"));
        doiTac.setSoVonDaDauTu(rs.getDouble("SoVonDaDauTu"));
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

    public static List<String> getAllID() {
        List<String> list = new ArrayList<>();
        try {
            ResultSet rs = com.createStatement().executeQuery("select * from doiTac");
            boolean isValid = false;
            while (rs.next()) {
                String id = rs.getString("IdDoiTac");
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

    public static void deleteByMaDoiTac(String IdDoiTac) throws SQLException {
        com.createStatement().executeUpdate("DELETE FROM doiTac WHERE (IdDoiTac = '" + IdDoiTac + "')");
    }


    public static void save(DoiTac doiTac, File file) throws SQLException, IOException {
        DoiTac doiTacExist = findByMaDoiTac(doiTac.getIdDoiTac());
        if (doiTacExist.getIdDoiTac() != 0) {
            if (file == null) {
                PreparedStatement pst = com.prepareStatement("UPDATE doiTac SET tenDoiTac = ?," +
                        "LinhVuc = ?," +
                        "DiaChi = ?," +
                        "Sdt = ?," +
                        "Email = ?," +
                        "SoVonDaDauTu = ? where IDDoiTac = ?");
                pst.setString(1, doiTac.getTenDoitac());
                pst.setString(2, doiTac.getLinhVuc());
                pst.setString(3, doiTac.getDiaChi());
                pst.setString(4, doiTac.getSdt());
                pst.setString(5, doiTac.getEmail());
                pst.setDouble(6, doiTac.getSoVonDaDauTu());
                pst.setInt(7, doiTac.getIdDoiTac());
                pst.execute();
            } else {
                PreparedStatement pst = com.prepareStatement("UPDATE doiTac SET tenDoiTac = ?," +
                        "LinhVuc = ?," +
                        "DiaChi = ?," +
                        "Sdt = ?," +
                        "Email = ?," +
                        "SoVonDaDauTu = ?, " +
                        "logo = ?" +
                        " where IDDoiTac = ?");
                pst.setString(1, doiTac.getTenDoitac());
                pst.setString(2, doiTac.getLinhVuc());
                pst.setString(3, doiTac.getDiaChi());
                pst.setString(4, doiTac.getSdt());
                pst.setString(5, doiTac.getEmail());
                pst.setDouble(6, doiTac.getSoVonDaDauTu());
                InputStream inputStream = new FileInputStream(file);
                pst.setBinaryStream(7, inputStream, (int) file.length());
                pst.setInt(8, doiTac.getIdDoiTac());
                pst.execute();
            }
        } else {
            if (file == null) {
                DecimalFormat df = new DecimalFormat("###");
                PreparedStatement pst = com.prepareStatement(
                        "INSERT INTO doiTac ( TenDoiTac, LinhVuc, DiaChi, Sdt, Email, " +
                                " SoVonDaDauTu) VALUES (?,?,?,?,?,?)");
                pst.setString(1, doiTac.getTenDoitac());
                pst.setString(2, doiTac.getLinhVuc());
                pst.setString(3, doiTac.getDiaChi());
                pst.setString(4, doiTac.getSdt());
                pst.setString(5, doiTac.getEmail());
                pst.setDouble(6, doiTac.getSoVonDaDauTu());
                pst.execute();
            } else {
                InputStream inputStream = new FileInputStream(file);
                PreparedStatement pst = com.prepareStatement(
                        "INSERT INTO doiTac ( TenDoiTac, LinhVuc, DiaChi, Sdt, Email, " +
                                " SoVonDaDauTu, logo) VALUES (?,?,?,?,?,?,?)");
                pst.setString(1, doiTac.getTenDoitac());
                pst.setString(2, doiTac.getLinhVuc());
                pst.setString(3, doiTac.getDiaChi());
                pst.setString(4, doiTac.getSdt());
                pst.setString(5, doiTac.getEmail());
                pst.setDouble(6, doiTac.getSoVonDaDauTu());
                pst.setBinaryStream(7, inputStream, (int) file.length());
                pst.execute();
                inputStream.close();
            }
        }
    }

    public static void main(String[] args) {
        List<String> ids = getAllID();
        System.out.println(ids.size());
    }
}

package service;


import model.Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProjectService {
    private static Connection com;

    static {
        try {
            com = DBConnector.Connection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Notification notification = new Notification();

    public ProjectService() throws SQLException {
    }

    public static Project getProjectFromResultSet(ResultSet rs) throws SQLException {
        Project project = new Project();
        project.setIdDuAn(rs.getInt("maProject"));
        project.setTenDuAn(rs.getString("tenDuAn"));
        project.setLoaiHinh(rs.getString("loaiHinh"));
        project.setDiaChi(rs.getString("diaChi"));
        project.setDienTich(rs.getDouble("dienTich"));
        project.setChiPhiDuAn(rs.getDouble("chiPhiDuAn"));
        project.setMucTieu(rs.getString("mucTieu"));
        project.setNgayBatDau(rs.getDate("ngayBatDau"));
        project.setNgayKetThuc(rs.getDate("ngayKetThuc"));
        project.setHinhThucQuanLi(rs.getString("hinhThucQuanLi"));
        project.setHinhThucDauTu(rs.getString("hinhThucDauTu"));
        project.setIdDoiTac(rs.getInt("idNguoiDung"));
        project.setMapX(rs.getString("mapX"));
        project.setMapY(rs.getString("mapY"));
        project.setBanKinh(rs.getDouble("banKinh"));
        return project;
    }

    public static Project findByMaProject(int maProject) {
        Project project = new Project();
        try {
            ResultSet rs = com.createStatement().executeQuery("select * from project");
            boolean isValid = false;
            while (rs.next()) {
                if (rs.getInt("MaProject") == maProject) {
                    isValid = true;
                    project = getProjectFromResultSet(rs);
                }
            }
            if (!isValid) {
                return project;
            }
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    public static List<Project> getAll() {
        List<Project> list = new ArrayList<>();
        try {
            ResultSet rs = com.createStatement().executeQuery("select * from project");
            boolean isValid = false;
            while (rs.next()) {
                Project project;
                project = getProjectFromResultSet(rs);
                list.add(project);
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

    public static void deleteByMaProject(String maProject) throws SQLException {
        com.createStatement().executeUpdate("DELETE FROM [javafx].[project] WHERE ('maProject' = '" + maProject + "')");
    }

    public static void save(Project project, File file) throws SQLException, FileNotFoundException {
        Project projectExist = findByMaProject(project.getIdDuAn());
        if (projectExist.getIdDuAn() != 0) {
            PreparedStatement pst = com.prepareStatement("UPDATE [javafx].[project] SET 'tenProject' = '" + project.getTenDuAn() +
                    "', 'loaiHinh' = '" + project.getLoaiHinh() +
                    "', 'diaChi' = '" + project.getDiaChi() +
                    "', 'dienTich' = '" + project.getDienTich() +
                    "', 'chiPhiDuAn' = '" + project.getChiPhiDuAn() +
                    "', 'mucTieu' = '" + project.getMucTieu() +
                    "', 'ngayBatDau' = '" + project.getNgayBatDau() +
                    "', 'ngayKetThuc' = '" + project.getNgayKetThuc() +
                    "', 'hinhThucQuanLi' = '" + project.getHinhThucQuanLi() +
                    "', 'hinhThucDauTu' = '" + project.getHinhThucDauTu() +
                    "', 'idNguoiDung' = '" + project.getIdDoiTac() +
                    "', 'trangThai' = '" + project.getTrangThai() +
                    "', 'mapX' = '" + project.getMapX() +
                    "', 'mapY' = '" + project.getMapY() +
                    "', 'banKinh' = '" + project.getBanKinh() +
                    "')");
            pst.execute();
        } else {
            PreparedStatement pst = com.prepareStatement(
                    "INSERT INTO [javafx].[project] ('idDuAn', 'tenProject', 'loaiHinh', 'diaChi','dienTich', 'chiPhiDuAn', " +
                            "'mucTieu', 'ngayBatDau', 'ngayKetThuc', 'hinhThucQuanLi', 'hinhThucDauTu', 'idNguoiDung', " +
                            "'trangThai', 'mapX', 'mapY', 'banKinh') VALUES ('" +
                            project.getIdDuAn() + "', '" +
                            project.getTenDuAn() + "',' " +
                            project.getLoaiHinh() + "',' " +
                            project.getDiaChi() + "','" +
                            project.getDienTich() + "','" +
                            project.getChiPhiDuAn() + "','" +
                            project.getMucTieu() + "','" +
                            project.getNgayBatDau() + "','" +
                            project.getNgayKetThuc() + "','" +
                            project.getHinhThucQuanLi() + "','" +
                            project.getHinhThucDauTu() + "','" +
                            project.getIdDoiTac() + "','" +
                            project.getTrangThai() + "','" +
                            project.getMapX() + "','" +
                            project.getMapY() + "','" +
                            project.getBanKinh() + "');");
            pst.execute();
        }
    }
}

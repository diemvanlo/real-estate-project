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
        project.setIdDuAn(rs.getInt("IdDuAn"));
        project.setTenDuAn(rs.getString("TenDuAn"));
        project.setLoaiHinh(rs.getString("LoaiHinh"));
        project.setDiaChi(rs.getString("DiaChi"));
        project.setDienTich(rs.getDouble("DienTich"));
        project.setChiPhiDuAn(rs.getDouble("ChiPhi"));
        project.setMucTieu(rs.getString("MucTieu"));
        project.setNgayBatDau(rs.getDate("NgayBatDau"));
        project.setNgayKetThuc(rs.getDate("NgayKetThuc"));
        project.setHinhThucQuanLi(rs.getString("HinhThucQuanLi"));
        project.setHinhThucDauTu(rs.getString("HinhThucDauTu"));
        project.setIdDoiTac(rs.getInt("IdDoiTac"));
        project.setTrangThai(rs.getString("TrangThai"));
        project.setMapX(rs.getString("mapX"));
        project.setMapY(rs.getString("mapY"));
        project.setBanKinh(rs.getDouble("banKinh"));
        return project;
    }

    public static Project findByMaProject(int maProject) {
        Project project = new Project();
        try {
            ResultSet rs = com.createStatement().executeQuery("select * from duan");
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
            ResultSet rs = com.createStatement().executeQuery("select * from duan");
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
        com.createStatement().executeUpdate("DELETE FROM [javafx].[duan] WHERE ('maProject' = '" + maProject + "')");
    }

    public static void save(Project project, File file) throws SQLException, FileNotFoundException {
        Project projectExist = findByMaProject(project.getIdDuAn());
        if (projectExist.getIdDuAn() != 0) {
            PreparedStatement pst = com.prepareStatement("UPDATE [javafx].[duan] SET 'tenProject' = '" + project.getTenDuAn() +
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
                    "INSERT INTO [javafx].[duan] ('idDuAn', 'tenProject', 'loaiHinh', 'diaChi','dienTich', 'chiPhiDuAn', " +
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

    public static void main(String[] args) {

        List<Project> projects = getAll();
        System.out.println("fff");
    }
}

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
import java.util.stream.Collectors;


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
        project.setNgayBatDau(rs.getString("NgayBatDau"));
        project.setNgayKetThuc(rs.getString("NgayKetThuc"));
        project.setHinhThucQuanLi(rs.getString("HinhThucQuanLi"));
        project.setHinhThucDauTu(rs.getString("HinhThucDauTu"));
        project.setIdDoiTac(rs.getInt("IdDoiTac"));
        project.setTrangThai(rs.getString("TrangThai"));
        project.setMapX(rs.getDouble("mapX"));
        project.setMapY(rs.getDouble("mapY"));
        project.setBanKinh(rs.getDouble("banKinh"));
        return project;
    }

    public static Project findByMaProject(int maProject) {
        Project project = new Project();
        try {
            ResultSet rs = com.createStatement().executeQuery("select * from duan");
            boolean isValid = false;
            while (rs.next()) {
                if (rs.getInt("IdDuAn") == maProject) {
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
            ResultSet rs = com.createStatement().executeQuery("select * from DuAn");
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

    public static List<String> getAllID() {
        List<String> list = new ArrayList<>();
        try {
            ResultSet rs = com.createStatement().executeQuery("select * from duAN");
            boolean isValid = false;
            while (rs.next()) {
                String id = rs.getString("IdDuAn");
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

    public static List<String> getAllYear() {
        List<String> list = new ArrayList<>();
        try {
            ResultSet rs = com.createStatement().executeQuery("select YEAR(NgayBatDau) year from duAN group by YEAR(NgayBatDau)");
            boolean isValid = false;
            while (rs.next()) {
                String year = rs.getString("year");
                list.add(year);
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
        ProductService.deleteByMaProject(maProject);
        com.createStatement().executeUpdate("DELETE FROM DuAn WHERE (idDuAn = '" + maProject + "')");
    }

    public static void save(Project project) throws SQLException, FileNotFoundException {
        if (project.getIdDuAn() != null) {
            PreparedStatement pst = com.prepareStatement("UPDATE DuAn SET tenDuAn = N'" + project.getTenDuAn() +
                    "', loaiHinh = N'" + project.getLoaiHinh() +
                    "', diaChi = N'" + project.getDiaChi() +
                    "', dienTich = '" + project.getDienTich() +
                    "', ChiPhi = '" + project.getChiPhiDuAn() +
                    "', ngayBatDau = '" + project.getNgayBatDau() +
                    "', ngayKetThuc = '" + project.getNgayKetThuc() +
                    "', hinhThucQuanLi = N'" + project.getHinhThucQuanLi() +
                    "', hinhThucDauTu = N'" + project.getHinhThucDauTu() +
                    "', IdDoiTac = '" + project.getIdDoiTac() +
                    "', trangThai = N'" + project.getTrangThai() +
                    "', mapX = '" + project.getMapX() +
                    "', mapY = '" + project.getMapY() +
                    "', banKinh = '" + project.getBanKinh() +
                    "' " +
                    "where IdDuAn = " + project.getIdDuAn());
            pst.execute();
        } else {
            PreparedStatement pst = com.prepareStatement(
                    "INSERT INTO DuAn (TenDuAn, loaiHinh, diaChi,dienTich, ChiPhi, " +
                            " ngayBatDau, ngayKetThuc, hinhThucQuanLi, hinhThucDauTu, IdDoiTac, " +
                            "trangThai, mapX, mapY, banKinh) VALUES (N'" +
                            project.getTenDuAn() + "',N' " +
                            project.getLoaiHinh() + "',N' " +
                            project.getDiaChi() + "','" +
                            project.getDienTich() + "','" +
                            project.getChiPhiDuAn() + "','" +
                            project.getNgayBatDau() + "','" +
                            project.getNgayKetThuc() + "',N'" +
                            project.getHinhThucQuanLi() + "',N'" +
                            project.getHinhThucDauTu() + "','" +
                            project.getIdDoiTac() + "',N'" +
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

package service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThongKeService {
    private static Connection com;

    static {
        try {
            com = DBConnector.Connection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Notification notification = new Notification();

    public ThongKeService() throws SQLException {
    }
    public List<Object[]> getduan() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = com.createStatement().executeQuery("{call sp_ThongKeDA}");
            boolean isValid = false;
            while (rs.next()) {
                Object[] model = {
                        rs.getInt("SoSP"),
                        rs.getInt("SoDT"),
                        rs.getInt("SoKH"),
                        rs.getFloat("DoanhThu")
                };
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
    public List<Object[]> getdoitac() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = com.createStatement().executeQuery("{call sp_ThongKeDoiTac}");
            boolean isValid = false;
            while (rs.next()) {
                Object[] model = {
                        rs.getInt("SoDuAnDauTu"),
                        rs.getInt("SoVonDaDauTu"),
                };
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
    public List<Object[]> getdoanhthu() {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = com.createStatement().executeQuery("{call sp_ThongKeDoanhThu}");
            boolean isValid = false;
            while (rs.next()) {
                Object[] model = {
                        rs.getInt("Nam"),
                        rs.getInt("SoDuAn"),
                        rs.getInt("SoSP"),
                        rs.getFloat("DoanhThu"),
                        rs.getFloat("CaoNhat"),
                        rs.getFloat("ThapNhat"),
                        rs.getFloat("TrungBinh"),
                };
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
}

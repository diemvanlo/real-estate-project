package service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.TongHopDoanhThu;
import model.TongHopDoiTac;
import model.TongHopDuAn;
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
    public static TongHopDuAn getTongHopDuAnFromResultSet(ResultSet rs) throws SQLException{
        TongHopDuAn tongHopDuAn = new TongHopDuAn();
        tongHopDuAn.setDoanhThu(rs.getDouble("DoanhThu"));
        tongHopDuAn.setNam(rs.getInt("NgayTao"));
        return tongHopDuAn;
    }
    public static TongHopDoiTac getTongHopDoiTacFromResultSet(ResultSet rs) throws SQLException{
        TongHopDoiTac tongHopDoiTac = new TongHopDoiTac();
        tongHopDoiTac.setSoDuAnDaDauTu(rs.getInt("SoDuAnDauTu"));
        tongHopDoiTac.setSoVonDaDauTu(rs.getDouble("SoVonDaDauTu"));
        tongHopDoiTac.setTenDoitac(rs.getString("dt"));
        return tongHopDoiTac;
    }
    public static TongHopDoanhThu getTongHopDoanhThuFromResultSet(ResultSet rs) throws SQLException{
        TongHopDoanhThu tongHopDoanhThu = new TongHopDoanhThu();
        tongHopDoanhThu.setDoanhThuCaoNhat(rs.getDouble("CaoNhat"));
        tongHopDoanhThu.setDoanhThuTB(rs.getDouble("TrungBinh"));
        tongHopDoanhThu.setDoanhThuThapNhat(rs.getDouble("ThapNhat"));
//        tongHopDoanhThu.setNam(rs.getInt("Nam"));
        tongHopDoanhThu.setSoSp(rs.getInt("SoDuAn"));
        tongHopDoanhThu.setSoSp(rs.getInt("SoSP"));
        tongHopDoanhThu.setTongDoanhThu(rs.getDouble("DoanhThu"));
        return tongHopDoanhThu;
    }
    public List<TongHopDuAn> getDuAn() {
        List<TongHopDuAn> list = new ArrayList<>();
        try {
            ResultSet rs = com.createStatement().executeQuery("{call sp_ThongKeDA}");
            boolean isValid = false;
            while (rs.next()) {
                TongHopDuAn tongHopDuAn;
                tongHopDuAn = getTongHopDuAnFromResultSet(rs);
                list.add(tongHopDuAn);
                isValid=true;
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
    public List<TongHopDoiTac> getDoiTac() {
        List<TongHopDoiTac> list = new ArrayList<>();
        try {
            ResultSet rs = com.createStatement().executeQuery("{call sp_ThongKeDoiTac}");
            boolean isValid = false;
            while (rs.next()) {
               TongHopDoiTac tongHopDoiTac;
               tongHopDoiTac = getTongHopDoiTacFromResultSet(rs);
               list.add(tongHopDoiTac);
               isValid=true;
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
    public List<TongHopDoanhThu> getDoanhThu() {
        List<TongHopDoanhThu> list = new ArrayList<>();
        try {
            ResultSet rs = com.createStatement().executeQuery("{call sp_ThongKeDoanhThu}");
            boolean isValid = false;
            while (rs.next()) {
                TongHopDoanhThu tongHopDoanhThu;
                tongHopDoanhThu = getTongHopDoanhThuFromResultSet(rs);
                list.add(tongHopDoanhThu);
                isValid=true;
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

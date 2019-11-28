package service;


import model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserService {
    private static Connection com;

    static {
        try {
            com = DBConnector.Connection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Notification notification = new Notification();

    public static User getUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setIdUser(rs.getInt("IdUser"));
        user.setNameUser(rs.getString("NameUser"));
        user.setUserName(rs.getString("UserName"));
        user.setPassWord(rs.getString("Password"));
        user.setAddress(rs.getString("DiaChi"));
        user.setEmail(rs.getString("Email"));
        user.setNumberPhone(rs.getString("Sdt"));
        user.setGender(rs.getBoolean("GioiTinh"));
        user.setChucVu(rs.getString("ChucVu"));
        user.setRole(rs.getInt("Role"));
        return user;
    }

    public static User findByMaUser(int IdUser) {
        User user = new User();
        try {
            ResultSet rs = com.createStatement().executeQuery("select * from Userr");
            boolean isValid = false;
            while (rs.next()) {
                if (rs.getInt("IdUser") == IdUser) {
                    isValid = true;
                    user = getUserFromResultSet(rs);
                }
            }
            if (!isValid) {
                return user;
            }
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static User findByUserName(String UserName) {
        User user = new User();
        try {
            ResultSet rs = com.createStatement().executeQuery("select * from Userr ");
            boolean isValid = false;
            while (rs.next()) {
                if (rs.getString("UserName").equalsIgnoreCase(UserName)) {
                    isValid = true;
                    user = getUserFromResultSet(rs);
                    return user;
                }
            }
            if (!isValid) {
                return user;
            }
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static List<User> getAll() {
        List<User> list = new ArrayList<>();
        try {
            ResultSet rs = com.createStatement().executeQuery("select * from Userr");
            boolean isValid = false;
            while (rs.next()) {
                User user;
                user = getUserFromResultSet(rs);
                list.add(user);
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

    public static void deleteByMaUser(String IdUser) throws SQLException {
        System.out.println(IdUser);
        com.createStatement().executeUpdate("DELETE FROM Userr WHERE (IdUser = '" + IdUser + "')");
    }

    public static void save(User user) throws SQLException, FileNotFoundException {
        User userExist = findByMaUser(user.getIdUser());
        if (userExist.getIdUser() != 0) {
            PreparedStatement pst = com.prepareStatement("UPDATE Userr SET NameUser = '" + user.getNameUser() +
                    "',NameUser = N'" + user.getNameUser() +
                    "',UserName = '" + user.getUserName() +
                    "',Password = '" + user.getPassWord() +
                    "',Sdt = '" + user.getNumberPhone() +
                    "',Email = '" + user.getEmail() +
                    "',DiaChi = N'" + user.getAddress() +
                    "',GioiTinh = '" + user.getGender() +
                    "',ChucVu = N'" + user.getChucVu() +
                    "',Role = '" + user.getRole() +
                    "' where  idUser = " + user.getIdUser());
            pst.execute();
        } else {

            PreparedStatement pst = com.prepareStatement(
                    "INSERT INTO Userr ( NameUser, UserName,Password,Sdt, Email, " +
                            "DiaChi, GioiTinh, ChucVu, Role) VALUES (N'" +
                            user.getNameUser() + "','" +
                            user.getUserName() + "','" +
                            user.getPassWord() + "','" +
                            user.getNumberPhone() + "','" +
                            user.getEmail() + "',N'" +
                            user.getAddress() + "','" +
                            user.getGender() + "',N'" +
                            user.getChucVu() + "','" +
                            user.getRole() + "')");
            pst.execute();
        }
    }
}

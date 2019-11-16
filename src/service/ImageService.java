package service;


import model.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ImageService {
    private static Connection com;

    static {
        try {
            com = DBConnector.Connection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Notification notification = new Notification();

    public static Image getImageFromResultSet(ResultSet rs) throws SQLException {
        Image image = new Image();
        image.setIdImager(rs.getInt("IdHinhAnh"));
        image.setImage(rs.getBinaryStream("HinhAnh"));
        image.setRegimeImager(rs.getString("CheDo"));
        image.setIdSanPham(rs.getInt("IdSanPham"));
        return image;
    }

    public static Image findByMaImage(int IdImage) {
        Image image = new Image();
        try {
            ResultSet rs = com.createStatement().executeQuery("select * from image");
            boolean isValid = false;
            while (rs.next()) {
                if (rs.getInt("IdHinhAnh") == IdImage) {
                    isValid = true;
                    image = getImageFromResultSet(rs);
                }
            }
            if (!isValid) {
                return image;
            }
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static List<Image> getAll() {
        List<Image> list = new ArrayList<>();
        try {
            ResultSet rs = com.createStatement().executeQuery("select * from image");
            boolean isValid = false;
            while (rs.next()) {
                Image image;
                image = getImageFromResultSet(rs);
                list.add(image);
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

    public static void deleteByMaImage(String IdImage) throws SQLException {
        com.createStatement().executeUpdate("DELETE FROM [javafx].[image] WHERE ('IdSanPham' = '" + IdImage + "')");
    }

    public static void save(Image image, File file) throws SQLException, FileNotFoundException {
        Image imageExist = findByMaImage(image.getIdImager());
        if (imageExist.getIdImager() != 0) {
            PreparedStatement pst = com.prepareStatement("UPDATE [javafx].[image] SET 'HinhAnh' = '" + image.getImage() +
                    "', 'IdHinhAnh' = '" + image.getIdImager() +
                    "', 'HinhAnh' = '" + image.getImage() +
                    "', 'CheDo' = '" + image.getRegimeImager() +
                    "', 'IdSanPham' = '" + image.getIdSanPham() +
                    "')");
            pst.execute();
        } else {
            PreparedStatement pst = com.prepareStatement(
                    "INSERT INTO [javafx].[image] ('IdHinhAnh', 'HinhAnh', 'CheDo', 'IdSanPham') VALUES ('" +
                            image.getIdImager() + "', '" +
                            image.getImage() + "',' " +
                            image.getRegimeImager() + "',' " +
                            image.getIdSanPham() + "','" +
                            "')");
            pst.execute();
        }
    }
}

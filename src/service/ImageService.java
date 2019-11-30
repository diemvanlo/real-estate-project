package service;



import model.ImageUpload;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
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

    public static ImageUpload getImageFromResultSet(ResultSet rs) throws SQLException {
        ImageUpload imageUpload = new ImageUpload();
        byte[] bytes = rs.getBytes("HinhAnh");
        if (bytes != null) {
            InputStream targetStream = new ByteArrayInputStream(bytes);
            imageUpload.setImage(targetStream);
        }
        imageUpload.setIdImager(rs.getInt("IdHinhAnh"));
        imageUpload.setRegimeImager(rs.getString("CheDo"));
        imageUpload.setIdSanPham(rs.getInt("IdSanPham"));
        return imageUpload;
    }

    public static ImageUpload findByMaImage(int IdImage) {
        ImageUpload imageUpload = new ImageUpload();
        try {
            ResultSet rs = com.createStatement().executeQuery("select * from HinhAnh");
            boolean isValid = false;
            while (rs.next()) {
                if (rs.getInt("IdHinhAnh") == IdImage) {
                    isValid = true;
                    imageUpload = getImageFromResultSet(rs);
                }
            }
            if (!isValid) {
                return imageUpload;
            }
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imageUpload;
    }

    public static List<ImageUpload> getAll() {
        List<ImageUpload> list = new ArrayList<>();
        try {
            ResultSet rs = com.createStatement().executeQuery("select * from HinhAnh");
            boolean isValid = false;
            while (rs.next()) {
                ImageUpload imageUpload;
                imageUpload = getImageFromResultSet(rs);
                list.add(imageUpload);
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
        com.createStatement().executeUpdate("DELETE FROM HinhAnh WHERE (IdHinhAnh = '" + IdImage + "')");
    }

    public static void save(ImageUpload imageUpload, File file) throws SQLException, IOException {
        ImageUpload imageUploadExist = findByMaImage(imageUpload.getIdImager());
        if (imageUploadExist.getIdImager() != 0) {
            if (file != null) {
                PreparedStatement pst = com.prepareStatement("UPDATE HinhAnh SET IdSanPham = '" + imageUpload.getImage() +
                        "', CheDo = '" + imageUpload.getIdImager() +
                        "', HinhAnh = ? where IdHinhAnh = " + imageUpload.getIdImager());
                InputStream inputStream = new FileInputStream(file);
                pst.setBinaryStream(1, inputStream, (int) file.length());
                pst.execute();
            } else {
                PreparedStatement pst = com.prepareStatement("UPDATE HinhAnh SET IdSanPham = ?," +
                        "CheDo = ?," +
                        " where IdHinhAnh = ?");
                pst.setInt(1, imageUpload.getIdSanPham());
                pst.setString(2, imageUpload.getRegimeImager());
                pst.execute();
            }
        } else {
            if (file == null) {
                DecimalFormat df = new DecimalFormat("###");
                PreparedStatement pst = com.prepareStatement(
                        "INSERT INTO HinhAnh ( CheDo, IdSanPham) VALUES (?,?)");
                pst.setInt(1, imageUpload.getIdSanPham());
                pst.setString(2, imageUpload.getRegimeImager());
                pst.execute();
            } else {
                InputStream inputStream = new FileInputStream(file);
                PreparedStatement pst = com.prepareStatement(
                        "INSERT INTO HinhAnh ( HinhAnh, CheDo ,IdSanPham ) VALUES (?,?,?)");
                pst.setInt(1, imageUpload.getIdSanPham());
                pst.setString(2, imageUpload.getRegimeImager());
                pst.setBinaryStream(3, inputStream, (int) file.length());
                pst.execute();
                inputStream.close();
            }
        }
    }
}

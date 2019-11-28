package controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sample.hopital.helpers.Routes;
import sample.hopital.hospitalfx.HomeViewController;
import service.Notification;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeView implements Initializable {
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private AnchorPane holderPane;
    @FXML
    private JFXDrawer drawer;

    public static boolean role = false;
    Notification notification = new Notification();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }

        });
        try {
            VBox sidePane = FXMLLoader.load(getClass().getResource("view/Drawer.fxml"));
            AnchorPane duAnPane = FXMLLoader.load(getClass().getResource("view/DuAn.fxml"));
            AnchorPane sanPhamPane = FXMLLoader.load(getClass().getResource("view/SanPham.fxml"));
            AnchorPane doiTacPane = FXMLLoader.load(getClass().getResource("view/DoiTac.fxml"));
            AnchorPane khachHangPane = FXMLLoader.load(getClass().getResource("view/KhachHang.fxml"));
            AnchorPane nguoiDungPane = FXMLLoader.load(getClass().getResource("view/NguoiDung.fxml"));
            AnchorPane updateAnhPane = FXMLLoader.load(getClass().getResource("view/ImageUploader.fxml"));
            AnchorPane danhGiaPane = FXMLLoader.load(getClass().getResource("view/DanhGiaKhachhang.fxml"));
            AnchorPane dashBoardPane = FXMLLoader.load(getClass().getResource("view/Dashboard.fxml"));
//            setNode(welcome);
            drawer.setSidePane(sidePane);

            for (Node node : sidePane.getChildren()) {
                if (node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent ev) -> {
                        switch (node.getAccessibleText()) {
                            case "homeMenu":
                                drawer.close();
                                setNode(dashBoardPane);
                                break;
                            case "duAnMenu":
                                drawer.close();
                                setNode(duAnPane);
                                break;
                            case "nguoiDung":
                                drawer.close();
                                if (HomeView.role == true) {
                                    setNode(nguoiDungPane);
                                } else {
                                    notification.notification("Lỗi truy cập", "Bạn không có quyền truy cập", 1);
                                }
                                break;
                            case "doiTac":
                                drawer.close();
                                setNode(doiTacPane);
                                break;
                            case "sanPhamMenu":
                                drawer.close();
                                setNode(sanPhamPane);
                                break;
                            case "khachHang":
                                drawer.close();
                                setNode(khachHangPane);
                                break;
                            case "danhGia":
                                drawer.close();
                                setNode(danhGiaPane);
                                break;
                            case "updateAnh":
                                drawer.close();
                                setNode(updateAnhPane);
                                break;
                        }
                    });
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add(node);
    }
}

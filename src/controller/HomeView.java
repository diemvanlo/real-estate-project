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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeView  implements Initializable {
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private AnchorPane holderPane;
    @FXML
    private JFXDrawer drawer;

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
//            AnchorPane login = FXMLLoader.load(getClass().getResource(Routes.LOGINVIEW));
//            AnchorPane payments = FXMLLoader.load(getClass().getResource(Routes.PAYMENTSVIEW));
//            AnchorPane appointment = FXMLLoader.load(getClass().getResource(Routes.APPOINTMENTSVIEW));
//            AnchorPane welcome = FXMLLoader.load(getClass().getResource(Routes.WELCOMEVIEW));
//            setNode(welcome);
            drawer.setSidePane(sidePane);

            for (Node node : sidePane.getChildren()) {
                if (node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent ev) -> {
                        switch (node.getAccessibleText()) {
                            case "homeMenu":
                                drawer.close();
//                                setNode(welcome);
                                break;
                            case "duAnMenu":
                                drawer.close();
                                setNode(duAnPane);
                                break;
                            case "paymentMenu":
                                drawer.close();
//                                setNode(payments);
                                break;
                            case "appointmentMenu":
                                drawer.close();
//                                setNode(appointment);
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

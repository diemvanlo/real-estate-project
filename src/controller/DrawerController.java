/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author danml
 */
public class DrawerController implements Initializable {

    @FXML
    private JFXButton homeBtn;
    @FXML
    private JFXButton sanPhamBtn;
    @FXML
    private JFXButton projectBtn;
    @FXML
    private JFXButton doiTacBtn;
    @FXML
    private JFXButton userBtn;
    @FXML
    private JFXButton khachHanhBtn;
    @FXML
    private JFXButton updateAnhBtn;
    @FXML
    private JFXButton logoutBtn;
    @FXML
    private JFXButton exitBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void logOut(ActionEvent event) {
        try {
            HomeView.role = false;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Login.fxml"));
            Parent root = loader.load();
            Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            appStage.hide();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception ex) {
            Logger.getLogger(DrawerController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }

}

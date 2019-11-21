package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import service.Notification;
import service.UserService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private JFXTextField emailTextField;

    @FXML
    private JFXPasswordField passwordTextField;

    @FXML
    private JFXCheckBox rememberMeCheckBox;

    @FXML
    private JFXButton btnLogin;


    public LoginController() throws SQLException {
    }

    public Notification notification = new Notification();

    public void login(javafx.event.ActionEvent actionEvent) throws IOException, SQLException {
        User user = UserService.findByUserName(emailTextField.getText());

        if (user.getUserName() != null && user.getPassWord().equals(passwordTextField.getText())) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/HomeView.fxml"));
            Parent root = loader.load();
            Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            appStage.hide();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            notification.notification("Đăng nhập thành công", "Vai Trò : " + user.getRole(), 0);
            if (user.getRole() == 1){
                HomeView.role = true;
            }
        } else {
            notification.notification("Đăng nhập thất bại", "UserName hoặc mật khẩu sai",1);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}

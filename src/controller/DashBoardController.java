package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class DashBoardController {
    public void changeStage(ActionEvent actionEvent, String form) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(form));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void toPointTable(ActionEvent actionEvent) throws IOException, SQLException {
        changeStage(actionEvent, "controller/view/DoanhThuTheoNam.fxml");
    }

    public void toDoanhThuCacNamTable(ActionEvent actionEvent) throws IOException, SQLException {
        changeStage(actionEvent, "view/DoanhThuCacNam.fxml");
        //changeStage(actionEvent, "controller/view/DoanhThuCacNam.fxml");
    }

    public void toTongHopDiemTable(ActionEvent actionEvent) throws IOException, SQLException {
        changeStage(actionEvent, "views/DoanhThuTheoNam.fxml");
    }
    public void toThongKeDoanhThu(ActionEvent actionEvent) throws IOException, SQLException {
        changeStage(actionEvent, "view/DoanhThuTheoNam.fxml");
    }
}

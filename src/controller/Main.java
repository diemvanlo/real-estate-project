package controller;

import controller.assets.efflect.FunLevelGauge;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {
    private FunLevelGauge gauge;
    double countdown = 0;
    Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void init() {
        gauge = new FunLevelGauge();
        gauge.setPrefSize(400, 400);
        gauge.setLevel(0);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setPrimaryStage(primaryStage);
        StackPane pane = new StackPane(gauge);
        pane.setPadding(new Insets(10));
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Quản lý ....");
        primaryStage.setScene(scene);
        primaryStage.show();
        Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                countdown = countdown + 0.5;
                System.out.println(countdown);
                gauge.setLevel(countdown);
                if (countdown > 0.9) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Parent root = null;
                            try {
                                root = FXMLLoader.load(getClass().getResource("view/Login.fxml"));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            primaryStage.setScene(new Scene(root));
                            primaryStage.show();
                        }
                    });
                    t.cancel();
                    t.purge();
                }
            }
        };
        t.schedule(tt, 500, 2000);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

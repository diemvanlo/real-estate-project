package service;

import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class Notification {
    public void notification(String title, String content, int type) {
        Notifications notifications = Notifications.create().title(title).text(content).graphic(null)
                .hideAfter(Duration.seconds(5)).position(Pos.BOTTOM_RIGHT);
        if (type == 0) {
            notifications.showInformation();
        } else if (type == 1) {
            notifications.showError();
        }
    }
}

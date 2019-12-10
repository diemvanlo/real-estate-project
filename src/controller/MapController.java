package controller;

import com.jfoenix.controls.JFXComboBox;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.util.MarkerImageFactory;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Project;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MapController extends Application implements MapComponentInitializedListener, Initializable {
    @FXML
    public JFXComboBox watchingMode;
    @FXML
    protected GoogleMapView mapComponent;
    protected GoogleMap map;
    public String mapX;
    public String mapY;
    public Project project;
    public MapTypeIdEnum mapTypeIdEnum = MapTypeIdEnum.TERRAIN;
    @FXML
    BorderPane boderPane;

    @Override
    public void start(Stage stage) {

        mapComponent = new GoogleMapView(Locale.getDefault().getLanguage(), null);
        mapComponent.addMapInializedListener(this);

        BorderPane bp = new BorderPane();
        ToolBar tb = new ToolBar();

        bp.setTop(tb);

        bp.setCenter(mapComponent);

        Scene scene = new Scene(bp, 650, 700);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void mapInitialized() {
        //Once the map has been loaded by the Webview, initialize the map details.
        LatLong centre = new LatLong(this.project.getMapX(), this.project.getMapY());
        MapOptions options = new MapOptions();
        options.center(centre)
                .zoom(17)
                .overviewMapControl(true)
                .panControl(true)
                .rotateControl(true)
                .scaleControl(true)
                .streetViewControl(true)
                .zoomControl(true)
                .mapType(mapTypeIdEnum);
        map = mapComponent.createMap(options, false);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(centre)
                .title("Centre Marker")
                .icon(MarkerImageFactory.createMarkerImage("/au/net/capper/gmapsfx/sample/res/mymarker.png", "png"))
                .animation(Animation.DROP)
                .visible(true);
        Marker myMarker = new Marker(markerOptions);
        map.addMarker(myMarker);
        String infoImg = MarkerImageFactory.createMarkerImage("/sample/res/mymarker2.png", "png");
        Logger.getLogger(getClass().getName()).log(Level.INFO, "InfoImage built as: {0}", infoImg);
        InfoWindowOptions infoOptions = new InfoWindowOptions();
        infoOptions.content("<h2>Đây là vị trí dự án</h2><p>Tên dự án  " + this.project.getTenDuAn() + "</p><p><img height=50 width=50 src=\"" + "https://freshome.com/wp-content/uploads/2018/09/contemporary-exterior.jpg" + "\" /></p>" +
                "<p>Loại hình  " + this.project.getLoaiHinh() + "</p>" +
                "<p>Địa chỉ  " + this.project.getDiaChi() + "</p>" +
                "<p>Diện tích  " + this.project.getDienTich() + "</p>" +
                "<p>Hình thức quản lý  " + this.project.getHinhThucQuanLi() + "</p>" +
                "<p>Hình thức đầu tư  " + this.project.getHinhThucDauTu() + "</p>" +
                "<p>Trạng thái " + this.project.getTrangThai() + "</p>" +
                "<p>Bán kinh  " + this.project.getBanKinh() + "</p>"
        )
                .position(centre);
        InfoWindow window = new InfoWindow(infoOptions);
        window.open(map, myMarker);
    }

    public void reloadMap() {
        //Once the map has been loaded by the Webview, initialize the map details.
        LatLong centre = new LatLong(Double.valueOf(this.mapX), Double.valueOf(this.mapY));
        MapOptions options = new MapOptions();
        options.center(centre)
                .zoom(17)
                .overviewMapControl(true)
                .panControl(true)
                .rotateControl(true)
                .scaleControl(true)
                .streetViewControl(true)
                .zoomControl(true)
                .mapType(mapTypeIdEnum);
        map = mapComponent.createMap(options, false);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(centre)
                .title("Centre Marker")
                .icon(MarkerImageFactory.createMarkerImage("/au/net/capper/gmapsfx/sample/res/mymarker.png", "png"))
                .animation(Animation.DROP)
                .visible(true);
        Marker myMarker = new Marker(markerOptions);
        map.addMarker(myMarker);
        String infoImg = MarkerImageFactory.createMarkerImage("/sample/res/mymarker2.png", "png");
        Logger.getLogger(getClass().getName()).log(Level.INFO, "InfoImage built as: {0}", infoImg);
        InfoWindowOptions infoOptions = new InfoWindowOptions();
        infoOptions.content("<h2>Đây là vị trí dự án</h2><p>Tên dự án  " + this.project.getTenDuAn() + "</p><p><img height=50 width=50 src=\"" + "https://freshome.com/wp-content/uploads/2018/09/contemporary-exterior.jpg" + "\" /></p>" +
                "<p>Loại hình  " + this.project.getLoaiHinh() + "</p>" +
                "<p>Địa chỉ  " + this.project.getDiaChi() + "</p>" +
                "<p>Diện tích  " + this.project.getDienTich() + "</p>" +
                "<p>Hình thức quản lý  " + this.project.getHinhThucQuanLi() + "</p>" +
                "<p>Hình thức đầu tư  " + this.project.getHinhThucDauTu() + "</p>" +
                "<p>Trạng thái " + this.project.getTrangThai() + "</p>" +
                "<p>Bán kinh  " + this.project.getBanKinh() + "</p>"
        )
                .position(centre);
        InfoWindow window = new InfoWindow(infoOptions);
        window.open(map, myMarker);
    }

    public void init(String mapX, String mapY, Project project) {
        this.mapX = mapX;
        this.mapY = mapY;
        this.project = project;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapComponent = new GoogleMapView(Locale.getDefault().getLanguage(), null);
        mapComponent.addMapInializedListener(this);
        boderPane.setTop(watchingMode);
        boderPane.setCenter(mapComponent);
        watchingMode.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("TERRAIN")) {
                    mapTypeIdEnum = MapTypeIdEnum.TERRAIN;
                } else if (newValue.equals("ROADMAP")) {
                    mapTypeIdEnum = MapTypeIdEnum.ROADMAP;
                } else if (newValue.equals("SATELLITE")) {
                    mapTypeIdEnum = MapTypeIdEnum.SATELLITE;
                } else if (newValue.equals("HYBRID")) {
                    mapTypeIdEnum = MapTypeIdEnum.HYBRID;
                }
                reloadMap();
            }
        });
    }
}

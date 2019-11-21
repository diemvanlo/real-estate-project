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
    public String name;
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
        LatLong centre = new LatLong(16.0247701, 108.1836957);
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
        infoOptions.content("<h2>Đây là vị trí dự án</h2><p>Tên dự án  " + this.name + "</p><p><img height=50 width=50 src=\"" + "https://fyf.tac-cdn.net/images/products/large/BF116-11KM_R.jpg?auto=webp&quality=60" + "\" /></p>")
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
        infoOptions.content("<h2>Here's an info window</h2><p>with some info and a picture</p><p><img src=\"" + "https://fyf.tac-cdn.net/images/products/large/BF116-11KM_R.jpg?auto=webp&quality=60" + "\" /></p>")
                .position(centre);
        InfoWindow window = new InfoWindow(infoOptions);
        window.open(map, myMarker);
    }

    public void init(String mapX, String mapY, String name) {
        this.mapX = mapX;
        this.mapY = mapY;
        this.name = name;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapComponent = new GoogleMapView(Locale.getDefault().getLanguage(), null);
        mapComponent.addMapInializedListener(this);
//        reloadMap();
        boderPane.setTop(watchingMode);
        boderPane.setCenter(mapComponent);
        watchingMode.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("TERRAIN")) {
                    mapTypeIdEnum = MapTypeIdEnum.TERRAIN;
                } else if (newValue.equals("ROADMAP")){
                    mapTypeIdEnum = MapTypeIdEnum.ROADMAP;
                } else if (newValue.equals("SATELLITE")){
                    mapTypeIdEnum = MapTypeIdEnum.SATELLITE;
                } else if (newValue.equals("HYBRID")){
                    mapTypeIdEnum = MapTypeIdEnum.HYBRID;
                }
                reloadMap();
            }
        });
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.map.sample;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.LatLongBounds;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.util.MarkerImageFactory;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

/**
 *
 * @author Geoff Capper
 */
public class SampleApp extends Application implements MapComponentInitializedListener {
	
	protected GoogleMapView mapComponent;
    protected GoogleMap map;
    
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
        LatLong centre = new LatLong(-42.138968, 146.23352);
        
        MapOptions options = new MapOptions();
        options.center(centre)
                .zoom(9)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.TERRAIN);
        
        map = mapComponent.createMap(options, false);
        
        MarkerOptions markerOptions = new MarkerOptions();
        
        markerOptions.position(centre)
                .title("Centre Marker")
                .icon(MarkerImageFactory.createMarkerImage("sample/map/sample/res/mymarker.png", "png"))
                .animation(Animation.DROP)
                .visible(true);
		
        Marker myMarker = new Marker(markerOptions);

        MarkerOptions markerOptions2 = new MarkerOptions();
        LatLong markerLatLong2 = new LatLong(-42.238968, 147.23352);
        markerOptions2.position(markerLatLong2)
                .title("A Different Marker")
                .icon(MarkerImageFactory.createMarkerImage("sample/map/sample/res/mymarker2.png", "png"))
                .visible(true);

        Marker myMarker2 = new Marker(markerOptions2);

        map.addMarker(myMarker);
        map.addMarker(myMarker2);

		String infoImg = MarkerImageFactory.createMarkerImage("sample/map/sample/res/Flower.png", "png");
		Logger.getLogger(getClass().getName()).log(Level.INFO, "InfoImage built as: {0}", infoImg);
        System.out.println(MarkerImageFactory.createMarkerImage("sample/map/sample/res/mymarker2.png", "png"));
        InfoWindowOptions infoOptions = new InfoWindowOptions();
        infoOptions.content("<h2>Here's an info window</h2><p>with some info and a picture</p><p><img src=\"" + infoImg + "\" /></p>")
                .position(centre);

        InfoWindow window = new InfoWindow(infoOptions);
        window.open(map, myMarker);
        
    }
	
	
}
